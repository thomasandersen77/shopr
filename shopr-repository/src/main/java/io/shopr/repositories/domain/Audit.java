package io.shopr.repositories.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Audit {
    @Column(name = "CREATED_ON")
    LocalDateTime createdOn;
    @Column(name = "MODIFIED_ON")
    LocalDateTime modifiedOn;
    @Column(name = "CREATED_BY")
    LocalDateTime createdBy;
    @Column(name = "MODIFIED_BY")
    LocalDateTime modifiedBy;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public LocalDateTime getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LocalDateTime createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(LocalDateTime modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
