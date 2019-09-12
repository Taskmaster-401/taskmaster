package com.twardcox.taskmaster.Repository;

import com.twardcox.taskmaster.Models.Task;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
    Optional<Task> findById(String id);
    Optional<List<Task>> findAllByAssignee(String assignee);
}