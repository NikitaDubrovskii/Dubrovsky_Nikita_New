package dev.dubrovsky.controller.loyalty.program;

import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import dev.dubrovsky.service.loyalty.program.UserLoyaltyProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-loyalty-program")
@AllArgsConstructor
public class UserLoyaltyProgramController {

    private final UserLoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserLoyaltyProgram userLoyaltyProgram) {
        loyaltyProgramService.create(userLoyaltyProgram);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(loyaltyProgramService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{programId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId,
                                    @PathVariable Integer programId) {
        loyaltyProgramService.delete(userId, programId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
