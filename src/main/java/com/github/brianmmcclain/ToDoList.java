package com.github.brianmmcclain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.security.Principal;
import java.util.List;

@Entity
@Table(name = "todolists")
public class ToDoList {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Autowired
    @Transient
    ToDoListRepository listRepo;

    @Autowired
    @Transient
    ToDoRepository todoRepo;

    public String getName() {
        return name;
    }

    public ToDoList() {}

    public ToDoList(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getLists(Principal principal) {
        String username = principal.getName();
        List<ToDoList> lists = listRepo.findByUserUsername(username);
        String r = "";
        for (ToDoList list: lists) {
            r += list.name + " - " + list.user.getUsername() + "<br />";
        }

        return r;
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String showList(@PathVariable("id") long id, Principal principal) {
        String username = principal.getName();
        ToDoList list = listRepo.findById(id);

        if (list == null || !list.user.getUsername().equals(username)) {
            return "You do not own this list!";
        }

        List<ToDo> todos = todoRepo.findByListId(id);
        System.out.println("List ID: " + id);
        System.out.println(todos.size());
        String r = "";
        for (ToDo todo: todos) {
            r += todo.getDescription() + " - " + todo.getCompleted() + "<br />";
        }
        return r;
    }
}
