import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeC {
    public  static ByteBuf  loginEncoder(ByteBufAllocator byteBufAllocator, MessageLite.Builder msg) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 PB 对象
        byte[] bytes = msg.build().toByteArray();
        // 3. 实际编码过程
        byteBuf.writeShort(0);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
}
