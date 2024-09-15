package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewUserLoyaltyProgramRequest;
import dev.dubrovsky.service.loyalty.program.UserLoyaltyProgramService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-loyalty-program")
@AllArgsConstructor
public class UserLoyaltyProgramController {

    private final UserLoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewUserLoyaltyProgramRequest request,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            loyaltyProgramService.create(request);
            return new ResponseEntity<>("Создано!", HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loyaltyProgramService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{programId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId,
                                    @PathVariable Integer programId) {
        loyaltyProgramService.delete(userId, programId);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
