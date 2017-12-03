package ua.shield.entity;

import java.util.List;
import java.util.Set;

/**
 * Created by sa on 30.11.17.
 */
public class Hospital {
    private int id;
    private String name;
    private String address;
    private District district;
    private Set<String> phones;
    private Set<Doctor> doctors;
    private Set<HospitalAdmin> hospitalAdmins;
    private List<News> newsList;
}
