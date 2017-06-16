package com.github.brianmmcclain;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.security.Principal;
import java.util.List;

@Controller
public class ToDoListController {

    @Autowired
    @Transient
    ToDoListRepository listRepo;

    @Autowired
    @Transient
    ToDoRepository todoRepo;

    @RequestMapping(value = "/api/lists", method=RequestMethod.GET)
    public @ResponseBody List<ToDoList> getLists(Principal principal) {
        String username = principal.getName();
        List<ToDoList> lists = listRepo.findByUserUsername(username);
        return lists;
    }

    @RequestMapping(value = "/api/lists/{id}", method = RequestMethod.GET)
    public @ResponseBody List<ToDo> showList(@PathVariable("id") long id, Principal principal) {
        String username = principal.getName();
        ToDoList list = listRepo.findById(id);

        if (list == null || !list.getUser().getUsername().equals(username)) {
            return null;
        }

        List<ToDo> todos = todoRepo.findByListId(list.getId());

        return todos;

    }
}
