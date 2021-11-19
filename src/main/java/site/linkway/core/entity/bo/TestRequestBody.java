package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
