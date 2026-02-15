package asw.bettermusic.albumservice.domain;

import asw.bettermusic.common.api.event.DomainEvent;

public interface AlbumEventPublisher {
    void publish(DomainEvent event);
}
