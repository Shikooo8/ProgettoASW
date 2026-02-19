package asw.bettermusic.recensioniseguite.domain;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.recensioni.api.event.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

import java.util.logging.Logger;


@Service
public class RecensioniEventConsumerService{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioniRepository recensioniRepository;

    public void onEvent(DomainEvent event){
//        private final Logger logger = Logger.getLogger(this.getClass().toString());
        logger.info("PROCESSING EVENT: " + event);
        if(event instanceof RecensioneCreatedEvent evt){
            handleRecensioneCreatedEvent(evt);
        }else{
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

   // @Transactional
    public void handleRecensioneCreatedEvent(RecensioneCreatedEvent e){
        Recensione recensione= new Recensione(e.getId(), e.getRecensore(), e.getIdAlbum(), e.getSunto());

        recensioniRepository.save(recensione);         

        logger.info("RECENSIONE SAVED: " + recensione.toString());
    }
    
}