package com.github.brianmmcclain;

import javax.persistence.*;

@Entity
@Table(name = "todolists")
public class ToDoList {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getUser() {
        return this.user;
    }

    public ToDoList() {}

    public ToDoList(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
