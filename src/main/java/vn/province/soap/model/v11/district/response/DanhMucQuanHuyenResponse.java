package vn.province.soap.model.v11.district.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "DanhMucQuanHuyenResponse", namespace = "http://tempuri.org/")
public class DanhMucQuanHuyenResponse {
    @XmlElement(name = "DanhMucTinhResult", namespace = "http://tempuri.org/")
    private DanhMucQuanHuyenResult danhMucTinhResult;
}