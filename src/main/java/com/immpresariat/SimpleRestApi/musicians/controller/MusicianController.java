package com.immpresariat.SimpleRestApi.musicians.controller;

import com.immpresariat.SimpleRestApi.musicians.model.Musician;
import com.immpresariat.SimpleRestApi.musicians.payload.MusicianDTO;
import com.immpresariat.SimpleRestApi.musicians.service.MusicianService;
import com.immpresariat.SimpleRestApi.musicians.service.impl.MusicianServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/musicians")
public class MusicianController {
    MusicianService musicianService;

    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

        @GetMapping("")
    public ResponseEntity<List<MusicianDTO>> findAll() {
        List<MusicianDTO> musiciansDto = musicianService.findAll();
        return new ResponseEntity<>(musiciansDto, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicianDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(musicianService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MusicianDTO> createMusician(@RequestBody MusicianDTO musicianDto) {
            return new ResponseEntity<>(musicianService.createMusician(musicianDto),
                    HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicianDTO> update(@RequestBody MusicianDTO musicianDTO, @PathVariable Long id) {
        return new ResponseEntity<>(musicianService.updateMusician(musicianDTO, id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        musicianService.deleteMusicianById(id);
        return ResponseEntity.ok("Successfully deleted musician with id: " + id);
    }


}
