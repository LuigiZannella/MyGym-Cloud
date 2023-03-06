package it.corso.mygym.service;

import it.corso.mygym.model.Constants;
import it.corso.mygym.model.User;
import it.corso.mygym.dao.UserRepository;
import it.corso.mygym.model.dto.UserDTO;
import it.corso.mygym.model.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    private void validateExists(Long id) {
        if (repo.findById(id).isEmpty()) throw new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION, id);
    }

    @Override
    public User save(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = (modelMapper.map(userDTO,User.class));
        return repo.save(user);
    }

    @Override
    public User findById(Long id) {
        validateExists(id);
        Optional<User> optionalUser = repo.findById(id);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        } else throw new ResourceNotFoundException();
    }

    @Override
    public List<User> findAll(boolean includeInactiveFlag) {
        if(includeInactiveFlag)
            return repo.findAll();
        else
            return repo.findByActivateTrue();
    }

    @Override
    public User update(Long id, UserDTO userDTO) {
        Optional<User> userOld = repo.findById(id);
        userDTO.setId(id);

        if(userOld.isPresent()){
            copyNonNullProperties(userDTO, userOld.get());
            userDTO.setId(id);

            return repo.saveAndFlush(userOld.get());
        } else throw new ResourceNotFoundException();
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> deleteUser = repo.findById(id);

        if (deleteUser.isPresent()) {
            User user = deleteUser.get();

            // hard-delete
            repo.deleteById(id);
            return null;

            // soft-delete
            // user.setActivate(false);
            // return repo.save(user);
        } else throw new ResourceNotFoundException();
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }
}