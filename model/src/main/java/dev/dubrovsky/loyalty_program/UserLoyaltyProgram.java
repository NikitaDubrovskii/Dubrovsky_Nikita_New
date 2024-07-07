package dev.dubrovsky.loyalty_program;

import java.sql.Timestamp;
import java.util.Objects;

public class UserLoyaltyProgram {

    private final UserLoyaltyProgramId userLoyaltyProgramId;
    private final Timestamp receivedAt;

    public UserLoyaltyProgram(UserLoyaltyProgramId userLoyaltyProgramId, Timestamp receivedAt) {
        this.userLoyaltyProgramId = userLoyaltyProgramId;
        this.receivedAt = receivedAt;
    }

    public UserLoyaltyProgramId getUserLoyaltyProgramId() {
        return userLoyaltyProgramId;
    }

    public Timestamp getReceivedAt() {
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