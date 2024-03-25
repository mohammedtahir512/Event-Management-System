package com.projects.event_management_system.repositories;

import com.projects.event_management_system.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository

    public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.date BETWEEN :startDate AND :endDate ORDER BY e.date, calculate_distance(:latitude, :longitude, e.latitude, e.longitude)")
    List<Event> findEventsWithinDateRangeSortedByDateAndDistance(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("latitude") double latitude, @Param("longitude") double longitude);
    }



