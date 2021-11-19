package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    boolean result;
    String message;

    public ErrorResult(String message) {
        this.result = false;
        this.message = message;
    }
}
