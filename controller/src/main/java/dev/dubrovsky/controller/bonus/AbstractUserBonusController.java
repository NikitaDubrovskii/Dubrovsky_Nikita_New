package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.dto.request.bonus.NewUserBonusRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractUserBonusController {

    @PostMapping
    public abstract ResponseEntity<?> create(@RequestBody @Valid NewUserBonusRequest request, BindingResult bindingResult);

    @GetMapping
    public abstract ResponseEntity<?> getAll();

    @DeleteMapping("/{userId}/{bonusId}")
    public abstract ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer bonusId);

}
