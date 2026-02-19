package asw.bettermusic.connessioni.domain;

import asw.bettermusic.common.api.event.DomainEvent;

public interface ConnessioniEventPublisher {
    void publish(DomainEvent event);
}
