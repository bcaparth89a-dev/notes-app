package com.example.notesapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // ✅ IMPORTANT CHANGE (for rich editor)
    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean archived = false;
    private boolean deleted = false;

    private String createdAt;

    @PrePersist
    protected void onCreate() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createdAt = LocalDateTime.now().format(formatter);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isArchived() { return archived; }
    public boolean isDeleted() { return deleted; }
    public String getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setArchived(boolean archived) { this.archived = archived; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}