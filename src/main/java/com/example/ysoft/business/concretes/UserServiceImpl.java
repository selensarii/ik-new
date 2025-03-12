package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.dataAccess.UserRepository;
import com.example.ysoft.entities.User;
import com.example.ysoft.library.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public CreateUserResponseDTO addUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = toUserEntity(createUserRequestDTO);
        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }
    //dönüşüm
    private User toUserEntity(CreateUserRequestDTO dto) {
        return new User(dto.getNickName(), dto.getPassword());
    }

    private CreateUserResponseDTO toUserResponse(User user) {
        return new CreateUserResponseDTO(user.getId(), user.getNickName(), user.getPassword());
    }


    @Override
    public UserResponseDto getById(String id) {
        UUID userId = UUID.fromString(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));
        return toResponse(user);
    }

    @Override
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        UUID userId = updateUserRequestDTO.getId();

        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setNickName(updateUserRequestDTO.getNickName());
                    existingUser.setPassword(updateUserRequestDTO.getPassword());

                    return userRepository.save(existingUser);
                })
                .map(this::toUpdateUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı."));
    }


    @Override
    public void deleteUser(String id) {
        UUID userId = UUID.fromString(id);
        userRepository.deleteById(userId);
    }

    private UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId().toString())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .build();
    }
    private UpdateUserResponseDTO toUpdateUserResponse(User user) {
        return new UpdateUserResponseDTO(
                user.getId(),
                user.getNickName(),
                user.getPassword()
        );
    }

}
