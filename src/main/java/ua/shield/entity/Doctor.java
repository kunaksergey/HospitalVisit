package ua.shield.entity;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 30.11.17.
 */
public class Doctor extends User {
    private Specialization specialization;
    private Set<Hospital> hospitalSet;
    private Set<Schedule> schedules;
    private Set<Ticket> tickets;
    private List<News> newsList;
}
