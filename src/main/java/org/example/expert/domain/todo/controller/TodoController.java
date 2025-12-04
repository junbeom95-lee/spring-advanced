package org.example.expert.domain.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.common.annotation.Auth;
import org.example.expert.common.dto.AuthUser;
import org.example.expert.common.dto.CommonResponse;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<CommonResponse<TodoSaveResponse>> saveTodo(
            @Auth AuthUser authUser,
            @Valid @RequestBody TodoSaveRequest todoSaveRequest
    ) {

        CommonResponse<TodoSaveResponse> result = todoService.saveTodo(authUser, todoSaveRequest);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/todos")
    public ResponseEntity<CommonResponse<Page<TodoResponse>>> getTodos(@RequestParam(defaultValue = "1") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        CommonResponse<Page<TodoResponse>> result = todoService.getTodos(page, size);

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponse>> getTodo(@PathVariable long todoId) {

        CommonResponse<TodoResponse> result = todoService.getTodo(todoId);

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
