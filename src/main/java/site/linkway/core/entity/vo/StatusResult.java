package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*API 基本结构 status为状态位  result为请求处理结果*/
@Data
@AllArgsConstructor
public class StatusResult {
    boolean result;//操作结果
    boolean isAdmin;//是否为管理员

    public StatusResult() {
        this.result = true;
    }
}
