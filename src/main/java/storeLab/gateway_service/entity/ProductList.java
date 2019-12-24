package storeLab.gateway_service.entity;


public class ProductList {
    private Integer id;

    private Integer productId;
    private Integer orderId;
    private int count;

    public ProductList(int productId, int orderId, int count) {
        this.productId = productId;
        this.orderId = orderId;
        this.count = count;
    }

    public ProductList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
