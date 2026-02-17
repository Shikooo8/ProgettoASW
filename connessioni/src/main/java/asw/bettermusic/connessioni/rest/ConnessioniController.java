package com.bettermusic.connessioni.controller;

import com.bettermusic.connessioni.model.Connessione;
import com.bettermusic.connessioni.service.ConnessioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/connessioni")
public class ConnessioniController {

    @Autowired
    private ConnessioniService service;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @PostMapping
    public ConnessioneResponse createConnessione(@RequestBody CreateConnessioneRequest request) {
        logger.info("REST CALL: createConnessione " + request);
        Connessione c = service.creaConnessione(request.getUtente(), request.getSeguito(), request.getRuolo());
        if (c == null) {
            logger.warning("Connessione not created: " + request);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Connessione not created");
        }
        ConnessioneResponse response = toResponse(c);
        logger.info("Connessione created: " + response);
        return response;
    }

    @GetMapping
    public Collection<ConnessioneResponse> getConnessioni() {
        logger.info("REST CALL: getConnessioni");
        return service.getConnessioni()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    
    @GetMapping("/{utente}")
    public Collection<ConnessioneResponse> getConnessioniByUtente(@PathVariable String utente) {
        logger.info("REST CALL: getConnessioniByUtente " + utente);
        return service.getConnessioniByUtente(utente)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    
    @GetMapping("/{utente}/{ruolo}")
    public Collection<ConnessioneResponse> getConnessioniByUtenteAndRuolo(@PathVariable String utente, @PathVariable String ruolo) {
        logger.info("REST CALL: getConnessioniByUtenteAndRuolo " + utente + ", " + ruolo);
        return service.getConnessioniByUtenteAndRuolo(utente, ruolo)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    
    @DeleteMapping("/{id}")
    public ConnessioneResponse deleteConnessioneById(@PathVariable String id) {
        logger.info("REST CALL: deleteConnessioneById " + id);
        Connessione c = service.cancellaConnessione(id) ? new Connessione(id) : null;
        if (c == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Connessione not found");
        }
        return toResponse(c);
    }

    
    @DeleteMapping
    public ConnessioneResponse deleteConnessione(@RequestBody DeleteConnessioneRequest request) {
        logger.info("REST CALL: deleteConnessione " + request);
        Connessione c = service.deleteConnessione(request.getUtente(), request.getSeguito(), request.getRuolo());
        if (c == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Connessione not deleted");
        }
        return toResponse(c);
    }

    
    private ConnessioneResponse toResponse(Connessione c) {
        return new ConnessioneResponse(c.getId(), c.getUtente(), c.getSeguito(), c.getRuolo());
    }
}
