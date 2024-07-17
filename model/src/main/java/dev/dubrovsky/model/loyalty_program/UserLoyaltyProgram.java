package dev.dubrovsky.model.loyalty_program;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_loyalty_programs")
public class UserLoyaltyProgram {

    @EmbeddedId
    private UserLoyaltyProgramId userLoyaltyProgramId;

    @Column(name = "received_at", updatable = false)
    private LocalDateTime receivedAt;

    public UserLoyaltyProgram() {
    }

    public UserLoyaltyProgram(UserLoyaltyProgramId userLoyaltyProgramId) {
        this.userLoyaltyProgramId = userLoyaltyProgramId;
        this.receivedAt = LocalDateTime.now();
    }

    public UserLoyaltyProgramId getUserLoyaltyProgramId() {
        return userLoyaltyProgramId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoyaltyProgram that = (UserLoyaltyProgram) o;
        return Objects.equals(userLoyaltyProgramId, that.userLoyaltyProgramId) && Objects.equals(receivedAt, that.receivedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLoyaltyProgramId, receivedAt);
    }

    @Override
    public String toString() {
        return "UserLoyaltyProgram{" +
                "userId=" + userLoyaltyProgramId.getUserId() +
                ", loyaltyProgramId=" + userLoyaltyProgramId.getProgramId() +
                ", receivedAt=" + receivedAt +
                '}';
    }

}