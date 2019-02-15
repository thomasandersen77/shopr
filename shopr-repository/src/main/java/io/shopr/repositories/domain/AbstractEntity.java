package io.shopr.repositories.domain;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;

@EntityListeners(AuditListener.class)
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Audit audit;

    public Long getId() {
        return id;
    }

    public Audit getAudit() {
        return audit;
    }
}
