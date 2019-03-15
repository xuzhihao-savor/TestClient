import io.netty.buffer.ByteBuf;

public class BattleRequestPacket  extends  Packet{
    private short id;
    private int length;
    private ByteBuf protobuf;

    public Byte getCommand() {
        return null;
    }
}
