package home

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.MessageToMessageDecoder


class NettyTcpServer(private val port: Int) {


  fun run() {
    val bootstrap = ServerBootstrap()
       .group(NioEventLoopGroup(), NioEventLoopGroup())
       .channel(NioServerSocketChannel::class.java)
       .option(ChannelOption.SO_BACKLOG, 128)
       .childOption(ChannelOption.SO_KEEPALIVE, true)
       .childHandler(object : ChannelInitializer<SocketChannel>() {
         override fun initChannel(ch: SocketChannel) {
           ch.pipeline()
              .addLast(LengthFieldBasedFrameDecoder(1024, 0, 4))
              .addLast(MessageToMessageHandler())
              .addLast(MyHandler())
         }
       })

    bootstrap.bind(port).sync()
  }

}

@Sharable
class MyHandler : ChannelInboundHandlerAdapter() {

  override fun channelActive(ctx: ChannelHandlerContext?) {
  }

  override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {

  }

}

@Sharable
class MessageToMessageHandler : MessageToMessageDecoder<ByteBuf>() {

  override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
  }

}