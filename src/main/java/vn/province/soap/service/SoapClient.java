package vn.province.soap.service;

import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
abstract class SoapClient<T, D> {

    abstract List<D> processResponse(String xmlResponse);

    abstract SOAPMessage createDistrictSOAPRequest(T t) throws Exception;

    abstract List<D> find(T t);

    SOAPMessage callSOAPWebService(String soapEndpointUrl, SOAPMessage request, String soapAction) throws Exception {
        try {
            // Tạo kết nối SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Thiết lập tiêu đề SOAPAction
            MimeHeaders headers = request.getMimeHeaders();
            headers.setHeader("SOAPAction", soapAction);

            // Gửi yêu cầu và nhận phản hồi
            SOAPMessage soapResponse = soapConnection.call(request, soapEndpointUrl);

            // Đóng kết nối
            soapConnection.close();

            return soapResponse;
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }
}