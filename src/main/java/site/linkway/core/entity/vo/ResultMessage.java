package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {
    boolean result;//操作结果
    String message;//捎带信息

    public ResultMessage(String message) {
        this.result = false;
        this.message = message;
    }
}
