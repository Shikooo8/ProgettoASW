package asw.bettermusic.recensioniseguite.domain;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.connessioni.api.event.*;

import org.springframework.stereotype.Service;

import java.util.*; 

import java.util.logging.Logger;


@Service
public class ConnessioniEventConsumerService{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioniRepository connessioniRepository;

    public void onEvent(DomainEvent event){
//        private final Logger logger = Logger.getLogger(this.getClass().toString());
        logger.info("PROCESSING EVENT: " + event);
        if(event instanceof ConnessioneCreatedEvent evt){
            handleConnessioneCreatedEvent(evt);
        }else if(event instanceof ConnessioneDeletedEvent evt){
            handleConnessioneDeletedEvent(evt);
        }
        else{
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

   // @Transactional
    public void handleConnessioneCreatedEvent(ConnessioneCreatedEvent e){
        Connessione connessione= new Connessione(e.getId(), e.getUtente(), e.getSeguito(), e.getRuolo());

        connessioneRepository.save(connessione);         

        logger.info("CONNESSIONE SAVED: " + connessione.toString());

    }

    public void handleConnessioneDeletedEvent(ConnessioneDeletedEvent e){
//        Connessione connessione= new Connessione(e.getId(), e.getUtente(), e.getSeguito(), e.getRuolo());
        Long idConnessione= e.getId();

        connessioneRepository.deletedById(idConnessione);         

        logger.info("CONNESSIONE DELETED (id: " + idConnessione + ")" );

    }
    
        
}