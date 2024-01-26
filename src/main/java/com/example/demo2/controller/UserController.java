package com.example.demo2.controller;

import com.example.demo2.model.Post;
import com.example.demo2.model.User;
import com.example.demo2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @GetMapping
    public ResponseEntity showListUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> createUsers(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody User user , @PathVariable Long id) {
        user.setId(id);
        return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity("xoa thanh cong", HttpStatus.OK);
    }
}
