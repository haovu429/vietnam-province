package vn.province.soap.service;

import jakarta.xml.soap.*;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import vn.province.soap.model.v11.ward.request.WardCatalogRequest;
import vn.province.soap.model.v11.ward.response.WardInfo;
import vn.province.soap.util.Constant;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static vn.province.soap.util.Constant.SOAP_ACTION_WARD;
import static vn.province.soap.util.Constant.SOAP_ENDPOINT_URL;

@Slf4j
public class WardSoapClient extends SoapClient<WardCatalogRequest, WardInfo> {

    @Override
    public List<WardInfo> processResponse(String xmlResponse) {
        // Xử lý phản hồi
        List<WardInfo> wardInfos = new ArrayList<>();
        try {
            // Parse the XML string into a DOM document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

            // Find all TABLE elements under DocumentElement
            NodeList tableNodes = document.getElementsByTagNameNS("", Constant.TABLE_ELEMENT_KEY);

            // Iterate over TABLE elements
            for (int i = 0; i < tableNodes.getLength(); i++) {
                Element tableElement = (Element) tableNodes.item(i);
                String maTinh = tableElement.getElementsByTagName(Constant.PROVINCE_CODE).item(0).getTextContent();
                String tenTinh = tableElement.getElementsByTagName(Constant.PROVINCE_NAME).item(0).getTextContent();
                String maQuanHuyen = tableElement.getElementsByTagName(Constant.DISTRICT_CODE).item(0).getTextContent();
                String tenQuanHuyen = tableElement.getElementsByTagName(Constant.DISTRICT_NAME).item(0).getTextContent();
                String maPhuongXa = tableElement.getElementsByTagName(Constant.WARD_CODE).item(0).getTextContent();
                String tenPhuongXa = tableElement.getElementsByTagName(Constant.WARD_NAME).item(0).getTextContent();
                String loaiHinh = tableElement.getElementsByTagName(Constant.CATALOG).item(0).getTextContent();
                String loaiDoThi = ""; // Example: You can add similar lines for other elements if needed
                String vung = "";       // Example: You can add similar lines for other elements if needed

                // Print or process the extracted data
//                System.out.println("Ma Tinh: " + maTinh);
//                System.out.println("Ten Tinh: " + tenTinh);
//                System.out.println("Ma Quan Huyen: " + maQuanHuyen);
//                System.out.println("Ten Quan Huyen: " + tenQuanHuyen);
//                System.out.println("Ma Phuong Xa: " + maPhuongXa);
//                System.out.println("Ten Phuong Xa: " + tenPhuongXa);
//                System.out.println("Loai Hinh: " + loaiHinh);
                WardInfo wardInfo = new WardInfo();
                wardInfo.setMaTinh(maTinh);
                wardInfo.setTenTinh(tenTinh);
                wardInfo.setMaQuanHuyen(maQuanHuyen);
                wardInfo.setTenQuanHuyen(tenQuanHuyen);
                wardInfo.setMaPhuongXa(maPhuongXa);
                wardInfo.setTenPhuongXa(tenPhuongXa);
                wardInfo.setLoaiHinh(loaiHinh);
                wardInfos.add(wardInfo);
            }
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return wardInfos;
    }

    @Override
    SOAPMessage createDistrictSOAPRequest(WardCatalogRequest wardCatalogRequest) throws Exception {
        // Tạo SOAP Message
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        // Tạo SOAP Part
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // Tạo Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", "http://tempuri.org/");

        // Tạo Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement districtCatalogElem = soapBody.addChildElement("DanhMucPhuongXa", "tem");
        SOAPElement toDateElem = districtCatalogElem.addChildElement("DenNgay", "tem");
        SOAPElement tinhElem = districtCatalogElem.addChildElement("Tinh", "tem");
        SOAPElement tenTinhElem = districtCatalogElem.addChildElement("TenTinh", "tem");
        SOAPElement quanHuyenElem = districtCatalogElem.addChildElement("QuanHuyen", "tem");
        SOAPElement tenQuanHuyenElem = districtCatalogElem.addChildElement("TenQuanHuyen", "tem");
        toDateElem.addTextNode(wardCatalogRequest.getToDate());
        tinhElem.addTextNode(wardCatalogRequest.getProvince());
        tenTinhElem.addTextNode(wardCatalogRequest.getProvinceName());
        quanHuyenElem.addTextNode(wardCatalogRequest.getDistrict());
        tenQuanHuyenElem.addTextNode(wardCatalogRequest.getDistrictName());

        soapMessage.saveChanges();

        // In yêu cầu ra console
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        log.info("Request SOAP Message: {}", out);

        return soapMessage;
    }

    @Override
    List<WardInfo> find(WardCatalogRequest wardCatalogRequest) {
        List<WardInfo> wardInfos = new ArrayList<>();
        try {
            SoapClient<WardCatalogRequest, WardInfo> wardSoapClient = new WardSoapClient();
            // Tạo yêu cầu SOAP
            if (wardCatalogRequest == null) {
                wardCatalogRequest = WardCatalogRequest.builder()
                        .toDate("")
                        .province("")
                        .provinceName("")
                        .district("")
                        .districtName("")
                        .build();
            }
            SOAPMessage soapMessage = wardSoapClient.createDistrictSOAPRequest(wardCatalogRequest);

            // Gửi yêu cầu SOAP và nhận phản hồi
            SOAPMessage soapResponse = wardSoapClient.callSOAPWebService(SOAP_ENDPOINT_URL, soapMessage, SOAP_ACTION_WARD);

            // In phản hồi ra console
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapResponse.writeTo(out);
            String response = out.toString();
            wardInfos = wardSoapClient.processResponse(response);
        } catch (Exception ex) {
            log.error("Error: ", ex);
        }
        return wardInfos;
    }
}