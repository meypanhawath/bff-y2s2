package co.istad.elearning.features.course.mapper;


import co.istad.elearning.features.course.Course;
import co.istad.elearning.features.course.dto.CourseResponse;
import co.istad.elearning.features.course.dto.CreateCourseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponse mapToResponse(Course course);
    Course mapToEntity(CreateCourseRequest createCourseRequest);
}
