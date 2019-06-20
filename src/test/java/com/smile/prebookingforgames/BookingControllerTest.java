package com.smile.prebookingforgames;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.prebookingforgames.common.RestDocsConfiguration;
import com.smile.prebookingforgames.controller.BookingController;
import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.RegisterReqDTO;
import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.service.BookingService;
import com.smile.prebookingforgames.validator.BookingValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookingService bookingService;

    @MockBean
    BookingValidator bookingValidator;

    @Test
    public void coupon_GetMapping_Success() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                //.andDo(print())
        ;
    }

    @Test
    public void coupon_PostMapping_Success() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("01077645831");
        registerReqDTO.setPrivateYn(true);

        Map<String,String> couponMap = new HashMap<>();
        couponMap.put("couponNumber","123A-4qwe-5bqw");
        given(this.bookingService.registerCoupon(any(RegisterReqDTO.class))).willReturn(couponMap);

        //when //then
        mockMvc.perform(post("/api/events")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(registerReqDTO)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("couponNumber").exists())
                        .andDo(document("create-event",
                            requestHeaders(
                                    headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                    headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
                            ),
                            requestFields(
                                    fieldWithPath("phoneNumber").description("user's phoneNumber"),
                                    fieldWithPath("privateYn").description("Personal Information Agreement_YN")
                            ),
                            responseHeaders(
                                    headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                            ),
                            responseFields( fieldWithPath("couponNumber").description("user's couponNumber")
                            )
                        ))

        ;
    }

    @Test
    public void coupon_PostMapping_BadRequest() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("xxxxxxxxxx");
        registerReqDTO.setPrivateYn(true);

        given(this.bookingService.registerCoupon(any(RegisterReqDTO.class))).willThrow(new PreBookingException(ServiceError.WRONG_PHONE_NUMBER));


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
    public void coupon_PostMapping_BadRequest_Validation() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("");
        registerReqDTO.setPrivateYn(false);


        given(this.bookingService.registerCoupon(any(RegisterReqDTO.class))).willThrow(new PreBookingException(ServiceError.VALIDATION_CHECK_ERROR));


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
    public void coupon_PostMapping_BadRequest_WrongNumber_Validation() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setPhoneNumber("0101112233");
        registerReqDTO.setPrivateYn(true);
        given(this.bookingService.registerCoupon(any(RegisterReqDTO.class))).willThrow(new PreBookingException(ServiceError.WRONG_PHONE_NUMBER));


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
        //given
        List<CouponListDTO> list = new ArrayList<>();
        CouponListDTO couponListDTO = CouponListDTO.builder()
                                        .couponSeq(1L)
                                        .couponNumber("123e-4qwer-5ASDF")
                                        .phoneNumber("010xxxxyyyy")
                                        .privateYn(true)
                                        .regDate(LocalDateTime.now())
                                        .build();

        list.add(couponListDTO);
        given(this.bookingService.getCouponList()).willReturn(list);

        //when //then
        mockMvc.perform(get("/api/events/coupon-list/all")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").exists())
                .andDo(document("get-event",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")
                        ),
                        responseFields(
                                fieldWithPath("data[]").description("Data List"),
                                fieldWithPath("data[].couponSeq").description("user's couponeSeq"),
                                fieldWithPath("data[].phoneNumber").description("user's phoneNumber"),
                                fieldWithPath("data[].privateYn").description("Personal Information Agreement_YN"),
                                fieldWithPath("data[].couponNumber").description("user's couponNumber"),
                                fieldWithPath("data[].regDate").description("Data Registration Date")
                        )
                ))
        ;
    }
}
