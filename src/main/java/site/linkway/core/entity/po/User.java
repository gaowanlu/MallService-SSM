package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public String userId;
    public String name;
    public String sex;
    public String headImgId;
    public double money;
    public String email;
}
