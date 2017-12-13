package ua.shield.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    private District district;

    @Transient
    private Set<String> phones;
    @Transient
    private Set<Doctor> doctors;
    @Transient
    private Set<HospitalAdmin> hospitalAdmins;
    @Transient
    private List<News> newsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<HospitalAdmin> getHospitalAdmins() {
        return hospitalAdmins;
    }

    public void setHospitalAdmins(Set<HospitalAdmin> hospitalAdmins) {
        this.hospitalAdmins = hospitalAdmins;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospital hospital = (Hospital) o;

        return name != null ? name.equals(hospital.name) : hospital.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
