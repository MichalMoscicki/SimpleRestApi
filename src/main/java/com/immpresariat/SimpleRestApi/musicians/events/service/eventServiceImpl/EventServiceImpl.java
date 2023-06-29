package com.immpresariat.SimpleRestApi.musicians.events.service.eventServiceImpl;

import com.immpresariat.SimpleRestApi.musicians.events.Event;
import com.immpresariat.SimpleRestApi.musicians.events.EventDTO;
import com.immpresariat.SimpleRestApi.musicians.events.EventRepository;
import com.immpresariat.SimpleRestApi.musicians.events.EventResponse;
import com.immpresariat.SimpleRestApi.musicians.events.service.EventService;
import com.immpresariat.SimpleRestApi.musicians.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDTO create(EventDTO eventDTO) {
        Event event = mapToEvent(eventDTO);
        Event eventFromDB = eventRepository.save(event);
        return mapToDTO(eventFromDB);
    }

    @Override
    public EventDTO update(EventDTO eventDTO, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event", "id", id));
        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setTime(eventDTO.getTime());
        event.setAddress(eventDTO.getAddress());
        Event updatedEvent = eventRepository.save(event);

        return mapToDTO(updatedEvent);
    }

    @Override
    public EventResponse findAll(int pageNumber, int pageSize,
                                 String sortBy, String sortDir)
    {
        Sort sort = sortDir.equals(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, sort);

        Page<Event> pages = eventRepository.findAll(pageRequest);
        List<Event> events = pages.getContent();
        List<EventDTO> eventsDTO = events.stream().map(event -> mapToDTO(event)).collect(Collectors.toList());

        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvents(eventsDTO);
        eventResponse.setPageNumber(pageNumber);
        eventResponse.setPageSize(pageSize);
        eventResponse.setTotalPages(pages.getTotalPages());
        eventResponse.setTotalElements(pages.getTotalElements());
        eventResponse.setLast(pages.isLast());

        return eventResponse;
    }

    @Override
    public EventDTO findById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Event", "id", id));
        return mapToDTO(event);
    }

    @Override
    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event", "id", id));
        eventRepository.delete(event);
    }

    private Event mapToEvent(EventDTO eventDTO){
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setTime(eventDTO.getTime());
        event.setAddress(eventDTO.getAddress());
        event.setDescription(eventDTO.getDescription());
        return event;
    }
    private EventDTO mapToDTO(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setTime(event.getTime());
        eventDTO.setAddress(event.getAddress());
        eventDTO.setDescription(event.getDescription());
        return eventDTO;
    }
}
