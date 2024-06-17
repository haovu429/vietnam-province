package vn.province.soap.model.v11.province.request;

import jakarta.xml.bind.annotation.XmlElement;

public class DanhMucTinh {

    private String denNgay;

    @XmlElement(name = "DenNgay", namespace = "http://tempuri.org/")
    public String getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }
}