package dev.dubrovsky.model.analytics;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "user_id")
    private Integer userId;

    public Analytics() {
    }

    public Analytics(Integer id, String activity, Timestamp timestamp, Integer userId) {
        this.id = id;
        this.activity = activity;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analytics analytics = (Analytics) o;
        return Objects.equals(id, analytics.id) && Objects.equals(activity, analytics.activity)
                && Objects.equals(timestamp, analytics.timestamp) && Objects.equals(userId, analytics.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, timestamp, userId);
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                '}';
    }

}