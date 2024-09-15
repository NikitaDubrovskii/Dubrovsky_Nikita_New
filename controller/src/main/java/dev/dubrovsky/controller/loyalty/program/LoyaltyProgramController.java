package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.dto.request.loyalty.program.NewLoyaltyProgramRequest;
import dev.dubrovsky.dto.request.loyalty.program.UpdateLoyaltyProgramRequest;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/loyalty-program")
@AllArgsConstructor
public class LoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid NewLoyaltyProgramRequest request,
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(loyaltyProgramService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loyaltyProgramService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateLoyaltyProgramRequest request,
                                    @PathVariable Integer id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            loyaltyProgramService.update(request, id);
            return new ResponseEntity<>("Обновлено!", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        loyaltyProgramService.delete(id);
        return new ResponseEntity<>("Удалено!", HttpStatus.OK);
    }

}
