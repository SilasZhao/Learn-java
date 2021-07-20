package cart;

import com.alibaba.fastjson.JSON;
import com.cart.CartService;
import com.cart.Sku;
import com.cart.SkuPredicate;
import org.junit.Test;

import java.util.List;

public class Version6Test {
    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(
                cartSkuList, (Sku sku) -> sku.getSkuPrice() > 1000);//lamdba 表达式！彻底用逻辑来判断
        System.out.println(
                JSON.toJSONString(result,true));
    }
}
