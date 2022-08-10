package server;

import service.BlogService;
import service.UserService;

import java.util.HashMap;
import java.util.Map;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

//        Map<String, Object> serviceProvide = new HashMap<>();
//
//        // 暴露两个服务接口，即在RPCServer中加一个HashMap
//        serviceProvide.put("service.UserService", userService);
//        serviceProvide.put("service.BlogService", blogService);

        ServiceProvider serviceProvide = new ServiceProvider();
        serviceProvide.provideServiceInterface(userService);
        serviceProvide.provideServiceInterface(blogService);

        RPCServer rpcServer = new ThreadPoolRPCServer(serviceProvide);
        rpcServer.start(13828);
    }
}
