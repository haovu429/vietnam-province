package vn.province.soap.model.v11.province.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "DanhMucTinhResponse", namespace = "http://tempuri.org/")
public class DanhMucTinhResponse {
    @XmlElement(name = "DanhMucTinhResult", namespace = "http://tempuri.org/")
    private DanhMucTinhResult danhMucTinhResult;
}