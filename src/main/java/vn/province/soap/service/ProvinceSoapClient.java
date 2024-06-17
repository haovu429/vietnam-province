package vn.province.soap.service;

import jakarta.xml.soap.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import vn.province.soap.model.v11.province.response.ProvinceInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class ProvinceSoapClient {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ProvinceSoapClient.class.getName());
        try {
            // Tạo yêu cầu SOAP
            SOAPMessage soapMessage = createProvinceSOAPRequest();

            // Gửi yêu cầu SOAP và nhận phản hồi
            SOAPMessage soapResponse = callSOAPWebService("https://danhmuchanhchinh.gso.gov.vn/DMDVHC.asmx", soapMessage, "http://tempuri.org/DanhMucTinh");

            // In phản hồi ra console
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapResponse.writeTo(out);
            System.out.println("Response SOAP Message:");
            String response = new String(out.toByteArray());
            logger.info(response);
            List<ProvinceInfo> provinceInfos = processResponse(response);
            logger.info("Tables: " + provinceInfos.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<ProvinceInfo> processResponse(String xmlResponse) {
        // Xử lý phản hồi
        List<ProvinceInfo> provinceInfos = new ArrayList<>();
        try {
            // Parse the XML string into a DOM document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

            // Find all TABLE elements under DocumentElement
            NodeList tableNodes = document.getElementsByTagNameNS("", "TABLE");

            // Iterate over TABLE elements
            for (int i = 0; i < tableNodes.getLength(); i++) {
                Element tableElement = (Element) tableNodes.item(i);
                String maTinh = tableElement.getElementsByTagName("MaTinh").item(0).getTextContent();
                String tenTinh = tableElement.getElementsByTagName("TenTinh").item(0).getTextContent();
                String loaiHinh = tableElement.getElementsByTagName("LoaiHinh").item(0).getTextContent();
                String loaiDoThi = ""; // Example: You can add similar lines for other elements if needed
                String vung = "";       // Example: You can add similar lines for other elements if needed

                // Print or process the extracted data
                System.out.println("Ma Tinh: " + maTinh);
                System.out.println("Ten Tinh: " + tenTinh);
                System.out.println("Loai Hinh: " + loaiHinh);
                ProvinceInfo provinceInfo =  new ProvinceInfo();
                provinceInfo.setMaTinh(maTinh);
                provinceInfo.setTenTinh(tenTinh);
                provinceInfo.setLoaiHinh(loaiHinh);
                provinceInfos.add(provinceInfo);
                // Print or process other elements as needed
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provinceInfos;
    }

    private static SOAPMessage createProvinceSOAPRequest() throws Exception {
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
        SOAPElement soapBodyElem = soapBody.addChildElement("DanhMucTinh", "tem");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("DenNgay", "tem");
        soapBodyElem1.addTextNode("?");

        soapMessage.saveChanges();

        // In yêu cầu ra console
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        System.out.println("Request SOAP Message:");
        System.out.println(new String(out.toByteArray()));

        return soapMessage;
    }

    private static SOAPMessage callSOAPWebService(String soapEndpointUrl, SOAPMessage request, String soapAction) throws Exception {
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
    }
}