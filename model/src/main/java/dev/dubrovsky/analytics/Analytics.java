package dev.dubrovsky.analytics;

import java.sql.Timestamp;
import java.util.Objects;

public class Analytics {

    private final Integer id;
    private final String activity;
    private final Timestamp timestamp;
    private final Integer userId;

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