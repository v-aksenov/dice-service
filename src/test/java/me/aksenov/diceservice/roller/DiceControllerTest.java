package me.aksenov.diceservice.roller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.aksenov.diceservice.roller.model.DiceResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rollTheDice() {
        DiceResult diceResult = rollDice(11);
        assertTrue(diceResult.success());
        assertTrue(diceResult.result() > 0);
        assertTrue(diceResult.result() < 12);
    }

    @Test
    void handleException() {
        DiceResult diceResult = rollDice(-1);
        assertFalse(diceResult.success());
        assertNull(diceResult.result());
    }

    private DiceResult rollDice(Integer faces) {
        try {
            String content = mockMvc
                    .perform(MockMvcRequestBuilders.get("/roll").param("faces", faces.toString()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            return asJsonString(content);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static DiceResult asJsonString(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, DiceResult.class);
    }
}

