package me.aksenov.diceservice.roller.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceServiceTest {

    private final DiceService diceService = new DiceService();

    @RepeatedTest(10)
    void roll() {
        Integer roll = diceService.roll(3);
        assertTrue(roll > 0 && roll < 4);
    }

    @Test
    void rollException() {
        assertThrows(IllegalArgumentException.class, () -> diceService.roll(-1));
    }
}