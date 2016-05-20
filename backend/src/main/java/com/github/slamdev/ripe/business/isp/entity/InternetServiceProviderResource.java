package com.github.slamdev.ripe.business.isp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = true)
public class InternetServiceProviderResource extends ResourceSupport {

    private final InternetServiceProvider internetServiceProvider;
}
