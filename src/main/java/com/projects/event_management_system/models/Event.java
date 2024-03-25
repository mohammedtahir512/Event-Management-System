package com.projects.event_management_system.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String event_name;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private String time;

    @Column(name = "latitude") // Match the column name in the database
    private double latitude;

    @Column(name = "longitude") // Match the column name in the database
    private double longitude;
}
