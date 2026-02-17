package asw.bettermusic.recensioni.eventlistener;

@Component
public class AlbumEventKafkaListener{

    @Value("${asw.kafka.channel.in}")       
    private String channel;
    //da qualche parte si dovrebbe riprendere il canale dichiarato in album-api-event

    @Value("${asw.kafka.groupid}")      //non sono certa
    private String groupId;

    @Autowired
    private AlbumEventConsumerService albumEventConsumerService;


    @KafkaListener(topics= "${asw.kafka.channe.in}", groupId="{asw.kafka.groupid}")
    public void listener(ConsumerRecord<String, Srting> record) throws Exception{
        String event= record.value();
        albumEventConsumerService.onEvent(event);
    } 

}