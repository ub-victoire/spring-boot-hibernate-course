package com.victoire.spring_boot_hibernate_course.repository;

import com.victoire.spring_boot_hibernate_course.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
