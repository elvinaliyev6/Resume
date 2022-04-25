package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceInter userService;

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//        return  ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//        return  ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id) {
        User user = userService.getById(id);

        List<UserDTO> userDTOS = new ArrayList<>();

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
//        return  ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        userService.removeUser(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully deleted"));
//        return  ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        userService.addUser(user);

        UserDTO userDTO1=new UserDTO();
        userDTO1.setId(user.getId());
        userDTO1.setName(user.getName());
        userDTO1.setSurname(user.getSurname());
        return ResponseEntity.ok(ResponseDTO.of(userDTO1, "Successfully added"));
    }

}
