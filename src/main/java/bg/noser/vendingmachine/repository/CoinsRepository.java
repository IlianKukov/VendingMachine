package bg.noser.vendingmachine.repository;

import bg.noser.vendingmachine.model.entity.CoinsEintity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinsRepository extends JpaRepository<CoinsEintity, Long> {
}
