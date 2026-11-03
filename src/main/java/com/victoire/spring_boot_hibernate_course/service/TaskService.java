package com.victoire.spring_boot_hibernate_course.service;

import org.springframework.stereotype.Service;
import com.victoire.spring_boot_hibernate_course.repository.TaskRepository;
import com.victoire.spring_boot_hibernate_course.entity.Task;

// @Service makes the class eligible for Spring's component scanning
@Service
public class TaskService {
    private final TaskRepository taskRepository ;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTaskById(long id){
        return taskRepository.findById(id);
    }
}

