package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.linkway.core.entity.po.CartItem;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CartList {
    public boolean result=true;//操作结果
    public List<CartItem> carts;//购物车列表
    public CartList(){
        this.carts=new ArrayList<>();
    }
}
