package com.smile.prebookingforgames;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.prebookingforgames.dto.RegisterReqDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void coupon_GetMapping_Success() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                //.andDo(print())
        ;
    }


    @Test
    @Transactional
    public void coupon_PostMapping_Success() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("01011111111");
        registerReqDTO.setPrivateYn("true");

        //when //then
        mockMvc.perform(post("/api/events")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(registerReqDTO)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("couponNumber").exists())
                        .andExpect(view().name("register"))
        ;
    }

    @Test
    @Transactional
    public void coupon_PostMapping_BadRequest() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("xxxxxxxxxx");
        registerReqDTO.setPrivateYn("true");

        //when //then
        mockMvc.perform(post("/api/events")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(registerReqDTO)))
                .andDo(print())
                .andExpect(status().isLengthRequired())
                .andExpect(jsonPath("resultCode").value("4003"))
        ;
    }

    @Test
    @Transactional
    public void coupon_PostMapping_BadRequest_Validation() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("");
        registerReqDTO.setPrivateYn("");

        //when //then
        mockMvc.perform(post("/api/events")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(registerReqDTO)))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("resultCode").value("4002"))
        ;
    }

    @Test
    @Transactional
    public void coupon_PostMapping_BadRequest_WrongNumber_Validation() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("0101112233");
        registerReqDTO.setPrivateYn("on");

        //when //then
        mockMvc.perform(post("/api/events")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(registerReqDTO)))
                .andDo(print())
                .andExpect(status().isLengthRequired())
                .andExpect(jsonPath("resultCode").value("4003"))
        ;
    }



    @Test
    public void couponList_GetMapping_SUCCESS() throws Exception {
        mockMvc.perform(get("/api/events/coupon-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("coupon-list"))
        //.andDo(print())
        ;
    }

    @Test
    public void couponListAll_GetMapping_SUCCESS() throws Exception {
        //when //then
        mockMvc.perform(get("/api/events/coupon-list/all")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").exists())
        ;
    }
}
