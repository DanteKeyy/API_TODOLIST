package br.com.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepo;

    public TodoController(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List <Todo> getAll() {
        return this.todoRepo.findAll();
    }
}
