package storeLab.gateway_service.entity;

public class Product {
    private Integer id;

    private String name;
    private float price;
    private String discount;
    private String img;
    private int count;


    public Product() {
    }

    public Product(Integer id, String name, float price, String discount, String img, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.count = count;
    }

    public Product(String name, float price, String discount, String img, int count) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
