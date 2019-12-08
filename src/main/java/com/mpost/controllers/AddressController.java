package com.mpost.controllers;

import com.mpost.models.Address;
import com.mpost.models.User;
import com.mpost.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public List<Address> findAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public boolean addAddress(User savedUser, Address address) {
        address.setUser(savedUser);
        addressRepository.save(address);
        return true;
    }
}
