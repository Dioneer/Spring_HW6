package Pegas.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreateUpdateNoteDto {
    String title;
    String body;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
