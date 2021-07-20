package cart;

import com.alibaba.fastjson.JSON;
import com.cart.CartService;
import com.cart.Sku;
import com.cart.SkuPredicate;
import org.junit.Test;

import java.util.List;

public class Version5Test {
    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();

        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuPredicate(){
                @Override
                public boolean test(Sku sku){
                    return sku.getSkuPrice() > 1000;
                }
        });//因为只使用一次，所以可以用匿名类。
        System.out.println(
                JSON.toJSONString(result,true));
    }
}
