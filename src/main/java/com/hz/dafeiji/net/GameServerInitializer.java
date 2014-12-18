package com.hz.dafeiji.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
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
        pipeline.addLast("codec", new HttpServerCodec());
        pipeline.addLast("handler", new HttpGameDispatcher());

    }
}
