package asw.bettermusic.album.eventpublisher; // Deve stare fuori dal domain [cite: 1546]

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate; 

import asw.bettermusic.album.domain.AlbumEventPublisher;
import asw.bettermusic.common.api.event.DomainEvent; 


@Component
public class AlbumEventKafkaPublisher implements AlbumEventPublisher {
    
    @Autowired 
    private KafkaTemplate<String, DomainEvent> template; 

    @Value("${asw.kafka.channel.out}") // Legge il topic da application.properties [cite: 345, 348]
    private String channel; 

    @Override
    public void publish(DomainEvent event) {
        template.send(channel, event); // Invio reale a Kafka [cite: 351, 1029]
    }
}
