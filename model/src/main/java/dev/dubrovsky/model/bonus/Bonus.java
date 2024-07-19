package dev.dubrovsky.model.bonus;

import java.util.Objects;

public class Bonus {

    private final Integer id;
    private final String name;
    private final String description;
    private final Integer points;
    private final Integer programId;

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