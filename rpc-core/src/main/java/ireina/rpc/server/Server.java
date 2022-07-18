package ireina.rpc.server;

import java.net.UnknownHostException;

/**
 * The main {@code Server} interface
 * @author ireina7
 * */
public interface Server {
    Server bind(String ip, int port);
    default Server bind(int port) {
        return bind("0.0.0.0", port);
    }
    void serve() throws UnknownHostException;
}

