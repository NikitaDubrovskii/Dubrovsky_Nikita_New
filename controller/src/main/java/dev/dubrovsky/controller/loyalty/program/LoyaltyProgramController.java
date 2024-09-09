package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/loyalty-program")
@AllArgsConstructor
public class LoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LoyaltyProgram loyaltyProgram) {
        loyaltyProgramService.create(loyaltyProgram);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<?> update(@RequestBody LoyaltyProgram loyaltyProgram,
                                    @PathVariable Integer id) {
        loyaltyProgramService.update(loyaltyProgram, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        loyaltyProgramService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
