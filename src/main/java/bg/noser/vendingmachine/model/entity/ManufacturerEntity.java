package bg.noser.vendingmachine.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manufacturers")
public class ManufacturerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<ProductEntity> products;

    public Long getId() {
        return id;
    }

    public ManufacturerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ManufacturerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public ManufacturerEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return "ManufacturerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}