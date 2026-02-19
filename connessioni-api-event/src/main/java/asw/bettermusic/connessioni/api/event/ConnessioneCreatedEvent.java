package asw.bettermusic.connessioni.api.event;

import asw.bettermusic.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConnessioneCreatedEvent implements DomainEvent{

    private Long id;

    private String utente;

    private String seguito;

    private String ruolo;


/*    
    private String connessioneId;
    private String userId;
    private String targetId;

    public ConnessionCreatedEvent(String connessioneId, String userId, String targetId) {
        this.connessioneId = connessioneId;
        this.userId = userId;
        this.targetId = targetId;
    }

    public String getConnessioneId() { return connessioneId; }
    public String getUserId() { return userId; }
    public String getTargetId() { return targetId; }
*/
}
