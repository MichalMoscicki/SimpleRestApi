package com.immpresariat.SimpleRestApi.musicians.musicians.service;

import com.immpresariat.SimpleRestApi.musicians.musicians.MusicianDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MusicianService {

    MusicianDTO createMusician(MusicianDTO musicianDTO);

    List<MusicianDTO> findAll();

    MusicianDTO findById(Long id);

    MusicianDTO updateMusician(@RequestBody MusicianDTO musicianDTO, Long id);

    void deleteMusicianById(Long id);

}
