package com.github.brianmmcclain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoTests {

    @Autowired
    private ToDoRepository toDoRepo;

    @Autowired
    private ToDoListRepository toDoListRepo;

    private User testUser;
    private ToDoList testList;

    @Before
    public void setup() {
        testList = toDoListRepo.findOne(1L);
        testUser = testList.getUser();
    }

    @Test
    public void newToDo() {
        ToDo toDo = new ToDo("Test ToDo");
        toDo.setList(testList);
        assertThat(toDo.getId(), is(notNullValue()));
        assertThat(toDo.getDescription(), is("Test ToDo"));

        toDoRepo.save(toDo);

        ToDo lookupToDo = toDoRepo.findById(toDo.getId());

        assertThat(lookupToDo, is(notNullValue()));

        assertThat(lookupToDo.getId(), is(toDo.getId()));
        assertThat(lookupToDo.getDescription(), is(equalTo(toDo.getDescription())));

    }
}
