package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.UserServiceApplication;
import com.batuhanozudogru.userservice.dto.request.UserSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserUpdateRequest;
import com.batuhanozudogru.userservice.entity.User;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserServiceApplication.class})
class UserControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;




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

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);

    }

    @Test
    void shouldSaveCustomer() throws Exception {

        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "Batuhan",
                "Özüdoğru",
                "44218392564",
                "Batuhan",
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

        boolean status = isStatus(mvcResult);
        assertTrue(status);
    }

    @Test
    void shouldSaveCustomer2() throws Exception {

        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "Kaan",
                "Özüdoğru",
                "12131463630",
                "Kaan",
                LocalDate.of(2007, 1, 1),
                BigDecimal.valueOf(0.50),
                BigDecimal.valueOf(0.40)
        );

        String requestAsString = objectMapper.writeValueAsString(userSaveRequest);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/save")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);
        assertTrue(status);
    }



    @Test
    void shouldGetUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-id/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);
        assertTrue(status);
    }

    @Test
    void shouldGetUserByTurkishRepublicIdNo() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-turkish-republic-id/12345678901"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);
        assertTrue(status);
    }

    @Test
    void shouldGetUserByUsername() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/get-by-username/user1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);
        assertTrue(status);

    }

    @Test
    void shouldDeleteUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/hard-delete-by-id/200"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        boolean status = isStatus(mvcResult);

        assertTrue(status);
    }



    @Test
    void shouldSoftDeleteUserById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/soft-delete-by-id/300"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);

        assertTrue(status);


    }

    @Test
    void shouldUpdateUser() throws Exception {

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
                "newUsername",
                BigDecimal.TEN,
                BigDecimal.TEN


        );

        String requestAsString = objectMapper.writeValueAsString(userUpdateRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users//update-user-by-id/400")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean status = isStatus(mvcResult);
        assertTrue(status);
    }

}