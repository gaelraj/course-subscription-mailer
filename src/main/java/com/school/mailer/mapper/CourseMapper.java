package com.school.mailer.mapper;

import com.school.mailer.dto.request.CreateCourseRequest;
import com.school.mailer.dto.response.CourseResponse;
import com.school.mailer.model.Course;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper {

  private final UserMapper userMapper;

  public Course toEntity(CreateCourseRequest request) {
    Course course = new Course();

    course.setTitle(request.title());
    course.setStart(request.start());
    course.setEnd(request.end());
    course.setSubscribers(new ArrayList<>());

    return course;
  }

  public CourseResponse toResponse(Course course) {
    return new CourseResponse(
        course.getId(),
        course.getTitle(),
        course.getStart(),
        course.getEnd(),
        course.getSubscribers().stream().map(userMapper::toResponse).toList());
  }
}
