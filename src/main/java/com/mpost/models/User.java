package com.mpost.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MyUser")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 2, message = "First name should have at least 2 or more characters")
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 10, max = 10, message = "Phone no must have 10 digits")
    private String phoneNo;
    @Size(min = 10, max = 10, message = "Alternate no must have 10 digits")
    private String alternateNo;
    @Size(min = 12, max = 12, message = "Aadhaar no must have 12 digits")
    private String aadhaarNo;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    public User(){

    }

    public User(Integer id, String firstName, String lastName, String email, String phoneNo, String alternateNo, String aadhaarNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.alternateNo = alternateNo;
        this.aadhaarNo = aadhaarNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAlternateNo() {
        return alternateNo;
    }

    public void setAlternateNo(String alternateNo) {
        this.alternateNo = alternateNo;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", alternateNo='" + alternateNo + '\'' +
                ", aadhaarNo='" + aadhaarNo + '\'' +
                '}';
    }
}
