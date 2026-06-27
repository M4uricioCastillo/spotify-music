package com.spotify.music.crm.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Embeddable audit information for playlist metadata.
 * @author Tu Nombre
 */
@Getter
@Embeddable
public class AuditInfo {

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;

    protected AuditInfo() {}

    public AuditInfo(String createdBy) {
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
    }

    public AuditInfo updateLastModified() {
        AuditInfo updated = new AuditInfo();
        updated.createdAt = this.createdAt;
        updated.createdBy = this.createdBy;
        updated.lastModifiedAt = LocalDateTime.now();
        return updated;
    }
}