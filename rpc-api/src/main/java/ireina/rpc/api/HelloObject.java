package ireina.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ireina7
 */
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {

    private Integer id;
    private String message;

}
