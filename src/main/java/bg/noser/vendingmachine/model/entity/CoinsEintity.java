package bg.noser.vendingmachine.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coins")
public class CoinsEintity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    private Double value;

    public Long getId() {
        return id;
    }

    public CoinsEintity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public CoinsEintity setName(String name) {
        Name = name;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public CoinsEintity setValue(Double value) {
        this.value = value;
        return this;
    }
}
