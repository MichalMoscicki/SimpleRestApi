package com.immpresariat.SimpleRestApi.musicians.events;

import com.immpresariat.SimpleRestApi.musicians.musicians.Musician;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime time;
    private String address;
    private String description;

    //-------------- to w dalszej części poradnika ---------------//
//    @OneToMany
//    private Set<Musician> musicians;



}
