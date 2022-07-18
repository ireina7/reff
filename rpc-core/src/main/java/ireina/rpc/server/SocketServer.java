package ireina.rpc.server;

import ireina.rpc.registry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 远程方法调用的提供者（服务端）
 * @author ireina7
 */
@Component
public class SocketServer implements Server {

    private final ExecutorService threadPool;
    private static final Logger logger =
        LoggerFactory.getLogger(SocketServer.class);
    private final ServiceRegistry serviceRegistry;
    private RequestHandler requestHandler =
        new RequestHandler();
    private String ip;
    private int port;

    @Autowired
    public SocketServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue =
            new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory =
            Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            workingQueue,
            threadFactory
        );
    }
    
    @Override
    public Server bind(String ip, int port) {
        this.ip = ip;
        this.port = port;
        return this;
    }
    
    @Override
    public void serve() {
        try (
            ServerSocket serverSocket =
                new ServerSocket(port, 50, InetAddress.getByName(ip))
        ) {
            logger.info("服务器正在启动...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接！IP为：" + socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }
}
