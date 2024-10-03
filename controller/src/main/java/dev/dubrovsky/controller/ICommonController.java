package dev.dubrovsky.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ICommonController<N, U> {

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid N request, BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Integer id);

    @GetMapping
    ResponseEntity<?> getAll();

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody @Valid U request, @PathVariable Integer id, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
