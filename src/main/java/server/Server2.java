package server;

import service.BlogService;
import service.UserService;

public class Server2 {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

//        Map<String, Object> serviceProvide = new HashMap<>();
//
//        // 暴露两个服务接口，即在RPCServer中加一个HashMap
//        serviceProvide.put("service.UserService", userService);
//        serviceProvide.put("service.BlogService", blogService);

        // 这里重用了服务暴露类，顺便在注册中心注册，实际上应分开，每个类做各自独立的事
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", 13829);

        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(13829);
    }
}
