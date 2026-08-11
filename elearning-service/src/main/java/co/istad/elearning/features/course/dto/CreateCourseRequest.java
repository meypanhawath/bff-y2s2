package co.istad.elearning.features.course.dto;

import co.istad.elearning.features.video.Video;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateCourseRequest(
        @NotBlank
        String slug,
        String keyword,

        @NotBlank
        String title,
        String description,
        String thumbnail,
        @Positive
        @NotNull
        Float totalHour,
        @NotBlank
        @Size(max = 50)
        String level,
        @NotNull
        @Positive
        BigDecimal price,
        @Positive
        Float discountPercent,

        //
        @Positive
        @NotNull
        Integer categoryID
) {
}
