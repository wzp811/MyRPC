package client;

import codec.JsonSerializer;
import codec.MyDecode;
import codec.MyEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
//      Java自带 解码器
//    @Override
//    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        ChannelPipeline pipeline = socketChannel.pipeline();
//        // 消息格式 [长度][消息体]
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        // 计算当前待发送消息的长度，写入到前4个字节中
//        pipeline.addLast(new LengthFieldPrepender(4));
//        pipeline.addLast(new ObjectEncoder());
//
//        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
//            @Override
//            public Class<?> resolve(String className) throws ClassNotFoundException {
//                return Class.forName(className);
//            }
//        }));
//
//        pipeline.addLast(new NettyClientHandler());
//    }

//  自定义解码器
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 使用自定义的编解码器
        pipeline.addLast(new MyDecode());
        // 编码需要传入序列化器，这里是json，还支持ObjectSerializer，也可以自己实现其他的
        pipeline.addLast(new MyEncode(new JsonSerializer()));
        pipeline.addLast(new NettyClientHandler());
    }
}
