package co.istad.elearning.features.course;

import co.istad.elearning.features.category.Category;
import co.istad.elearning.features.category.CategoryRepository;
import co.istad.elearning.features.course.dto.CourseResponse;
import co.istad.elearning.features.course.dto.CreateCourseRequest;
import co.istad.elearning.features.course.mapper.CourseMapper;
import co.istad.elearning.features.instructor.Instructor;
import co.istad.elearning.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CourseResponse> getAllCourses(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(courseMapper::mapToResponse);
    }

    @Override
    public CourseResponse getCourseBySlug(String slug) {

        return null;
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {

        if (courseRepository.existsBySlug(createCourseRequest.slug())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Slug has already been used");
        }

        Category category = categoryRepository.findById(createCourseRequest.categoryID())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category Id not found"
                ));

        Course course = courseMapper.mapToEntity(createCourseRequest);
        course.setCategory(category);
        course.setIsDeleted(false);
        course.setIsPublished(false);
        course.setCountRating(0);

        course.setInstructorProfile(new Instructor(AuthUtils.extractUserId()));

        courseRepository.save(course);

        return courseMapper.mapToResponse(course);
    }

    @Override
    public CourseResponse patchCourseBySlug(String slug, CreateCourseRequest createCourseRequest) {
        return null;
    }

    @Override
    public void deleteCourseBySlug(String slug) {

    }
}
