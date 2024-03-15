package com.batuhanozudogru.userservice.controller;

import com.batuhanozudogru.userservice.general.result.ResultData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseControllerTest {

    protected ObjectMapper objectMapper;

    protected boolean isStatus(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();

        ResultData resultData = objectMapper.readValue(content, ResultData.class);

        boolean isStatus = resultData.isStatus();

        return isStatus;
    }
}
