package com.school.mailer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "The title is mandatory")
  @Column(name = "title", nullable = false)
  private String title;

  @NotNull(message = "The start is mandatory")
  @Column(name = "start_date", nullable = false)
  private Instant start;

  @NotNull(message = "The end is mandatory")
  @Column(name = "end_date", nullable = false)
  private Instant end;

  @ManyToMany
  @JoinTable(
      name = "course_subscription",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> subscribers;
}
