package co.istad.elearning.exception;

public record FieldErrorResponse(
        String filed,
        String reason
) {
}