package vn.province.soap.model.v11.province.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "NewDataSet")
@XmlType(propOrder = {"tables"})
public class DanhMucTinhResult {
    @XmlElement(name = "TABLE")
    private List<ProvinceInfo> provinceInfos;
}