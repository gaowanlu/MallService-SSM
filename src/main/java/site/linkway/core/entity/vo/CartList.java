package site.linkway.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.linkway.core.entity.po.Cart;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CartList {
    public int status=200;
    public boolean result=true;
    public List<Cart> carts;
    public CartList(){
        this.carts=new ArrayList<>();
    }
}
