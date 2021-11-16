package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*API 基本结构 status为状态位  result为请求处理结果*/
@Data
@AllArgsConstructor
public class StatusResult {
    int status;
    boolean result;
    public StatusResult(){
        this.status=200;
        this.result=true;
    }
}
