package dev.dubrovsky.repository.loyalty.program;

import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgramId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoyaltyProgramRepository extends JpaRepository<UserLoyaltyProgram, UserLoyaltyProgramId> {
}
