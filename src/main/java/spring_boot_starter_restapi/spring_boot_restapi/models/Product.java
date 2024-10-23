package spring_boot_starter_restapi.spring_boot_restapi.models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "tb_product") // chỉ định tên bảng maf entity này có lên kết với csdl
public class Product {
//    This is field the primary key
    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName="product_sequence",
            allocationSize=1
    )
    // Đinh nghĩa giá trị của trường được thực hiện bằng SequenceGenerator với name là product_sequence
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="product_sequence"
    )
//    @GeneratedValue(strategy = GenerationType.AUTO)  //auto-increment value
    private Long id;

    @Column(nullable = false,unique = true,length=255) //chỉ định cac constrant cho truong;
    private String name;

    private int productYear;
    private double price;
    private String url;

    // caculated field = transient
    @Transient
    private int age;
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - productYear;
    }
    public Product() {
    }

    public Product(String name, int year, double price, String url) {
        this.name = name;
        this.productYear = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int year) {
        this.productYear = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + productYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productYear == product.productYear && Double.compare(price, product.price) == 0 && age == product.age && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productYear, price, url, age);
    }
}
