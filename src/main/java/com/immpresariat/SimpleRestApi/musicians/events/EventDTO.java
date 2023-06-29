package com.immpresariat.SimpleRestApi.musicians.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;
    private String name;
    private LocalDateTime time;
    private String address;
    private String description;
}
