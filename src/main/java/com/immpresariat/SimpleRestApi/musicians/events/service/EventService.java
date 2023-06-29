package com.immpresariat.SimpleRestApi.musicians.events.service;

import com.immpresariat.SimpleRestApi.musicians.events.EventDTO;
import com.immpresariat.SimpleRestApi.musicians.events.EventResponse;

import java.util.List;

public interface EventService {

    public EventDTO create(EventDTO eventDTO);

    public EventDTO update (EventDTO eventDTO, Long id);

    public EventResponse findAll(int pageNumber, int pageSize, String sortBy, String sortDir);
    public EventDTO findById(Long id);

    public void deleteById(Long id);
}
