package Pegas.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ReadNoteDto {
    Long id;
    String title;
    String body;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
