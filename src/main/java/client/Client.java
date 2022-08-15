package client;

import common.Blog;
import common.User;
import service.BlogService;
import service.UserService;

public class Client {
    public static void main(String[] args) {
        // 构建一个使用Java Socket/ netty/...传输的客户端
        RPCClient rpcClient = new NettyRPCClient("127.0.0.1", 13828);
        // 把这个客户端传入代理客户端
        RPCClientProxy rpcClientProxy = new RPCClientProxy(rpcClient);
        // 代理客户端根据不同的服务，获得一个代理类，并且这个代理类的方法已获得增强（封装数据，发送请求）
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        // 调用方法
        User user = userService.getUserByUserId(10);
        System.out.println("从服务端得到user: " + user);

        user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入数据: " + integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blog = blogService.getBlogById(10000);
        System.out.println("从服务端得到blog: " + blog);
    }
}
