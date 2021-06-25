package me.aksenov.diceservice.roller.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceService {

    private final Random random = new Random();

    public Integer roll(Integer faces) {
        return random.nextInt(faces) + 1;
    }
}
