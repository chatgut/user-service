package se.iths.userservice.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.userservice.dto.UserDTO;
import se.iths.userservice.entities.UserEntity;
import se.iths.userservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        userRepository.save(convertToUserEntity(userDTO));
    }

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return convertToUserDTO(userEntity);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        updateUserEntity(userEntity, userDTO);
        userRepository.save(userEntity);
        return convertToUserDTO(userEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(this::convertToUserDTO).toList();
    }

    private void updateUserEntity(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setImageUrl(userDTO.getImageUrl());
    }

    private UserEntity convertToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        updateUserEntity(userEntity, userDTO);
        return userEntity;
    }

    private UserDTO convertToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        updateUserDTOFields(userDTO, userEntity);
        return userDTO;
    }

    private void updateUserDTOFields(UserDTO userDTO, UserEntity userEntity) {
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setImageUrl(userEntity.getImageUrl());
    }
}
