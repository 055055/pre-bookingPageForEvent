package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.model.Coupon;
import com.smile.prebookingforgames.repository.CouponRepository;
import com.smile.prebookingforgames.utils.MakeCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private MakeCoupon makeCoupon;

    @Override
    public Map<String,String> registerCoupon(CouponIssueDto registerDTO)  {
      try {
          makeCoupon.phoneNumberDuplicateCheck(registerDTO.getPhoneNumber());
          String found = makeCoupon.digit12Number();
          log.info("found  :  " + found);
          Coupon coupon = Coupon.builder()
                  .phoneNumber(registerDTO.getPhoneNumber())
                  .privateYn(registerDTO.isPrivateYn())
                  .couponNumber(found)
                  .build();

         coupon =  couponRepository.save(coupon);

          Map<String,String> result = new HashMap();
          result.put("couponNumber",coupon.getCouponNumber());
         return result;

      }catch (PreBookingException e){
          log.error("registerCoupon PreeBookingException={}",e.getServiceError());
          throw new PreBookingException(e.getServiceError());
      }catch (Exception ex){
          log.error("registerCouponException={}",ex);
          throw new PreBookingException(ServiceError.INTERNAL_SERVER_ERROR);
      }

    }

    @Override
    public  List<CouponListDTO> getCouponList() {

        List<CouponListDTO> result = new ArrayList<>();

      List<Coupon> found =   couponRepository.findAll();

        for (Coupon coupon : found) {
             CouponListDTO couponListDTO = CouponListDTO.builder()
                                             .couponSeq(coupon.getCouponSeq())
                                             .couponNumber(coupon.getCouponNumber())
                                            .phoneNumber(coupon.getPhoneNumber())
                                            .privateYn(coupon.isPrivateYn())
                                            .regDate(coupon.getRegDate())
                                             .build();
            result.add(couponListDTO);
        }
       return result;
    }


}
