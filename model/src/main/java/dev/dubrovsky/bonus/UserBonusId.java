package dev.dubrovsky.bonus;

import java.util.Objects;

public class UserBonusId {

    private final Integer userId;
    private final Integer bonusId;

    public UserBonusId(Integer userId, Integer bonusId) {
        this.userId = userId;
        this.bonusId = bonusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getBonusId() {
        return bonusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBonusId that = (UserBonusId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(bonusId, that.bonusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bonusId);
    }

}
