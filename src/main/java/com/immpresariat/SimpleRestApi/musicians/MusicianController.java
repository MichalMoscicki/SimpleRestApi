package com.immpresariat.SimpleRestApi.musicians;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/musicians")
public class MusicianController {

    @Autowired
    MusicianRepository musicianRepository;


    @GetMapping("")
    public ResponseEntity<List<Musician>> findAll() {
        List<Musician> musicians = musicianRepository.findAll();
        return new ResponseEntity<>(musicians, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Musician> findById(@PathVariable Long id) {
        Optional<Musician> musicianOptional = musicianRepository.findById(id);
        return new ResponseEntity<>(musicianOptional.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Musician> add(@RequestBody @Valid Musician musician) {
            musicianRepository.save(musician);
            return new ResponseEntity<>(musician, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Musician> update(@RequestBody Musician musicianUpdated, @PathVariable Long id) {

            Musician musician = musicianRepository.findById(id).get();
            musician.update(musicianUpdated);
            musicianRepository.save(musician);

        return new ResponseEntity<>(musician, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        musicianRepository.deleteById(id);
        return ResponseEntity.ok("Succesfully deleted musician with id: " + id);
    }


}
