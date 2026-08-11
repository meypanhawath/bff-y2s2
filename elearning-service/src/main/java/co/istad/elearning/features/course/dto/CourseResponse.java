package co.istad.elearning.features.course.dto;

import co.istad.elearning.features.category.dto.CategoryResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
public record CourseResponse(
        String slug,
        String keyword,
        String title,
        String description,
        String thumbnail,
        Float starRating,
        Integer countRating,
        Float totalHours,
        String level,
        BigDecimal price,
        Float discountPercent,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        CategoryResponse category,
        String instructorName,
        Boolean isPublished
) {
}
