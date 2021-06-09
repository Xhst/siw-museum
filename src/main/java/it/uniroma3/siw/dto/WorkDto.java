package it.uniroma3.siw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkDto {

    private String title;

    private String imageUrl;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfRealization;

    private Long artistId;

    private Long collectionId;
}
