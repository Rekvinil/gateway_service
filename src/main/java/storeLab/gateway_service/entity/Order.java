package storeLab.gateway_service.entity;


import java.util.Date;

public class Order {

    private Integer id;
    private Integer userId;
    private float price;
    private Date date;

    public Order() {
    }

    public Order(Integer userId, float price, Date date) {
        this.userId = userId;
        this.price = price;
        this.date = date;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
