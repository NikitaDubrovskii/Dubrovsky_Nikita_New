package dev.dubrovsky.model.bonus;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class UserBonusId {

    private Integer userId;
    private Integer bonusId;

    public UserBonusId(Integer userId, Integer bonusId) {
        this.userId = userId;
        this.bonusId = bonusId;
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
