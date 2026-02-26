package com.zakdevops.showcase.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ItemsControllerTest {

  @Autowired
  MockMvc mvc;

  @Test
  void list_returns_array() throws Exception {
    mvc.perform(get("/api/items"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(3));
  }

  @Test
  void get_unknown_returns_404() throws Exception {
    mvc.perform(get("/api/items/999"))
        .andExpect(status().isNotFound());
  }
}