package bg.noser.vendingmachine.model.dto;

public class ManufacturerDTO {

    private String name;

    public String getName() {
        return name;
    }

    public ManufacturerDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
