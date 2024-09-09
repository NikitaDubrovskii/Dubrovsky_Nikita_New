package dev.dubrovsky.repository.bonus;

import dev.dubrovsky.model.bonus.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Integer> {
}
