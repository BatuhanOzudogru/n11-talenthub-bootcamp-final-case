package com.batuhanozudogru.userservice.service;


import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.KpsPublicSoapService;


@Service
public class MernisService {


    public Boolean verifyUser(String turkishRepublicId, String firstName, String lastName, String birthYear) {

        KpsPublicSoapService kpsPublicSoapService = new KpsPublicSoapService();

        boolean result = kpsPublicSoapService.verifyTurkishRepublicIdNo(turkishRepublicId, firstName, lastName, birthYear);


        return result;
    }


}
