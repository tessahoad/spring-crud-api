package com.elsevier.techassociates.springcrudapi.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootResource {
    public static final String BASE_URL = "/api/";

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_JPEG = "image/jpeg";

    @GetMapping(RootResource.BASE_URL)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> get() {
        return Map.of("description", "My really neat API");
    }
}
