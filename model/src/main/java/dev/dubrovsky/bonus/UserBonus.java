package dev.dubrovsky.bonus;

import java.sql.Timestamp;
import java.util.Objects;

public class UserBonus {

    private final UserBonusId userBonusId;
    private final Timestamp receivedAt;

    public UserBonus(UserBonusId userBonusId, Timestamp receivedAt) {
        this.userBonusId = userBonusId;
        this.receivedAt = receivedAt;
    }

    public UserBonusId getUserBonusId() {
        return userBonusId;
    }

    public Timestamp getReceivedAt() {
        return receivedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBonus userBonus = (UserBonus) o;
        return Objects.equals(userBonusId, userBonus.userBonusId) && Objects.equals(receivedAt, userBonus.receivedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userBonusId, receivedAt);
    }

    @Override
    public String toString() {
        return "UserBonus{" +
                "userBonusId=" + userBonusId +
                ", receivedAt=" + receivedAt +
                '}';
    }

}
