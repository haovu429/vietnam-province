package vn.province.soap.service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import vn.province.soap.model.v11.common.Envelope;
import vn.province.soap.model.v11.province.request.DanhMucTinh;

import java.io.StringReader;

public class XmlToObjectExample {

    public static void main(String[] args) {
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <tem:DanhMucTinh>" +
                "         <tem:DenNgay>Ha Noi</tem:DenNgay>" +
                "      </tem:DanhMucTinh>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Envelope<DanhMucTinh> envelope = (Envelope) unmarshaller.unmarshal(reader);

            // In ra kết quả
            System.out.println("DenNgay: " + envelope.getBody().getContent().getDenNgay());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
