package dev.dubrovsky.repository.analytics;

import dev.dubrovsky.model.analytics.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Integer> {

}
