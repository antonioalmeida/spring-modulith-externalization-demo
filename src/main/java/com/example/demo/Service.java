package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Service {

    private final ApplicationEventPublisher eventPublisher;

   @Transactional
   public void withListener() {
       TransactionEvent event = new TransactionEvent(UUID.randomUUID().toString(), "listener");
       System.out.println("Publishing event with listener: " + event.id());

        eventPublisher.publishEvent(event);
    }

    @Transactional
    public void noListener() {
        EventNoListener event = new EventNoListener(UUID.randomUUID().toString(), "no-listener");
        System.out.println("Publishing event without listener: " + event.id());

        eventPublisher.publishEvent(event);
    }

    @Transactional
    public void externalized() {
        EventExternalized event = new EventExternalized(UUID.randomUUID().toString(), "externalized");
        System.out.println("Publishing @Externalized event: " + event.id());

        eventPublisher.publishEvent(event);
    }

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void on(TransactionEvent event) {
        System.out.println("TransactionEvent listener: id:" + event.id());
        // TODO: uncomment for failure scenario
        // throw new RuntimeException();
    }
}
