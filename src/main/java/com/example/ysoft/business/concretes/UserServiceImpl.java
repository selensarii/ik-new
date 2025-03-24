package com.example.ysoft.business.concretes;

import com.example.ysoft.business.abstracts.UserService;
import com.example.ysoft.business.dtos.requests.user.CreateUserRequestDTO;
import com.example.ysoft.business.dtos.requests.user.UpdateUserRequestDTO;
import com.example.ysoft.business.dtos.responses.UserResponseDto;
import com.example.ysoft.business.dtos.responses.user.CreateUserResponseDTO;
import com.example.ysoft.business.dtos.responses.user.UpdateUserResponseDTO;
import com.example.ysoft.core.mapper.MapperService;
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
    private final MapperService mapperService;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(mapperService::toResponsed).toList();
    }

    @Override
    public CreateUserResponseDTO addUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = mapperService.toUserEntity(createUserRequestDTO);
        User savedUser = userRepository.save(user);
        return mapperService.toUserResponse(savedUser);
    }

    @Override
    public UserResponseDto getById(String id) {
        UUID userId = UUID.fromString(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));
        return mapperService.toResponsed(user);
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
                .map(mapperService::toUpdateUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı."));
    }

    @Override
    public void deleteUser(String id) {
        UUID userId = UUID.fromString(id);
        userRepository.deleteById(userId);
    }


}
