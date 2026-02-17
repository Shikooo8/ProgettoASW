package asw.bettermusic.recensioni.eventlistener;

import asw.bettermusic.common.api.event.DomainEvent;
import asw.bettermusic.album.api.event.*;
import asw.bettermusic.recensioni.domain.AlbumEventConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class AlbumEventKafkaListener{

//    @Value("${asw.kafka.channel.in}")       
//    private String channel;
    //da qualche parte si dovrebbe riprendere il canale dichiarato in album-api-event

    @Value("${asw.kafka.groupid}")      //non sono certa
    private String groupId;

    @Autowired
    private AlbumEventConsumerService albumEventConsumerService;


    @KafkaListener(topics= AlbumServiceEventChannel.channel, groupId="{asw.kafka.groupid}")
    public void listener(ConsumerRecord<String, DomainEvent> record) throws Exception{       //String->chiave; String->valore messaggio
        DomainEvent event= record.value();
        albumEventConsumerService.onEvent(event);
    } 

}