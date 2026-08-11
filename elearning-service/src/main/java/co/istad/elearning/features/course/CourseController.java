package co.istad.elearning.features.course;

import co.istad.elearning.features.course.dto.CourseResponse;
import co.istad.elearning.features.course.dto.CreateCourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<CourseResponse> getAllCourses (@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "20") Integer pageSize){
        return courseService.getAllCourses(pageNumber, pageSize);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody CreateCourseRequest createCourseRequest){
        return courseService.createCourse(createCourseRequest);
    }
}
