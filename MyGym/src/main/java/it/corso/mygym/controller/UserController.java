package it.corso.mygym.controller;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDTO;


import it.corso.mygym.model.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users")
public interface UserController {


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    ResponseEntity<User> save(@RequestBody UserDTO userDTO);

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable("id") Long id) throws UserNotFoundException;

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/findAll")
    ResponseEntity<List<User>> findAll(boolean includeInactiveFlag);

    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO userDTO) throws UserNotFoundException;

    @DeleteMapping("/{id}")
    ResponseEntity <User> deleteById(@PathVariable("id") Long id) throws UserNotFoundException;
}