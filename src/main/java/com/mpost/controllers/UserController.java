package com.mpost.controllers;

import com.mpost.exceptions.UserNotFoundException;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

        return "init called!";
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
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
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id:" + id));
        return user;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteAllUser(){
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @Valid @RequestBody User user){
        User existingUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAadhaarNo(user.getAadhaarNo());
        existingUser.setAlternateNo(user.getAlternateNo());
        existingUser.setPhoneNo(user.getPhoneNo());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users/{userId}/address")
    public ResponseEntity updateAddress(@PathVariable int userId, @Valid @RequestBody Address address){
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("id: "+userId));
        existingUser.setAddress(address);
        User savedUser =userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users/{id}/address")
    public Address findAddress(@PathVariable int id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id)).getAddress();
    }

}
