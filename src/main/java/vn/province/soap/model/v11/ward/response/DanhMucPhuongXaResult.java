package vn.province.soap.model.v11.ward.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import vn.province.soap.model.v11.province.response.ProvinceInfo;

import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "NewDataSet")
@XmlType(propOrder = {"tables"})
public class DanhMucPhuongXaResult {
    @XmlElement(name = "TABLE")
    private List<WardInfo> wardInfos;
}