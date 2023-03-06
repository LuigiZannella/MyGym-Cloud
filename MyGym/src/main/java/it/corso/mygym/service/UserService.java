package it.corso.mygym.service;

import it.corso.mygym.model.User;
import it.corso.mygym.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User save(UserDTO userDTO);

    User findById(Long id);

    List<User> findAll(boolean includeInactiveFlag);


    User update(Long id, UserDTO userDTO);

    User deleteById(Long id);
}
