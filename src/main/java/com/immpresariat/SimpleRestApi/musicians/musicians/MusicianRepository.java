package com.immpresariat.SimpleRestApi.musicians.musicians;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianRepository extends JpaRepository<Musician, Long> {


}
