package asw.bettermusic.recensioni.api.event;
import java.util.Set;

import asw.bettermusic.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//questi servono con Lombok per "accorciare" il codice
@Data @NoArgsConstructor @AllArgsConstructor
public class RecensioneCreatedEvent implements DomainEvent{

    private Long id;

    private String recensore;

    private Long idAlbum;

    private String testo;

    private String sunto;
    
    
}
