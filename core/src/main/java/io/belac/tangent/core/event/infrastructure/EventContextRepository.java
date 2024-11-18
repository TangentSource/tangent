package io.belac.tangent.core.event.infrastructure;

import io.belac.tangent.core.event.domain.EventContextEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventContextRepository implements PanacheRepositoryBase<EventContextEntity, Long> {

}
