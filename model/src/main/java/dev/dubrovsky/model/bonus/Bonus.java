package dev.dubrovsky.model.bonus;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "bonuses")
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

    @Column(name = "program_id")
    private Integer programId;

    public Bonus() {
    }

    public Bonus(Integer id, String name, String description, Integer points, Integer programId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
        this.programId = programId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getProgramId() {
        return programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(id, bonus.id) && Objects.equals(name, bonus.name)
                && Objects.equals(description, bonus.description) && Objects.equals(points, bonus.points)
                && Objects.equals(programId, bonus.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, points, programId);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", programId=" + programId +
                '}';
    }

}