package it.uniroma3.siw.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectionDto {

    private String name;

    private String description;

    private Long curatorId;
}
