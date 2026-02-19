package com.bettermusic.connessioni.api.event;

import asw.bettermusic.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor

public class ConnessioneDeletedEvent implements DomainEvent {
    
    private Long id;            //potrebbe essere necessario anche solo questo!!

    private String utente;

    private String seguito;

    private String ruolo;

/*    
    private String connessioneId;

    public ConnessionDeletedEvent(String connessioneId) {
        this.connessioneId = connessioneId;
    }

    public String getConnessioneId() { return connessioneId; }
*/
}
