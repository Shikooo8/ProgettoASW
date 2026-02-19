package asw.bettermusic.recensioniseguite.eventlistener;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.recensioni.api.event.*;
import asw.bettermusic.recensioniseguite.domain.RecensioniEventConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;


@Component
public class RecensioniEventKafkaListener{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioniEventConsumerService recensioniEventConsumerService;


    @KafkaListener(topics= RecensioniServiceEventChannel.channel, groupId="${spring.kafka.consumer.group-id}")
    public void listener(ConsumerRecord<String, DomainEvent> record) throws Exception{       //String->chiave; String->valore messaggio
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event= record.value();
        recensioniEventConsumerService.onEvent(event);
        logger.info("RecensioniEventKafkaListener-rs received event: " + event);
    } 

}