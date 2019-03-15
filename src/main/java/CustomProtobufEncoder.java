//package com.dianhun.netty.testclient;
//
//import battleapp.netproto.Msg;
//import com.google.protobuf.MessageLite;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandler.Sharable;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//
///**
// * 参考ProtobufVarint32LengthFieldPrepender 和 ProtobufEncoder
// */
//@Sharable
//public class CustomProtobufEncoder extends MessageToByteEncoder<MessageLite> {
//
//
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
//
//
//        byte[] body = msg.toByteArray();
//        byte[] header = encodeHeader(msg, (short)body.length);
//
//        out.writeBytes(header);
//        out.writeBytes(body);
//
//        return;
//    }
//
//    private ByteBuf encodeHeader(MessageLite msg, short bodyLength) {
//        short messageType = 0x0f;
//
//        if (msg instanceof Msg.CS_TestMove) {
//            messageType = 10001;
//
//       }
//
//        ByteBuf header = new ByteBuf() {
//        }
//        header[0] = (byte) (bodyLength & 0xff);
//        header[1] = (byte) ((bodyLength >> 8) & 0xff);
//        header[2] = 0; // 保留字段
//        header[3] = messageType;
//
//        return header;
//
//    }
//}