package co.istad.elearning.features.instructor;

import co.istad.elearning.features.course.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "instructor_profiles")
public class Instructor {


    public Instructor(String userId) {
        this.userId = userId;
    }

    @Id
    private String userId;

    private String biography;
    private String jobTitle;
    private String phoneNumber;
    private String githubLink;
    private String facebookLink;

    @OneToMany(mappedBy = "instructorProfile")
    private List<Course> courses;
}
