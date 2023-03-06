package it.corso.mygym.controller;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDTO;
import it.corso.mygym.model.exception.UserNotFoundException;
import it.corso.mygym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

@Autowired
    private UserService userService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody UserDTO UserDTO) {
        User userSaved = userService.save(UserDTO);

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @Override
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity <User> findById(@PathVariable("id") Long id) throws UserNotFoundException {
        User userId = userService.findById(id);
        return new ResponseEntity<>(userId, HttpStatus.FOUND);
    }

    @Override
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll(boolean includeInactiveFlag) {
        List<User> users = userService.findAll(true);
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO userDTO) throws UserNotFoundException {
        User userUpdated = userService.update(id, userDTO);

        return ResponseEntity.ok(userUpdated);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity <User> deleteById(@PathVariable("id") Long id) throws UserNotFoundException {
        User users = userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
