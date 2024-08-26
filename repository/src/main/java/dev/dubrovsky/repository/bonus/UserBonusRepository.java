package dev.dubrovsky.repository.bonus;

import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.model.bonus.UserBonusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBonusRepository extends JpaRepository<UserBonus, UserBonusId> {



}
