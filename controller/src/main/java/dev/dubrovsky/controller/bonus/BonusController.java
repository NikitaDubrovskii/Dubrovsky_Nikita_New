package dev.dubrovsky.controller.bonus;

import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.service.bonus.BonusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bonus")
@AllArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Bonus bonus) {
        bonusService.create(bonus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(bonusService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bonusService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Bonus bonus,
                                    @PathVariable Integer id) {
        bonusService.update(bonus, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        bonusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
