package co.istad.elearning.features.course;

import co.istad.elearning.features.course.dto.CourseResponse;
import co.istad.elearning.features.course.dto.CreateCourseRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.jwt.Jwt;

public interface CourseService {

    Page<CourseResponse> getAllCourses(Integer pageNumber, Integer pageSize);
    CourseResponse getCourseBySlug (String slug);
    CourseResponse createCourse(CreateCourseRequest createCourseRequest);
    CourseResponse patchCourseBySlug(String slug, CreateCourseRequest createCourseRequest);
    void deleteCourseBySlug(String slug);
}
