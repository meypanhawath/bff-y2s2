package co.istad.elearning.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse (
        Integer id,
        String name,
        String icon
) {
}
