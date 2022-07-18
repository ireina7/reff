package ireina.test;

import ireina.rpc.api.HelloObject;
import ireina.rpc.api.HelloService;
import ireina.rpc.client.RpcClientProxy;

/**
 * Testing client
 * @author ireina7
 */
public class TestClient {

    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}
