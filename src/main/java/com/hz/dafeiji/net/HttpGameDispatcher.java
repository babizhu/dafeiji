package com.hz.dafeiji.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.GameWorld;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.HandlerManager;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.dafeiji.net.handler.all.UserLoginHandler;
import com.hz.dafeiji.net.handler.all.UserRegHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * user         LIUKUN
 * time         2014-12-16 11:56
 */

public class HttpGameDispatcher extends SimpleChannelInboundHandler<DefaultFullHttpRequest>{

    private static final Logger logger = LoggerFactory.getLogger( HttpGameDispatcher.class );
    public static final String FUNCTION = "/do";//所有http的请求的响应地址，浏览器经常会请求一个/favicon.ico，因此需要区分一下

    private JSONObject responseJson = new JSONObject();
    private String url;
    private ChannelHandlerContext ctx;

    /**
     * Buffer that stores the response content
     */
    //private final StringBuilder buf = new StringBuilder();
    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        this.ctx = ctx;
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception{
        ctx.flush();
    }

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, DefaultFullHttpRequest msg ){
//        DecoderResult result = msg.getDecoderResult();
//        if( !result.isSuccess() ) {
//            writeResponseError( ErrorCode.HTTP_INVALID_REQUEST );
//            return;
//        }

        //HttpRequest request = msg;


//        buf.setLength( 0 );
//        buf.append( "WELCOME TO THE WILD WILD WEB SERVER\r\n" );
//        buf.append( "===================================\r\n" );
//
//        buf.append( "VERSION: " ).append( msg.getProtocolVersion() ).append( "\r\n" );
//        buf.append( "HOSTNAME: " ).append( getHost( msg, "unknown" ) ).append( "\r\n" );
//        buf.append( "REQUEST_URI: " ).append( msg.getUri() ).append( "\r\n\r\n" );

//        HttpHeaders headers = msg.headers();
//        if( !headers.isEmpty() ) {
//            for( Map.Entry<String, String> h : headers ) {
//                String key = h.getKey();
//                String value = h.getValue();
//                buf.append( "HEADER: " ).append( key ).append( " = " ).append( value ).append( "\r\n" );
//            }
//            buf.append( "\r\n" );
//        }

//        QueryStringDecoder queryStringDecoder = new QueryStringDecoder( msg.getUri() );
//        Map<String, List<String>> params = queryStringDecoder.parameters();
//        if( !params.isEmpty() ) {
//            for( Map.Entry<String, List<String>> p : params.entrySet() ) {
//                String key = p.getKey();
//                List<String> vals = p.getValue();
//                for( String val : vals ) {
//                    buf.append( "PARAM: " ).append( key ).append( " = " ).append( val ).append( "\r\n" );
//                }
//            }
//            buf.append( "\r\n" );
//        }

//        URL url1 = new URL( msg.getUri() );;
        url = (msg.getUri()).replace( "%22", "\"" );

        if( url.length() < 3 || url.substring( 0, 3 ).equals( FUNCTION ) ) {
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder( msg.getUri() );
            Map<String, List<String>> params = queryStringDecoder.parameters();
            String data = getQueryString( params, "data" );
            String signature = getQueryString( params, "s" );
            String session = getQueryString( params, "h" );
            String uname = getQueryString( params, "u" );
            dispatch( signature, data, session, uname );
        } else {
            writeResponseError( ErrorCode.HTTP_INVALID_REQUEST );
        }
    }

    /**
     * 在url中通过key获取相应的值，如果值不存在，则返回null
     *
     * @param queryMap queryMap
     * @param key      key
     * @return 通过key获取相应的值，如果值不存在，则返回null
     */
    private String getQueryString( Map<String, List<String>> queryMap, String key ){
        List<String> list = queryMap.get( key );
        if( list != null ) {
            return list.get( 0 );
        }
        return null;
    }
//    private static void appendDecoderResult( StringBuilder buf, HttpObject o ){
//        DecoderResult result = o.getDecoderResult();
//        if( result.isSuccess() ) {
//            return;
//        }
//
//        buf.append( ".. WITH DECODER FAILURE: " );
//        buf.append( result.cause() );
//        buf.append( "\r\n" );
//    }

    private void writeResponseError( ErrorCode eCode ){


        responseJson.put( "s", eCode.toNum() + "," + eCode );
        writeResponse();
    }

    private void writeResponse(){

        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1, OK,
                Unpooled.copiedBuffer( responseJson.toString(), CharsetUtil.UTF_8 ) );
        //Unpooled.copiedBuffer( s, CharsetUtil.UTF_8 ) );

        response.headers().set( CONTENT_TYPE, "text/plain; charset=UTF-8" );
        response.headers().set( CONTENT_LENGTH, response.content().readableBytes() );
        response.headers().set( "Access-Control-Allow-Origin", "*" );
        ctx.write( response ).addListener( ChannelFutureListener.CLOSE );

        logger.info( "url=" + url + ",response=" + responseJson );
    }


    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{

        logger.error( cause.getMessage() );
        ctx.close();
    }

    /**
     * 检测玩家提交的数字签名对不对
     *
     * @param signature     签名
     */
    private boolean signatureIsValid( String data, String signature ){
        if( signature == null || signature.trim().isEmpty() ) {
            return false;
        }
        return true;
    }

    private void dispatch( String signature, String data, String session, String uname ){
        //logger.info( "signature = [" + signature + "], data = [" + data + "], session = [" + session + "]" );
        if( !signatureIsValid( data, signature ) ) {
            writeResponseError( ErrorCode.SIGNATURE_ERROR );
            return;
        }
        try {
            JSONObject parse = (JSONObject) JSON.parse( data );
            String handlerName = (parse.get( "mod" ).toString() + parse.get( "do" )).toLowerCase();
            IGameHandler handler = HandlerManager.INSTANCE.getHandler( handlerName );

            if( handler == null ) {
                writeResponseError( ErrorCode.HANDLER_NOT_FOUND );
                return;
            }

            responseJson.put( "s", ErrorCode.SUCCESS.toNum() );//缺省情况下为成功

            if( session == null || session.trim().isEmpty() || uname == null || uname.trim().isEmpty() ) {
                if( handler instanceof UserLoginHandler ||
                        handler instanceof UserRegHandler ) {
                    JSONObject request = (JSONObject) parse.get( "p" );
                    handler.run( request, responseJson, null );
                } else {//玩家没有合法的session，却要请求登陆用户才可以请求的数据
                    writeResponseError( ErrorCode.USER_NOT_LOGIN );
                    return;
                }
            } else {

                User user = GameWorld.INSTANCE.getUserBySession( uname, session );
                if( user == null ) {
                    writeResponseError( ErrorCode.USER_NOT_LOGIN );
                    return;
                }
                JSONObject request = (JSONObject) parse.get( "p" );
                handler.run( request, responseJson, user );
            }

            writeResponse();


        } catch( ClientException e ) {
            e.printStackTrace();
            logger.error( "dispatch： ClientException = " + e.getCode() );
            writeResponseError( e.getCode() );

//        } catch( JSONException e ){
//            logger.error( "dispatch：" + e );
//            writeResponseError( ErrorCode.HTTP_INVALID_REQUEST );
        } catch( Exception e ) {
            e.printStackTrace();
            logger.error( "dispatch：" + e );
            writeResponseError( ErrorCode.HTTP_INVALID_REQUEST );

        }


    }
}