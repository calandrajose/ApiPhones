package com.phones.phones;

import com.phones.phones.model.Province;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtils {

    public static URI getProvinceLocation(Province province) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(province.getId())
                .toUri();
    }
}
