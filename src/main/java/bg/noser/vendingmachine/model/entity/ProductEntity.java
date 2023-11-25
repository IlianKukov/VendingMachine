package bg.noser.vendingmachine.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double price;
    @ManyToOne
    private ManufacturerEntity manufacturer;


    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public ProductEntity setType(String type) {
        this.type = type;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ManufacturerEntity getManufacturer() {
        return manufacturer;
    }

    public ProductEntity setManufacturer(ManufacturerEntity manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer=" + (manufacturer != null ? manufacturer.getName() : null) +
                '}';
    }
}