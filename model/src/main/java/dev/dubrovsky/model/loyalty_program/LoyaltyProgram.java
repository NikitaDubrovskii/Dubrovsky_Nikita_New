package dev.dubrovsky.model.loyalty_program;

import java.sql.Timestamp;
import java.util.Objects;

public class LoyaltyProgram {

    private final Integer id;
    private final String name;
    private final String description;
    private final Timestamp createdAt;

    public LoyaltyProgram(Integer id, String name, String description, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
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

}
