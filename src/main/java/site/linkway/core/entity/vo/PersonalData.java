package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*用户个人信息*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalData {
    public String userId;//用户id
    public String name;//用户昵称
    public String sex;//性别
    public String profilePhotoURL;//头像URL
    public double money;//余额
    public String email;//用户邮箱
}
