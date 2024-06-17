package vn.province.soap.model.v11.province.request;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@XmlRootElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class Body<T> {

    @XmlAnyElement(lax = true)
    private T content;
}