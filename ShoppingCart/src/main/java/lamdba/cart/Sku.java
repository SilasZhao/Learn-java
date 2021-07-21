package lamdba.cart;

public class Sku {
    private Integer skuId;
    private String SkuName;
    private Double SkuPrice;
    private Integer totalNum;
    private Double totalPrice;
    private Enum skuCategory;

    public Integer getSkuId() {
        return skuId;
    }

    public String getSkuName() {
        return SkuName;
    }

    public Double getSkuPrice() {
        return SkuPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Enum getSkuCategory() {
        return skuCategory;
    }

    public Sku(Integer skuId, String skuName, Double skuPrice, Integer totalNum, Double totalPrice, Enum skuCategory) {
        this.skuId = skuId;
        SkuName = skuName;
        SkuPrice = skuPrice;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
        this.skuCategory = skuCategory;
    }


}
