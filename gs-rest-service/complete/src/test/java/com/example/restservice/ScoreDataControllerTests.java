
package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ST3ALTHY0.CardGameAPI.restservice.RestServiceApplication; 


@SpringBootTest(classes = RestServiceApplication.class)
@AutoConfigureMockMvc
public class ScoreDataControllerTests {

	@Autowired
	private MockMvc mockMvc;

    //Im getting a lot of help with these test methods.
    //I just don't know a lot of the libraries involved here


    /*
    * Tests if Getting highScores returns an Array
    */
	@Test
	public void selectHighScoresTest() throws Exception {

		this.mockMvc.perform(get("/scoreData")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}


    /*
    * Tests if inserting a User works
    */
	@Test
    public void insertUserTest() throws Exception {
        this.mockMvc.perform(put("/scoreData")
                .param("username", "JohnDoe")
                .param("gameType", "Medium")
                .param("scoreValue", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    //////////////////Tests Below Check Edge Cases/////////////////////////////

    /*
    * Tests if lacking data is handled correctly when inserting a User.
    */
    @Test
    public void insertUserWithMissingParametersTest() throws Exception {
        this.mockMvc.perform(put("/scoreData")
                .param("username", "JohnDoe"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    /*
    * Tests if incorrect data (String passed when int is expected) being passed
    * is handled.
    */
    @Test
    public void insertUserWithInvalidScoreTest() throws Exception {
        this.mockMvc.perform(put("/scoreData")
                .param("username", "JohnDoe")
                .param("gameType", "Medium")
                .param("scoreValue", "invalidScore"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /*
    * Tests if having a empty string for username is handled.
    */
    @Test
    public void insertUserWithEmptyUsernameTest() throws Exception {
        this.mockMvc.perform(put("/scoreData")
                .param("username", "") 
                .param("gameType", "Medium")
                .param("scoreValue", "100"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /*
    * Tests if having a invalid gameType is handled.
    */
    @Test
    public void insertUserWithInvalidGameType() throws Exception {
        this.mockMvc.perform(put("/scoreData")
                .param("username", "John") 
                .param("gameType", "SuperHard")
                .param("scoreValue", "100"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Game type SuperHard not allowed"));
    }



}
