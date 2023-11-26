package bg.noser.vendingmachine.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class CoinDTO {

    private Long id;

    private Double value;

    public Long getId() {
        return id;
    }

    public CoinDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public CoinDTO setValue(Double value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "CoinDTO{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
