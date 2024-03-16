package com.batuhanozudogru.userservice.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;


@Service
public class MernisService {

    @Value("${mernis.isActive}")
    private boolean isActive;


    public Boolean verifyUser(String turkishRepublicId, String firstName, String lastName, String birthYear) {

        if (!isActive) {
            return true;
        }
        KpsPublicSoapService kpsPublicSoapService = new KpsPublicSoapService();

        return kpsPublicSoapService.verifyTurkishRepublicIdNo(turkishRepublicId, firstName, lastName, birthYear);
    }


}
