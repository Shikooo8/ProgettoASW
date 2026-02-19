package asw.bettermusic.recensioni.domain;

import asw.bettermusic.common.api.event.DomainEvent;

public interface RecensioniEventPublisher {
    void publish(DomainEvent event);
}
