package cart;
import com.alibaba.fastjson.JSON;
import com.cart.CartService;
import com.cart.Sku;
import org.junit.Test;

import java.util.List;

public class Version1Test {
    @Test
    public void filterElectronicsSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result =
                CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(
                JSON.toJSONString(result,true));
    }
}
