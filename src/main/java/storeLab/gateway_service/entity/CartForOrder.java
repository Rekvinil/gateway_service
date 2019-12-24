package storeLab.gateway_service.entity;

public class CartForOrder {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private int count;
    private int discount;

    public CartForOrder(Integer id, Integer userId, Integer productId, int count, int discount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
