package bg.noser.vendingmachine.repository;

import bg.noser.vendingmachine.model.entity.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
//Optional<ManufacturerEntity> findManufacturerEntityByName(String name);
}


