package com.github.brianmmcclain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    public ToDo findById(Long id);
    public List<ToDo> findByListId(Long id);
}
