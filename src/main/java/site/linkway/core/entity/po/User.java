package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户
public class User {
    public String userId;//用户id
    public String password;//密码
    public String name;//昵称
    public String sex;//性别
    public String headImgId;//头像id
    public double money;//余额
    public String email;//邮箱
}
