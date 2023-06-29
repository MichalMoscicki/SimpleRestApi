package com.immpresariat.SimpleRestApi.musicians.events;

import com.immpresariat.SimpleRestApi.musicians.events.service.EventService;
import com.immpresariat.SimpleRestApi.musicians.events.service.eventServiceImpl.EventServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return new ResponseEntity<>(eventService.create(eventDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<EventResponse> findAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "SortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
            ){

        return new ResponseEntity<>(eventService.findAll(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> udpateEvent(@RequestBody EventDTO eventDTO, @PathVariable Long id) {
        return new ResponseEntity<>(eventService.update(eventDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return new ResponseEntity<>("Succesfully deleted event with id: " + id, HttpStatus.OK);
    }
}
