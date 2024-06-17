package vn.province.soap.service;

import com.google.gson.Gson;
import jakarta.xml.soap.SOAPMessage;
import lombok.extern.slf4j.Slf4j;
import vn.province.soap.model.v11.district.response.DistrictInfo;
import vn.province.soap.model.v11.ward.request.WardCatalogRequest;
import vn.province.soap.model.v11.ward.response.WardInfo;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
public class Test {

    public static void main(String[] args) {
        try {
            SoapClient<WardCatalogRequest, WardInfo> wardSoapClient = new WardSoapClient();
            WardCatalogRequest wardCatalogRequest = WardCatalogRequest.builder()
                    .toDate("")
                    .province("")
                    .provinceName("")
                    .district("")
                    .districtName("Diễn Châu")
                    .build();
            List<WardInfo> wardInfos = wardSoapClient.find(wardCatalogRequest);
            log.info("Tables: " + wardInfos.size());
            Gson gson = new Gson();
            log.info("Result: " + gson.toJson(wardInfos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
