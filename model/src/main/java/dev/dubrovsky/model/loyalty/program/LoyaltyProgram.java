package dev.dubrovsky.model.loyalty.program;

import dev.dubrovsky.dto.response.loyalty.program.LoyaltyProgramResponse;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "loyalty_programs")
@Getter
@Setter
@NoArgsConstructor
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "user_loyalty_programs",
            joinColumns = { @JoinColumn(name = "program_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;

    @OneToMany(mappedBy = "program")
    private List<Bonus> bonuses;

    public LoyaltyProgram(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoyaltyProgram that = (LoyaltyProgram) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(description, that.description) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt);
    }

    @Override
    public String toString() {
        return "LoyaltyProgram{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public LoyaltyProgramResponse mapToResponse() {
        return new LoyaltyProgramResponse(id, name, description, createdAt);
    }

}
