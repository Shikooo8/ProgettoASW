package asw.bettermusic.recensioni.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import asw.bettermusic.recensioni.domain.RecensioniEventPublisher;
import asw.bettermusic.common.api.event.DomainEvent; 

import asw.bettermusic.recensioni.api.event.RecensioniServiceEventChannel;

import java.util.logging.Logger;

@Component
public class RecensioniEventKafkaPublisher implements RecensioniEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());
    
    @Autowired 
    private KafkaTemplate<String, DomainEvent> template; 

    private String channel= RecensioniServiceEventChannel.channel; 

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }
}