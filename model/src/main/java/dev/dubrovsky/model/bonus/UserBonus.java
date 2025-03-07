package dev.dubrovsky.model.bonus;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_bonuses")
@Getter
@Setter
@NoArgsConstructor
public class UserBonus {

    @EmbeddedId
    private UserBonusId userBonusId;

    @Column(name = "received_at", updatable = false)
    private LocalDateTime receivedAt;

    public UserBonus(UserBonusId userBonusId) {
        this.userBonusId = userBonusId;
        this.receivedAt = LocalDateTime.now();
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
                "userId=" + userBonusId.getUserId() +
                ", bonusId=" + userBonusId.getBonusId() +
                ", receivedAt=" + receivedAt +
                '}';
    }

}
