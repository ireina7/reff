package ireina.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ireina.rpc.api.HelloObject;
import ireina.rpc.api.HelloService;

/**
 * @author ireina7
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger =
        LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "这是调用的返回值，id = " + object.getId();
    }

}
