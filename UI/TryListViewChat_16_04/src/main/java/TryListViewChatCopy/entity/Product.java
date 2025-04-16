package TryListViewChatCopy.entity;


public class Product {
    private String name;
    private double price;
    private String brand;

    // Constructor
    public Product(String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    // Getters và Setters (nếu cần)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Phương thức toString để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
