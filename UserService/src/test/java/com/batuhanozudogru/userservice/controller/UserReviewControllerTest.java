package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.UserServiceApplication;
import com.batuhanozudogru.userservice.client.RestaurantClient;
import com.batuhanozudogru.userservice.controller.contract.impl.UserReviewControllerContractImpl;
import com.batuhanozudogru.userservice.dto.request.UserForReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewForRestaurantRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewSaveRequest;
import com.batuhanozudogru.userservice.dto.request.UserReviewUpdateRequest;
import com.batuhanozudogru.userservice.entity.UserReview;
import com.batuhanozudogru.userservice.general.enums.ReviewRate;
import com.batuhanozudogru.userservice.general.result.ResultData;
import com.batuhanozudogru.userservice.mapper.UserReviewMapper;
import com.batuhanozudogru.userservice.service.UserReviewService;
import com.batuhanozudogru.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserServiceApplication.class})
class UserReviewControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;


    private MockMvc mockMvc;



    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldGetUserReviews() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }

    @Test
    void shouldGetUserReviewById() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/get-by-id/1000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }


    @Test
    void shouldGetUserReviewListByUserId() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/get-by-user-id/400"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }

    @Test
    void shouldGetUserReviewByRestaurantId() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/get-by-restaurant-id/f50d76b2-f2cd-4ee9-88ff-d252453b632f"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean isStatus = resultData.isStatus();


        assertTrue(isStatus);



    }

    @Test
    void shouldGetUserReviewByUserUsername() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/get-by-username/user5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }

    @Test
    void shouldSaveUserReview() throws Exception {


        UserReviewSaveRequest userReviewSaveRequest = new UserReviewSaveRequest("review",
                "f50d76b2-f2cd-4ee9-88ff-d252453b632f",
                ReviewRate.FIVE,
                new UserForReviewSaveRequest(100L));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user-reviews/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userReviewSaveRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }

    @Test
    void shouldUpdateUserReview() throws Exception {

        UserReviewUpdateRequest userReviewUpdateRequest = new UserReviewUpdateRequest("updated review",
                ReviewRate.FIVE);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user-reviews/update/3000")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userReviewUpdateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }



    @Test
    void shouldDeleteUserReview() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user-reviews/delete/2000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean isStatus = isStatus(mvcResult);
        assertTrue(isStatus);
    }








}