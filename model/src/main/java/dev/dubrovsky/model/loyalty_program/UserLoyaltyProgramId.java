package dev.dubrovsky.model.loyalty_program;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class UserLoyaltyProgramId {

    private Integer userId;
    private Integer programId;

    public UserLoyaltyProgramId() {
    }

    public UserLoyaltyProgramId(Integer userId, Integer programId) {
        this.userId = userId;
        this.programId = programId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getProgramId() {
        return programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoyaltyProgramId that = (UserLoyaltyProgramId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(programId, that.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, programId);
    }

    @Override
    public String toString() {
        return "UserLoyaltyProgramId{" +
                "userId=" + userId +
                ", programId=" + programId +
                '}';
    }

}