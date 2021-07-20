package cart;

import com.alibaba.fastjson.JSON;
import com.cart.CartService;
import com.cart.Sku;
import com.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.List;

public class Version2Test {
    @Test
    public void filterSkusByCategory(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        System.out.println(JSON.toJSONString(result,true));
    }
}
