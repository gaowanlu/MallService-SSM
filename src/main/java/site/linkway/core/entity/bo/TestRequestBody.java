package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*为了测试 请求体为json 类型 写的实体类
* 先放这里吧
* 编码：高万禄
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestRequestBody {
    @Override
    public String toString() {
        return "TestRequestBody{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String name;
    public int age;
}
