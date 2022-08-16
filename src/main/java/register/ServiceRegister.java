package register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    void register(String serviceName, InetSocketAddress serverAddress);
    InetSocketAddress serviceDiscovery(String serviceName);
}
