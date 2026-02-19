package asw.bettermusic.recensioni.domain;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.album.api.event.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

import java.util.logging.Logger;


@Service
public class AlbumEventConsumerService{

    private final Logger logger = Logger.getLogger(this.getClass().toString());


//    @Value("${asw.kafka.consumer.name}")
//    private String consumerName;

    @Autowired
    private AlbumRepository albumRepository;

    public void onEvent(DomainEvent event){
//        private final Logger logger = Logger.getLogger(this.getClass().toString());
        logger.info("PROCESSING EVENT: " + event);
        if(event instanceof AlbumCreatedEvent evt){
            handleAlbumCreatedEvent(evt);
        }else{
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

   // @Transactional
    public void handleAlbumCreatedEvent(AlbumCreatedEvent e){
        Album album= new Album(e.getId(), e.getTitolo(), e.getArtista(), e.getGeneri());

        albumRepository.save(album);        //il metodo si trova in AlbumRepository del servizio Recensioni 

        logger.info("ALBUM SAVED: " + album.toString());

        //il costruttore è stato definito automaticamente basandosi sull'ordine dei campi definiti in Recensioni/.../Album
    }
    
}