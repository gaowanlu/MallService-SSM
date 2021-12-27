package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//管理员
public class Admin {
    public String adminId;//管理员id
    public String userId;//管理员账号id
}
