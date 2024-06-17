package vn.province.soap.model.v11.ward.response;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WardInfo {
    @XmlElement(name = "MaTinh")
    private String maTinh;
    @XmlElement(name = "TenTinh")
    private String tenTinh;
    @XmlElement(name = "MaQuanHuyen")
    private String maQuanHuyen;
    @XmlElement(name = "TenQuanHuyen")
    private String tenQuanHuyen;
    @XmlElement(name = "MaPhuongXa")
    private String maPhuongXa;
    @XmlElement(name = "TenPhuongXa")
    private String tenPhuongXa;
    @XmlElement(name = "LoaiHinh")
    private String loaiHinh;
}