package com.school.mailer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "The first name is mandatory")
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotBlank(message = "The last name is mandatory")
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @NotBlank(message = "The username is mandatory")
  @Column(name = "username", nullable = false, unique = true)
  private String userName;

  @NotBlank(message = "The email is mandatory")
  @Email(message = "The email must be valid")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @ManyToMany(mappedBy = "subscribers")
  @JsonIgnore
  private List<Course> courses;
}
