import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Myencoder extends MessageToByteEncoder<MessageLite>  {
    private static final int HEADER_SIZE=6;
    private int length;
    private  short id=10001;

    protected void encode(ChannelHandlerContext channelHandlerContext, MessageLite in, ByteBuf out) throws Exception {
        System.out.println("encode");
        System.out.println(in.getClass().getName());
        byte[] body = in.toByteArray();
        length=body.length+HEADER_SIZE;
        out.writeByte(length);
        out.writeByte(this.id);
        out.writeBytes(body);
    }
}
