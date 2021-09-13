package cr.una.taskapp.backend.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base Error class
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 8/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSubError {
    private String code;
    private String message;

    public ApiSubError(String message) {
        this.code = "NO-CODE";
        this.message = message;
    }
}
