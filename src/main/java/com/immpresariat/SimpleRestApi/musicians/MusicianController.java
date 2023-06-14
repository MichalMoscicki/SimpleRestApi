package com.immpresariat.SimpleRestApi.musicians;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musician")
public class MusicianController {

    @Autowired
    MusicianRepository musicianRepository;

    @GetMapping("")
    public List<Musician> findAll(){
        return musicianRepository.findAll();
    }

    @GetMapping("/{id}")
    public Musician findById(@PathVariable Long id){
        return musicianRepository.findById(id).get();
    }

    @PostMapping("")
    public int add(@RequestBody Musician musician){
        try{
            musicianRepository.save(musician);
        } catch (Exception e){
            e.printStackTrace();
            //tu można sprawdzić, co nie działa i wyrzucić czego brakuje
            return -1;
        }
        return 1;
    }

    @PutMapping("/{id}")
    public int update(@RequestBody Musician musicianUpdated, @PathVariable Long id){

        try{
            Musician musician = musicianRepository.findById(id).get();
            musician.update(musicianUpdated);
            musicianRepository.save(musician);
        } catch (Exception e){
            e.printStackTrace();
            //tu można sprawdzić, co nie działa i wyrzucić czego brakuje
            return -1;
        }
        return 1;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
            musicianRepository.deleteById(id);
    }


}
