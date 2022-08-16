package client;

import common.RPCRequest;
import common.RPCResponse;
import lombok.AllArgsConstructor;
import register.ServiceRegister;
import register.ZkServiceRegister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


@AllArgsConstructor
public class SimpleRPCClient implements RPCClient {
    private String host;
    private int port;
    private ServiceRegister serviceRegister;

    public SimpleRPCClient() {
        this.serviceRegister = new ZkServiceRegister();
    }

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        // 从注册中心获取host port
        InetSocketAddress address = serviceRegister.serviceDiscovery(request.getInterfaceName());
        host = address.getHostName();
        port = address.getPort();
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse) objectInputStream.readObject();

            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("客户端错误");
            return null;
        }
    }
}
