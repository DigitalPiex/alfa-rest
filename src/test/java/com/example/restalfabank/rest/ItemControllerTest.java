package com.example.restalfabank.rest;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(args={"--classpath=src\\main\\resources\\file\\input.xml"})
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void  should_return_for_default_input_file() {

        MvcResult actualId = mockMvc.perform(MockMvcRequestBuilders.post("/item?box=3&color=red"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(actualId.getResponse().getContentAsString()).isEqualTo("[3]");

    }

}
