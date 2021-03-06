package com.hz.dafeiji.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * user         LIUKUN
 * time         2014-12-16 11:56
 */

public class HttpGameDispatcher1 extends SimpleChannelInboundHandler<DefaultFullHttpRequest>{

    private HttpRequest request;
    /**
     * Buffer that stores the response content
     */
    private final StringBuilder buf = new StringBuilder();

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception{
        ctx.flush();
    }

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, DefaultFullHttpRequest msg ){

        if( msg instanceof HttpRequest ) {
            HttpRequest request = this.request = (HttpRequest) msg;

            if( is100ContinueExpected( request ) ) {
                send100Continue( ctx );
            }

            buf.setLength( 0 );
            buf.append( "WELCOME TO THE WILD WILD WEB SERVER\r\n" );
            buf.append( "===================================\r\n" );

            buf.append( "VERSION: " ).append( request.getProtocolVersion() ).append( "\r\n" );
            buf.append( "HOSTNAME: " ).append( getHost( request, "unknown" ) ).append( "\r\n" );
            buf.append( "REQUEST_URI: " ).append( request.getUri() ).append( "\r\n\r\n" );

            HttpHeaders headers = request.headers();
            if( !headers.isEmpty() ) {
                for( Map.Entry<String, String> h : headers ) {
                    String key = h.getKey();
                    String value = h.getValue();
                    buf.append( "HEADER: " ).append( key ).append( " = " ).append( value ).append( "\r\n" );
                }
                buf.append( "\r\n" );
            }

            QueryStringDecoder queryStringDecoder = new QueryStringDecoder( request.getUri() );
            Map<String, List<String>> params = queryStringDecoder.parameters();
            if( !params.isEmpty() ) {
                for( Map.Entry<String, List<String>> p : params.entrySet() ) {
                    String key = p.getKey();
                    List<String> vals = p.getValue();
                    for( String val : vals ) {
                        buf.append( "PARAM: " ).append( key ).append( " = " ).append( val ).append( "\r\n" );
                    }
                }
                buf.append( "\r\n" );
            }

            String x = buf.toString();
            System.out.println( buf );
            System.out.println( "-------------------------------------------------------------" );
            appendDecoderResult( buf, request );
            System.out.println( buf.toString().equals( x ) );
        }

        if( msg instanceof HttpContent ) {
            HttpContent httpContent = (HttpContent) msg;

            ByteBuf content = httpContent.content();
            if( content.isReadable() ) {
                buf.append( "CONTENT: " );
                buf.append( content.toString( CharsetUtil.UTF_8 ) );
                buf.append( "\r\n" );
                appendDecoderResult( buf, request );
            }

            if( msg instanceof LastHttpContent ) {
                buf.append( "END OF CONTENT\r\n" );

                LastHttpContent trailer = (LastHttpContent) msg;
                if( !trailer.trailingHeaders().isEmpty() ) {
                    buf.append( "\r\n" );
                    for( String name : trailer.trailingHeaders().names() ) {
                        for( String value : trailer.trailingHeaders().getAll( name ) ) {
                            buf.append( "TRAILING HEADER: " );
                            buf.append( name ).append( " = " ).append( value ).append( "\r\n" );
                        }
                    }
                    buf.append( "\r\n" );
                }

                writeResponse( trailer, ctx );
            }
        }
    }

    private static void appendDecoderResult( StringBuilder buf, HttpObject o ){
        DecoderResult result = o.getDecoderResult();
        if( result.isSuccess() ) {
            return;
        }

        buf.append( ".. WITH DECODER FAILURE: " );
        buf.append( result.cause() );
        buf.append( "\r\n" );
    }

    private boolean writeResponse( HttpObject currentObj, ChannelHandlerContext ctx ){
        // Decide whether to close the connection or not.
        boolean keepAlive = isKeepAlive( request );
        String s = "{" +
                "  \"h\":\"V09mXRRyY2ZmXnJbXWZHcnEVQVwPL0wgYC90OAAfEFBzWA\",\n" +
                "  \"uId\":\"19\",\n" +
                "  \"s\":0" +
                "}";
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1, currentObj.getDecoderResult().isSuccess() ? OK : BAD_REQUEST,
                Unpooled.copiedBuffer( buf.toString(), CharsetUtil.UTF_8 ) );
        //Unpooled.copiedBuffer( s, CharsetUtil.UTF_8 ) );

        response.headers().set( CONTENT_TYPE, "text/plain; charset=UTF-8" );

        if( keepAlive ) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().set( CONTENT_LENGTH, response.content().readableBytes() );
            // Add keep alive header as per:
            // - http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set( CONNECTION, HttpHeaders.Values.KEEP_ALIVE );
        }

        // Encode the cookie.
        String cookieString = request.headers().get( COOKIE );
        if( cookieString != null ) {
            Set<Cookie> cookies = CookieDecoder.decode( cookieString );
            if( !cookies.isEmpty() ) {
                // Reset the cookies if necessary.
                for( Cookie cookie : cookies ) {
                    response.headers().add( SET_COOKIE, ServerCookieEncoder.encode( cookie ) );
                }
            }
        } else {
            // Browser sent no cookie.  Add some.
            response.headers().add( SET_COOKIE, ServerCookieEncoder.encode( "key1", "value1" ) );
            response.headers().add( SET_COOKIE, ServerCookieEncoder.encode( "key2", "value2" ) );
        }

        // Write the response.
        ctx.write( response );

        return keepAlive;
    }

    private static void send100Continue( ChannelHandlerContext ctx ){
        FullHttpResponse response = new DefaultFullHttpResponse( HTTP_1_1, CONTINUE );
        ctx.write( response );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        cause.printStackTrace();
        ctx.close();
    }

    private void dispatch(){
    }
}