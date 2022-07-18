package ireina.test;

import ireina.rpc.api.HelloService;
import ireina.rpc.registry.DefaultServiceRegistry;
import ireina.rpc.registry.ServiceRegistry;
import ireina.rpc.server.SocketServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;

/**
 * Testing server
 * @author ireina7
 */
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@SpringBootConfiguration
@ComponentScan(basePackages = {"ireina.rpc"})
@RunWith(SpringRunner.class)
public class TestServer {
    @Autowired
    SocketServer server;
    @Test
    public void testServer() {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer rpcServer = new SocketServer(serviceRegistry);
        try {
            rpcServer.bind(9000).serve();
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }
}
