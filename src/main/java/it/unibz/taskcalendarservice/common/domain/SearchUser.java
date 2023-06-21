package it.unibz.taskcalendarservice.common.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchUser {
    UserRepository userRepository;

    @Autowired
    public SearchUser(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new IllegalArgumentException("Task with id '" + id + "' does not exist");
        return user.get();
    }

    public List<User> getAll(){
        List<User> tasks = userRepository.findAll();
        System.out.println("\nTaskList size: " + tasks.size());
        return tasks;
    }
}