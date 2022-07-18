package ireina.test;

import ireina.rpc.api.HelloObject;
import ireina.rpc.api.HelloService;
import ireina.rpc.client.RpcClientProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Testing client
 * @author ireina7
 */
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@SpringBootConfiguration
@ComponentScan(basePackages = {"ireina.rpc"})
@RunWith(SpringRunner.class)
public class TestClient {
    @Test
    public void testClient() {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
