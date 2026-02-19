package asw.bettermusic.connessioni.eventpublisher; // Deve stare fuori dal domain [cite: 1546]

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate; 

import asw.bettermusic.connessioni.domain.ConnessioniEventPublisher;
import asw.bettermusic.common.api.event.DomainEvent; 

import asw.bettermusic.connessioni.api.event.ConnessioniServiceEventChannel;

import java.util.logging.Logger;

@Component
public class ConnessioniEventKafkaPublisher implements ConnessioniEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());
    
    @Autowired 
    private KafkaTemplate<String, DomainEvent> template; 

    private String channel= ConnessioniServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }
}