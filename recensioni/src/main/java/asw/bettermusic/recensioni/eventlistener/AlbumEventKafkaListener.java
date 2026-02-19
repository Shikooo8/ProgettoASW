package asw.bettermusic.recensioni.eventlistener;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.album.api.event.*;
import asw.bettermusic.recensioni.domain.AlbumEventConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;


@Component
public class AlbumEventKafkaListener{

    private final Logger logger = Logger.getLogger(this.getClass().toString());


//    @Value("${asw.kafka.channel.in}")       
//    private String channel;
    //da qualche parte si dovrebbe riprendere il canale dichiarato in album-api-event

//    @Value("${asw.kafka.groupid}")
//    private String groupId;

    @Autowired
    private AlbumEventConsumerService albumEventConsumerService;


    @KafkaListener(topics= AlbumServiceEventChannel.channel, groupId="${spring.kafka.consumer.group-id}")
    public void listener(ConsumerRecord<String, DomainEvent> record) throws Exception{       //String->chiave; String->valore messaggio
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event= record.value();
        albumEventConsumerService.onEvent(event);
        logger.info("AlbumEventKafkaListener-r received event: " + event);
    } 

}