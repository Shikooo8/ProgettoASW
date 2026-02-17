package asw.bettermusic.album.domain;

import asw.bettermusic.common.api.event.DomainEvent;


@Service
public class AlbumEventConsumerService{

//    @Value("${asw.kafka.consumer.name}")
//    private String consumerName;

    @Autowired
    private AlbumRepository albumRepository;

    public void onEvent(DomainEvent event){
        if(event instanceof AlbumCreatedEvent evnt){
            handleAlbumCreatedEvent(evnt);
        }
    }

    public void handleAlbumCreatedEvent(AlbumCreatedEvent e){
        Album album= new Album(e.getId(), e.getTitolo, e.getArtista(), e.getGeneri());

        albumRepository.save(album);        //occhio a questo! Non trovo il metodo! 

        //ma deve essere memorizzata in una tabella di Album di Recensioni?? 

        //mi sa che non c'è un costruttore di album che prenede l'id
    }
    
}