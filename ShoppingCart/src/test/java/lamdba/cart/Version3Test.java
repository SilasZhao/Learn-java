package lamdba.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class Version3Test {
    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList,null,2000.00,false);
        System.out.println(JSON.toJSONString(result,true));

    }

}
