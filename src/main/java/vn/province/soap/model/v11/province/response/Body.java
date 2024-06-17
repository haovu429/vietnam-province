package vn.province.soap.model.v11.province.response;

import jakarta.xml.bind.annotation.XmlElement;

public class Body {
    private DanhMucTinhResponse danhMucTinhResponse;

    @XmlElement(name = "DanhMucTinhResponse", namespace = "http://tempuri.org/")
    public DanhMucTinhResponse getDanhMucTinhResponse() {
        return danhMucTinhResponse;
    }

    public void setDanhMucTinhResponse(DanhMucTinhResponse danhMucTinhResponse) {
        this.danhMucTinhResponse = danhMucTinhResponse;
    }
}