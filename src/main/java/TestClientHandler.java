import battleapp.netproto.Msg;
import battleapp.netproto.Struct;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class TestClientHandler extends SimpleChannelInboundHandler<Msg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");
        // 编码
        Msg.CS_AskMatchGameInfo.Builder msg = Msg.CS_AskMatchGameInfo.newBuilder();

        ByteBuf buffer = PacketCodeC.loginEncoder(ctx.alloc(),msg);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
