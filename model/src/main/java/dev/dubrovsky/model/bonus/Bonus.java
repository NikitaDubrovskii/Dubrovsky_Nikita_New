package dev.dubrovsky.model.bonus;

import dev.dubrovsky.dto.response.bonus.BonusResponse;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bonuses")
@Getter
@Setter
@NoArgsConstructor
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private LoyaltyProgram program;

    @ManyToMany
    @JoinTable(
            name = "user_bonuses",
            joinColumns = {@JoinColumn(name = "bonus_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    public Bonus(String name, String description, Integer points, LoyaltyProgram program) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.program = program;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(id, bonus.id) && Objects.equals(name, bonus.name)
                && Objects.equals(description, bonus.description) && Objects.equals(points, bonus.points)
                && Objects.equals(program, bonus.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, points, program);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", programId=" + program +
                '}';
    }

    public BonusResponse mapToResponse() {
        return new BonusResponse(id, name, description, points, program.mapToResponse());
    }

}
