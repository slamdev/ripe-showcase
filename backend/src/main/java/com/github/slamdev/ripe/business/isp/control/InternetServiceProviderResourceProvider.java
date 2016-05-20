package com.github.slamdev.ripe.business.isp.control;

import com.github.slamdev.ripe.business.isp.boundary.InternetServiceProviderController;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProviderResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class InternetServiceProviderResourceProvider
        extends ResourceAssemblerSupport<InternetServiceProvider, InternetServiceProviderResource> {

    public InternetServiceProviderResourceProvider() {
        super(InternetServiceProviderController.class, InternetServiceProviderResource.class);
    }

    @Override
    public InternetServiceProviderResource toResource(InternetServiceProvider entity) {
        InternetServiceProviderResource resource = new InternetServiceProviderResource(entity);
        resource.add(linkTo(methodOn(InternetServiceProviderController.class).get(entity.getId())).withSelfRel());
        return resource;
    }
}
