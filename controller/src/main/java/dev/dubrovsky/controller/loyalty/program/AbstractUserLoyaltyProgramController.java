package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractUserLoyaltyProgramController {

    @PostMapping
    public abstract ResponseEntity<?> create(@RequestBody @Valid NewUserLoyaltyProgramRequest request, BindingResult bindingResult);

    @GetMapping
    public abstract ResponseEntity<?> getAll();

    @DeleteMapping("/{userId}/{programId}")
    public abstract ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer programId);

}
