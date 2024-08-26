package dev.dubrovsky.repository.loyalty.program;

import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Integer> {
}
