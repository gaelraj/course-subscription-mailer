package com.school.mailer.service;

import com.school.mailer.dto.request.CreateUserRequest;
import com.school.mailer.dto.response.UserResponse;
import com.school.mailer.mapper.UserMapper;
import com.school.mailer.model.User;
import com.school.mailer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse create(CreateUserRequest request) {
    User user = userMapper.toEntity(request);

    User savedUser = userRepository.save(user);

    return userMapper.toResponse(savedUser);
  }
}
