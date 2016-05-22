package com.github.slamdev.ripe.integration;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

public final class HeaderUtils {

    private HeaderUtils() {
        // Utility class
    }

    public static void setHeaders(HttpServletResponse response, HttpHeaders headers) {
        headers.forEach((name, values) -> values.forEach(value -> response.setHeader(name, value)));
    }

    public static HttpHeaders attachment(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return headers;
    }

    public static HttpHeaders selfLocation(ResourceSupport resource) {
        HttpHeaders headers = new HttpHeaders();
        if (resource != null) {
            Link link = resource.getLink(REL_SELF);
            if (link != null) {
                headers.setLocation(URI.create(link.getHref()));
            }
        }
        return headers;
    }
}
