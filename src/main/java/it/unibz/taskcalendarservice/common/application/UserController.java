package it.unibz.taskcalendarservice.common.application;

import it.unibz.taskcalendarservice.common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final CRUDUser crudUser;
    private final SearchUser searchUser;

    @Autowired
    public UserController(CRUDUser crudTask, SearchUser searchTask) {
        this.crudUser = crudTask;
        this.searchUser = searchTask;
    }

    /*
    Test: curl http://localhost:8080/api/tasks/sayhello
    run in terminal
     */
    @PostMapping("/{id}")
    public User findById(@PathVariable("id")Long id){
        return searchUser.findById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserDTO createUserDTO){
        return crudUser.createUser(createUserDTO.getName());
    }

    @PostMapping("/update/{id}")
    public User updateTask(@PathVariable("id") Long id, String name) {
        return  crudUser.updateUser(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask( @PathVariable("id") Long id){
        return crudUser.deleteUser(id);
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return searchUser.getAll();
    }
}
