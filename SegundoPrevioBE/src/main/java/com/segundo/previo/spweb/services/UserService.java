package com.segundo.previo.spweb.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segundo.previo.spweb.entities.User;
import com.segundo.previo.spweb.repositories.UserRepository;
import com.segundo.previo.spweb.shared.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);

        User savedUser = userRepository.save(newUser);

        UserDTO savedUserDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, savedUserDTO);
        return savedUserDTO;
    }

    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BeanUtils.copyProperties(userDTO, foundUser);
        foundUser.setId(userId);
        User updatedUser = userRepository.save(foundUser);
        UserDTO updatedUserDTO = new UserDTO();
        BeanUtils.copyProperties(updatedUser, updatedUserDTO);
        return updatedUserDTO;
    }

    public void deleteUser(Integer userId){
         User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(foundUser);

    }

    public UserDTO getUserById(Integer userId) {
      UserDTO userDTO = new UserDTO();
        User userOptional= userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                 
        BeanUtils.copyProperties(userOptional,userDTO  );
        return userDTO;
    }
     
     /**
     * @return
     */

     public List<UserDTO>listUser(){
        List<User>users=userRepository.findAll();
        return users.stream()
        .map(this::converEntityToDTO)
        .collect(Collectors.toList());
             
     }
     private UserDTO converEntityToDTO(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
     }
}
