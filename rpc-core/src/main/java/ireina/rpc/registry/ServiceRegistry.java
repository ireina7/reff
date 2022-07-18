package ireina.rpc.registry;

/**
 * @author ireina7
 * */
public interface ServiceRegistry {
    <T> void register(T service);
    Object getService(String serviceName);
}
