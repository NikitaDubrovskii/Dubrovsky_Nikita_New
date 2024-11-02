package dev.dubrovsky.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ICommonController<N, U> {

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> create(@RequestBody @Valid N request, BindingResult bindingResult);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> getById(@PathVariable Integer id);

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> getAll();

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> update(@RequestBody @Valid U request, @PathVariable Integer id, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
