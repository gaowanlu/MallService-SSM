package site.linkway.core.entity.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    String goodType;
    int goodTypeId;
    String name;
    String profile;
    double price;
    String goodId;
    int stock;
    int soldSum;
    List<String> imgsURL=new ArrayList<>();
}
