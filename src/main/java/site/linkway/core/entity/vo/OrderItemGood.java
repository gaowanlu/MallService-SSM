package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemGood {
    public int num;
    public String goodId;
    public double price;
    public String name;
    public String profile;
    public List<String> imgsURL=new ArrayList<>();
}
