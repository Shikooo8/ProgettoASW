package asw.bettermusic.recensioniseguite.eventlistener;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.connessioni.api.event.*;
import asw.bettermusic.recensioniseguite.domain.ConnessioniEventConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;


@Component
public class ConnessioniEventKafkaListener{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ConnessioniEventConsumerService connessioniEventConsumerService;


    @KafkaListener(topics= ConnessioniServiceEventChannel.channel, groupId="${spring.kafka.consumer.group-id}")
    public void listener(ConsumerRecord<String, DomainEvent> record) throws Exception{       //String->chiave; String->valore messaggio
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event= record.value();
        connessioniEventConsumerService.onEvent(event);
        logger.info("ConnessioniEventKafkaListener-rs received event: " + event);
    } 

}