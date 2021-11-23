package com.kabisa.quotes;

import com.kabisa.quotes.endpoints.QuoteController;
import com.kabisa.quotes.repositories.Repository;
import com.kabisa.quotes.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = QuoteController.class)
public class QuoteEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allQuotesTest() throws Exception {
        this.mockMvc.perform(get("/quotes")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(Repository.getQuoteRepository().getAllQuotes().size())));
    }

    @Test
    public void popularQuotesTest() throws Exception {
        //like quote with id 3
        this.mockMvc.perform(post("/quotes/3/like")).andExpect(status().isOk());
        // expect quote with id 3 to be on top of the list.
        this.mockMvc.perform(get("/quotes/popular")).andExpect(status().isOk()).andExpect(jsonPath("[0].id", is(3)));
    }

    @Test
    public void randomQuoteTest() throws Exception {
        this.mockMvc.perform(get("/quotes/random")).andExpect(status().isOk());
    }

    @Test
    public void singleQuoteTest() throws Exception {
        this.mockMvc.perform(get("/quotes/4")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(4)));
    }

    @Test
    public void likeQuoteTest() throws Exception {
        //like quote with id '3' four times and see if the like increments.
        this.mockMvc.perform(post("/quotes/3/like")).andExpect(status().isOk()).andExpect(content().string(containsString("1")));
        this.mockMvc.perform(post("/quotes/3/like")).andExpect(status().isOk()).andExpect(content().string(containsString("2")));
        this.mockMvc.perform(post("/quotes/3/like")).andExpect(status().isOk()).andExpect(content().string(containsString("3")));
        this.mockMvc.perform(post("/quotes/3/like")).andExpect(status().isOk()).andExpect(content().string(containsString("4")));
    }

    @BeforeEach
    void setUp(){
        Repository.initialize(Service.Api);
    }
}
