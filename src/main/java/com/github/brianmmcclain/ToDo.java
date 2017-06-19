package com.github.brianmmcclain;

import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@RestController
@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue
    private long id;
    private String description;
    private boolean completed = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id")
    private ToDoList list;

    public ToDo() {}

    public ToDo(String description) {
        this.description = description;
        this.completed = false;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    public long getId() { return this.id; }
}
