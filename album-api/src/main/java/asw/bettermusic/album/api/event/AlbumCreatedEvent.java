package asw.bettermusic.album.api.event;

import asw.bettermusic.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//questi servono con Lombok per "accorciare" il codice
@Data @NoArgsConstructor @AllArgsConstructor
public class AlbumCreatedEvent implements DomainEvent{

    private Long id;

    private String titolo;

    private String artista;

    private Set<String> generi;
    
    
}
