package com.school.mailer.service;

import com.school.mailer.dto.request.CreateCourseRequest;
import com.school.mailer.dto.response.CourseResponse;
import com.school.mailer.endpoint.event.EventProducer;
import com.school.mailer.endpoint.event.model.SubscriptionConfirmed;
import com.school.mailer.exception.ConflictException;
import com.school.mailer.exception.NotFoundException;
import com.school.mailer.mapper.CourseMapper;
import com.school.mailer.model.Course;
import com.school.mailer.repository.CourseRepository;
import com.school.mailer.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final UserRepository userRepository;
  private final CourseMapper courseMapper;
  private final EventProducer<SubscriptionConfirmed> eventProducer;

  @Transactional
  public CourseResponse create(CreateCourseRequest request) {
    Course course = courseMapper.toEntity(request);

    Course savedCourse = courseRepository.save(course);

    return courseMapper.toResponse(savedCourse);
  }

  @Transactional
  public CourseResponse subscribe(UUID courseId, UUID userId) {
    var course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new NotFoundException("Course not found"));

    var user =
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

    boolean alreadySubscribed =
        course.getSubscribers().stream().anyMatch(subscriber -> subscriber.getId().equals(userId));

    if (alreadySubscribed) {
      throw new ConflictException("User already subscribed to this course");
    }

    course.getSubscribers().add(user);

    Course savedCourse = courseRepository.save(course);

    var event =
        SubscriptionConfirmed.builder()
            .studentEmail(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .courseTitle(course.getTitle())
            .build();
    eventProducer.accept(List.of(event));

    return courseMapper.toResponse(savedCourse);
  }
}
