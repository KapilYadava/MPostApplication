package com.mpost.controllers;

import com.mpost.models.Address;
import com.mpost.models.User;
import com.mpost.repositories.AddressRepository;
import com.mpost.repositories.UserRepository;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/init")
    public String init(){
        Address address = new Address("36, prateeksha","14th Cross, Pragthi Layout",
                "Bhuvneshwari Nager, Kempapura" , "Bangalore", "Karnataka",
                "India", "Ganaga Bakery", "null", "560024");
        User user = new User();
        user.setFirstName("Kapil");
        user.setLastName("Yadav");
        user.setAlternateNo("9718313865");
        user.setPhoneNo("9718313865");
        user.setAddress(address);
        user.setEmail("kapilyadava.isa@gmail.com");
        user.setAadhaarNo("12345678901234");
        User savedUser = userRepository.save(user);
        address.setUser(savedUser);
        addressRepository.save(address);

        return "init called!";
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User savedUser = userRepository.save(user);
        Address address = user.getAddress();
        address.setUser(savedUser);
        addressRepository.save(address);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users;

    }
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id){
        Optional user = userRepository.findById(id);
        return (User) user.get();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        addressRepository.deleteByUserId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteAllUser(){
        userRepository.deleteAll();
        addressRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user){
        User existingUser = userRepository.findById(id).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAadhaarNo(user.getAadhaarNo());
        existingUser.setAlternateNo(user.getAlternateNo());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setEmail(user.getEmail());
        //existingUser.setAddress(user.getAddress());
        User savedUser = userRepository.save(existingUser);
//        Address address = addressRepository.findByUserId(id).get(0);
//        address.setHouseNo(user.getAddress().getHouseNo());
//        address.setStreet(user.getAddress().getStreet());
//        address.setLocality(user.getAddress().getLocality());
//        address.setCity(user.getAddress().getCity());
//        address.setState(user.getAddress().getState());
//        address.setCountry(user.getAddress().getCountry());
//        address.setPin(user.getAddress().getPin());
//        address.setLandmark(user.getAddress().getLandmark());
//        address.setGpsLocation(user.getAddress().getGpsLocation());
//        address.setUser(savedUser);
//        addressRepository.save(address);
        updateAddress(id, user.getAddress());
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users/{userId}/address")
    public ResponseEntity updateAddress(@PathVariable int userId, @RequestBody Address address){
        Address existingAddress = addressRepository.findByUserId(userId).get(0);
        existingAddress.setHouseNo(address.getHouseNo());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setLocality(address.getLocality());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setPin(address.getPin());
        existingAddress.setLandmark(address.getLandmark());
        existingAddress.setGpsLocation(address.getGpsLocation());
        existingAddress.setUser(userRepository.findById(userId).get());
        addressRepository.save(existingAddress);
        return ResponseEntity.ok(existingAddress);
    }

    @GetMapping("/users/{id}/address")
    public List<Address> findAddress(@PathVariable int id){
        return addressRepository.findByUserId(id);
    }

}
