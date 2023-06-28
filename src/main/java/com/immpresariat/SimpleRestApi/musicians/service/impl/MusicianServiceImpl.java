package com.immpresariat.SimpleRestApi.musicians.service.impl;

import com.immpresariat.SimpleRestApi.musicians.exception.ResourceNotFoundException;
import com.immpresariat.SimpleRestApi.musicians.model.Musician;
import com.immpresariat.SimpleRestApi.musicians.repository.MusicianRepository;
import com.immpresariat.SimpleRestApi.musicians.payload.MusicianDTO;
import com.immpresariat.SimpleRestApi.musicians.service.MusicianService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicianServiceImpl  implements MusicianService {

    private final MusicianRepository musicianRepository;

    public MusicianServiceImpl(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @Override
    public MusicianDTO createMusician(MusicianDTO musicianDTO) {
        Musician musician = mapDtoToMusician(musicianDTO);
        Musician newMusician  =  musicianRepository.save(musician);
        return mapMusicianToDto(newMusician);
    }

    @Override
    public List<MusicianDTO> findAll() {
        List<Musician> musicianList = musicianRepository.findAll();
        return musicianList.stream()
                .map(musician -> mapMusicianToDto(musician)).collect(Collectors.toList());
    }

    @Override
    public MusicianDTO findById(Long id) {
        Musician musician = musicianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Musician", "id", id));
        return mapMusicianToDto(musician);
    }

    @Override
    public MusicianDTO updateMusician(@RequestBody MusicianDTO musicianDTO, Long id) {
        Musician musician = musicianRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Musician", "id", id));
        musician.setFirstName(musicianDTO.getFirstName());
        musician.setLastName(musicianDTO.getLastName());
        musician.setEmail(musicianDTO.getEmail());
        musician.setInstrument(musicianDTO.getInstrument());
        musician.setPhoneNumber(musicianDTO.getPhoneNumber());

        Musician updatedMusician = musicianRepository.save(musician);

        return mapMusicianToDto(updatedMusician);
    }

    @Override
    public void deleteMusicianById(Long id) {
        Musician musician = musicianRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Musician", "id", id));
        musicianRepository.delete(musician);
    }

    private Musician mapDtoToMusician(MusicianDTO musicianDTO){
        Musician musician = new Musician();
        musician.setId(musicianDTO.getId());
        musician.setFirstName(musicianDTO.getFirstName());
        musician.setLastName(musicianDTO.getLastName());
        musician.setEmail(musicianDTO.getEmail());
        musician.setInstrument(musicianDTO.getInstrument());
        musician.setPhoneNumber(musicianDTO.getPhoneNumber());

        return musician;
    }

    private MusicianDTO mapMusicianToDto(Musician musician){
        MusicianDTO musicianDTO = new MusicianDTO();
        musicianDTO.setId(musician.getId());
        musicianDTO.setFirstName(musician.getFirstName());
        musicianDTO.setLastName(musician.getLastName());
        musicianDTO.setEmail(musician.getEmail());
        musicianDTO.setInstrument(musician.getInstrument());
        musicianDTO.setPhoneNumber(musician.getPhoneNumber());
        return musicianDTO;
    }

}
