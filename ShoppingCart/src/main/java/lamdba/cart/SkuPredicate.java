package lamdba.cart;

/**
 * Sku选择谓词？接口
 */
public interface SkuPredicate {
    /**
     * 选择判断标准
     * @param sku
     * @return
     */
    boolean test(Sku sku);
}
