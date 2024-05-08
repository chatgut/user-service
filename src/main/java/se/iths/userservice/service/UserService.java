package se.iths.userservice.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.userservice.dto.UserDTO;
import se.iths.userservice.entities.UserEntity;
import se.iths.userservice.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = convertToUserEntity(userDTO);
        userRepository.save(userEntity);
    }

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return convertToUserDTO(userEntity);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElse(null);
        if (userEntity == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        userEntity = convertToUserEntity(userDTO);
        userRepository.save(userEntity);
        return convertToUserDTO(userEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserEntity convertToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setImageUrl(userDTO.getImageUrl());
        return userEntity;
    }

    private UserDTO convertToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setImageUrl(userEntity.getImageUrl());
        return userDTO;
    }
}
