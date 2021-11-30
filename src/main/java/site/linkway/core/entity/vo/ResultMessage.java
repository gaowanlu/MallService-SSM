package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {
    boolean result;
    String message;

    public ResultMessage(String message) {
        this.result = false;
        this.message = message;
    }
}
