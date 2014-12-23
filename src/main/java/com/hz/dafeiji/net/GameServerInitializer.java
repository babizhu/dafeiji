package com.hz.dafeiji.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * user         LIUKUN
 * time         2014-12-15 17:50
 */

public class GameServerInitializer extends ChannelInitializer<SocketChannel>{
//    private static final ProtobufVarint32FrameDecoder VARINT32_FRAME_DECODER = new ProtobufVarint32FrameDecoder();

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("decoder", new HttpRequestDecoder());
//        /**
//         * http-response解码器
//         * http服务器端对response编码
//         */
//        pipeline.addLast("encoder", new HttpResponseEncoder());
//        pipeline.addLast( new ReadTimeoutHandler( 10 ) );
//        pipeline.addLast( new WriteTimeoutHandler( 1 ) );
        pipeline.addLast( "codec", new HttpServerCodec() );
        pipeline.addLast( "aggregator", new HttpObjectAggregator( 8192 ) );
        pipeline.addLast( "handler", new HttpGameDispatcher() );

    }
}
