package io1.github.example.codes.protostuff;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by tuyuelai on 2017/12/28.
 */
public class ByteToArrayDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> list) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.getBytes(0,bytes);
    }
}
