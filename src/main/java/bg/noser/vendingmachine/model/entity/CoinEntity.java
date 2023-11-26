package bg.noser.vendingmachine.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coins")
public class CoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    public Long getId() {
        return id;
    }

    public CoinEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public CoinEntity setValue(Double value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "CoinEintity{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
