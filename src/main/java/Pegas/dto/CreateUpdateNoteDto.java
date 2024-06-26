package Pegas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Value
public class CreateUpdateNoteDto {
    @NotBlank
            @Size(min=3, max=125)
    String title;
    @NotBlank
    String info;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
