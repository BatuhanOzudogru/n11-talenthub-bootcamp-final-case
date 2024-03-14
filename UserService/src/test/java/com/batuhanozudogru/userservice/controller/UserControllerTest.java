package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.UserServiceApplication;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserServiceApplication.class})
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldGetUsers() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);

    }

    @Test
    void shouldSaveCustomer() throws Exception {

        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "Atacan",
                "Erdoğan",
                "49408587140",
                "batuhanozudogru2",
                LocalDate.of(1997, 1, 1),
                BigDecimal.valueOf(0.50),
                BigDecimal.valueOf(0.40)
        );

        String requestAsString = objectMapper.writeValueAsString(userSaveRequest);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/save")
                .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);
    }



    @Test
    void shouldGetUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-id/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);
    }

    @Test
    void shouldGetUserByTurkishRepublicIdNo() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-turkish-republic-id/12131463630"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);
    }

    @Test
    void shouldGetUserByUsername() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-username/asdasd"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);
    }

    @Test
    void shouldDeleteUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/hard-delete-by-id/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();
        assertTrue(status);
    }

    @Test
    void shouldSoftDeleteUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/soft-delete-by-id/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean status = resultData.isStatus();

        assertTrue(status);


    }

}