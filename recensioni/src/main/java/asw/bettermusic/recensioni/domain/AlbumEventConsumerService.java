package asw.bettermusic.album.domain;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.album.api.event.*;

import org.springframework.stereotype.Service;

import java.util.*; 



@Service
public class AlbumEventConsumerService{

//    @Value("${asw.kafka.consumer.name}")
//    private String consumerName;

    @Autowired
    private AlbumRepository albumRepository;

    public void onEvent(DomainEvent event){
//        private final Logger logger = Logger.getLogger(this.getClass().toString());
        if(event instanceof AlbumCreatedEvent evt){
            handleAlbumCreatedEvent(evt);
        }
    }

    public void handleAlbumCreatedEvent(AlbumCreatedEvent e){
        Album album= new Album(e.getId(), e.getTitolo(), e.getArtista(), e.getGeneri());

        albumRepository.save(album);        //il metodo si trova in AlbumRepository del servizio Recensioni 

        //il costruttore è stato definito automaticamente basandosi sull'ordine dei campi definiti in Recensioni/.../Album
    }
    
}