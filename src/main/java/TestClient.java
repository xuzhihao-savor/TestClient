import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import java.net.InetSocketAddress;
import java.net.URL;

public class TestClient {
    private final String host;
    private final int port;
    public TestClient() {
        this(0);
    }

    public TestClient(int port) {
        this("localhost", port);
    }

    public TestClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(this.host, this.port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...");
//                            ch.pipeline().addLast(new TestClientHandler());
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("","",true));
//                            ch.pipeline().addLast(new ReadTimeoutHandler(60));
//                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//                            ch.pipeline().addLast(new ProtobufDecoder(Msg.SC_NotifyGameInit.getDefaultInstance()));
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            ch.pipeline().addLast(new ProtobufEncoder());


                        }
                    });
            System.out.println("created..");

            ChannelFuture cf = b.connect().sync();
            System.out.println("connected...");

            cf.channel().closeFuture().sync();
            System.out.println("closed..");
        } finally {
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws Exception {
        HttpRequestUtils httpRequestUtils=new HttpRequestUtils();
        String herf=httpRequestUtils.getUrl();
        URL url=new URL(herf);
        int port=url.getPort();
        String host=url.getHost();
        System.out.println(port+"    "+host);
        TestClient testClient=new TestClient(host,port);
        testClient.start();
    }
}
