package vn.province.soap.model.v11.district.response;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DistrictInfo {
    @XmlElement(name = "MaTinh")
    private String maTinh;
    @XmlElement(name = "TenTinh")
    private String tenTinh;
    @XmlElement(name = "MaQuanHuyen")
    private String maQuanHuyen;
    @XmlElement(name = "TenQuanHuyen")
    private String tenQuanHuyen;
    @XmlElement(name = "LoaiHinh")
    private String loaiHinh;
}