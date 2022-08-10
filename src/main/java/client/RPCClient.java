package client;

import common.Blog;
import common.User;
import service.BlogService;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 13828);
        UserService userService = clientProxy.getProxy(UserService.class);

        User user = userService.getUserByUserId(10);
        System.out.println("从服务端得到的user: " + user);

        user = User.builder().userName("张三").id(20).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入user: " + user);

        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blog = blogService.getBlogById(1000);
        System.out.println("从服务端获取blog: " + blog);
    }
}
