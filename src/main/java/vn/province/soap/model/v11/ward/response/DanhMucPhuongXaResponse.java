package vn.province.soap.model.v11.ward.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "DanhMucQuanHuyenResponse", namespace = "http://tempuri.org/")
public class DanhMucPhuongXaResponse {
    @XmlElement(name = "DanhMucTinhResult", namespace = "http://tempuri.org/")
    private DanhMucPhuongXaResult danhMucTinhResult;
}