package com.github.slamdev.ripe.business.isp.boundary;

import com.github.slamdev.ripe.business.isp.control.InternetServiceProviderResourceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProviderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.github.slamdev.ripe.integration.HeaderUtils.selfLocation;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/isp")
public class InternetServiceProviderController {

    @Autowired
    private InternetServiceProviderResourceProvider resourceProvider;

    @Autowired
    private InternetServiceProviderRepository repository;

    @RequestMapping(method = POST)
    public ResponseEntity<Void> create(@Valid InternetServiceProvider internetServiceProvider) {
        repository.save(internetServiceProvider);
        InternetServiceProviderResource resource = resourceProvider.toResource(internetServiceProvider);
        return new ResponseEntity<>(selfLocation(resource), CREATED);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public InternetServiceProviderResource get(@PathVariable Long id) {
        return resourceProvider.toResource(repository.findOne(id).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return new ResponseEntity<>(NO_CONTENT);
        }
        throw new NotFoundException();
    }

    @RequestMapping(method = GET)
    public Resources<InternetServiceProviderResource> getAll() {
        List<InternetServiceProviderResource> resources = repository.findAll()
                .map(InternetServiceProviderResource::new).collect(toList());
        return new Resources<>(resources);
    }
}
