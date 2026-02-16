package asw.bettermusic.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.bettermusic.album.domain.AlbumEventPublisher;

@Component
public class AlbumEventKafkaPublisher implements AlbumEventPublisher {
    
	@Autowired private KafkaTemplate<String, DomainEvent> template;
    private String channel = "album-created-topic"; // Definito in application.properties

    @Override
    public void publish(DomainEvent event) {
        template.send(channel, event); // Invio reale a Kafka [cite: 1029]
    }
}
