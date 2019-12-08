package com.mpost.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@DynamicUpdate
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @NotEmpty
    private String houseNo;
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @Size(min = 2, message = "locality must not be null")
    private String locality;
    @NotNull
    @Size(min=2, message = "city must not be null")
    private String city;
    @NotNull
    @Size(min = 2, message = "state must not be null")
    private String state;
    @NotNull
    @Size(min =2, message = "country must not be null")
    private String country;
    private String landmark;
    private String gpsLocation;
    @NotNull
    @Size(min=6, max = 6, message = "pin must not be 6 digits")
    private String pin;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    @JsonIgnore
    private User user;

    public Address(){

    }

    public Address(String houseNo, String street, String locality, String city, String state, String country, String landmark, String gpsLocation, String pin) {
        this.houseNo = houseNo;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.landmark = landmark;
        this.gpsLocation = gpsLocation;
        this.pin = pin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", houseNo='" + houseNo + '\'' +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", landmark='" + landmark + '\'' +
                ", gpsLocation='" + gpsLocation + '\'' +
                ", pin=" + pin +
                '}';
    }
}
