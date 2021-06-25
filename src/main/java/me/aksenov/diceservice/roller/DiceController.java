package me.aksenov.diceservice.roller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.aksenov.diceservice.roller.model.DiceResult;
import me.aksenov.diceservice.roller.service.DiceService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class DiceController {

    private final DiceService diceService;

    @GetMapping("/roll")
    public DiceResult rollTheDice(@RequestParam Integer faces) {
        return new DiceResult(true, diceService.roll(faces));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public DiceResult handleException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new DiceResult(false, null);
    }
}
