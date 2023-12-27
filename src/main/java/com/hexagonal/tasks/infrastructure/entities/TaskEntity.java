package com.hexagonal.tasks.infrastructure.entities;

import com.hexagonal.tasks.domain.models.Task;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    private boolean completed;

    public TaskEntity(){}
    public TaskEntity(Long id, String title, String description, LocalDateTime creationDate, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.completed = completed;
    }


    public static TaskEntity fromDomainModel(Task task){

        return new TaskEntity(task.getId(), task.getTitle(), task.getDescription(),task.getCreationDate(), task.isCompleted());
    }
    public Task toDomainModel(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedCreationDate = creationDate.format(formatter);
        return new Task(id, title, description, creationDate, completed);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
}
