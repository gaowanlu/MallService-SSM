package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCart {
    String goodId;//物品id
    int num;//物品数量
}
