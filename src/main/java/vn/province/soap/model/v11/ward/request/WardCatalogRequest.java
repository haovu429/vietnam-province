package vn.province.soap.model.v11.ward.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WardCatalogRequest {
    private String toDate;
    private String province;
    private String provinceName;
    private String district;
    private String districtName;
}
