package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.model.bonus.UserBonus;
import dev.dubrovsky.service.bonus.UserBonusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-bonus")
@AllArgsConstructor
public class UserBonusController {

    private final UserBonusService userBonusService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserBonus userBonus) {
        userBonusService.create(userBonus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userBonusService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{bonusId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId,
                                    @PathVariable Integer bonusId) {
        userBonusService.delete(userId, bonusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
