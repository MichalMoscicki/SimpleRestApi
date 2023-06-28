package com.immpresariat.SimpleRestApi.musicians.repository;

import com.immpresariat.SimpleRestApi.musicians.model.Musician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianRepository extends JpaRepository<Musician, Long> {


}
