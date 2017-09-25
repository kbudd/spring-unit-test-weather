package com.teamtreehouse.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class WeatherControllerTest {
    private MockMvc mockMvc;
    private WeatherController weatherController;

    @Before
    public void setUp() {
        weatherController = new WeatherController();
        mockMvc = new MockMvcBuilders().standaloneSetup(weatherController).build();
    }

    @Test
    public void home_shouldRenderDetailView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("weather/detail"));
    }

    @Test
    public void search_ShouldRedirectWithPathParameter() throws Exception {
        mockMvc.perform(get("/search").param("q", "33596"))
                .andExpect(redirectedUrl("/search/33596"));
    }
}