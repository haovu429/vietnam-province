package vn.province.soap.model.v11.province.response;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProvinceInfo {
    @XmlElement(name = "MaTinh")
    private String maTinh;
    @XmlElement(name = "TenTinh")
    private String tenTinh;
    @XmlElement(name = "LoaiHinh")
    private String loaiHinh;
}