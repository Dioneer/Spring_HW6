package Pegas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class FilterNoteDto {
    @NotBlank
    @Size(min=3, max=125)
    String title;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
