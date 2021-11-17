package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*用户个人信息*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalData {
    public int status=200;
    public String userId;
    public String name;
    public String sex;
    public String headImgId;
    public double money;
    public String email;
}
