package com.etaluma.scopesmart.util;

//import com.etaluma.scopesmart.core.assay.dto.KitDTO;
//import com.etaluma.scopesmart.core.domain.barcode.TestBatchBarcode;
//import com.etaluma.scopesmart.core.service.dto.UserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestClient {

    static final String BASE_API_URL="http://localhost:8080%s";

    /*public static List<KitDTO> getKits(String auth)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", auth);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        return restTemplate.exchange(String.format(BASE_API_URL,"/api/kits"), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<KitDTO>>(){}).getBody();

    }

    public static UserDTO getAccount(String auth)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", auth);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        return restTemplate.exchange(String.format(BASE_API_URL,"/api/account"), HttpMethod.GET, requestEntity, UserDTO.class).getBody();

    }

    public static TestBatchBarcode getBarcodeInfo(String auth, String barcode)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", auth);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        return restTemplate.exchange(String.format(BASE_API_URL,"/api/barcodes/info/"+barcode), HttpMethod.GET, requestEntity, TestBatchBarcode.class).getBody();

    }*/
}
