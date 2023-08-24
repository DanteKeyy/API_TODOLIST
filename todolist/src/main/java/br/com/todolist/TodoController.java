package br.com.todolist;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo create(@RequestBody Todo tarefa) {
        return this.todoRepo.save(tarefa);
    }
   /* @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> delete (@PathVariable Integer tarefaID) {
        Optional<Todo> todo = this.todoRepo.findById(tarefaID);
        if (todo.isPresent()) {
            this.todoRepo.deleteById(tarefaID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
@PutMapping ("/{todoId}/start_task")
public  ResponseEntity<Todo> startTask(@PathVariable Integer todoId) {
   Todo todoDatabase = this.todoRepo.findById(todoId).get();
    if (todoDatabase != null) {
        todoDatabase.setStatus(StatusEnum.IN_PROGRESS);
        this.todoRepo.save(todoDatabase);
        return ResponseEntity.ok(todoDatabase);
    } else {
        return ResponseEntity.notFound().build();
    }

    }
    @PutMapping("/{todoId}/end_task")
    public ResponseEntity<Todo> endtask(@PathVariable Integer todoId) {
        Todo todoDatabase = this.todoRepo.findById(todoId).get();
        if (todoDatabase != null) {
            todoDatabase.setStatus(StatusEnum.FINISHED);
            this.todoRepo.save(todoDatabase);
            return ResponseEntity.ok(todoDatabase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> update(@PathVariable Integer todoId, @RequestBody Todo todo) {
        Todo todoDatabase = this.todoRepo.findById(todoId).get();
        if (todoDatabase != null) {
            todoDatabase.setTitle(todo.getTitle());
            todoDatabase.setDescription(todo.getDescription());
            this.todoRepo.save(todoDatabase);
            return ResponseEntity.ok(todoDatabase);
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

}

