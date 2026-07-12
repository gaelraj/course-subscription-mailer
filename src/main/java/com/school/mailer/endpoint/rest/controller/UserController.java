package com.school.mailer.endpoint.rest.controller;

import com.school.mailer.dto.request.CreateUserRequest;
import com.school.mailer.dto.response.UserResponse;
import com.school.mailer.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/users")
  public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
    return userService.create(request);
  }
}
