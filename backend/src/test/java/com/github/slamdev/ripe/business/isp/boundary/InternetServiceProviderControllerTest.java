package com.github.slamdev.ripe.business.isp.boundary;

import com.github.slamdev.ripe.business.isp.control.InternetServiceProviderResourceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProviderResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.stream.Stream;

import static java.net.URI.create;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RunWith(MockitoJUnitRunner.class)
public class InternetServiceProviderControllerTest {

    private static final long ID = 1L;

    @InjectMocks
    private InternetServiceProviderController controller;

    @Mock
    private InternetServiceProviderResourceProvider resourceProvider;

    @Mock
    private InternetServiceProviderRepository repository;

    @Test
    public void should_return_resource_location_after_creating() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        InternetServiceProviderResource resource = new InternetServiceProviderResource(isp);
        resource.add(Link.valueOf("<example.com>;rel=\"foo\"").withSelfRel());
        when(resourceProvider.toResource(isp)).thenReturn(resource);
        ResponseEntity<Void> response = controller.create(isp);
        assertThat(response.getHeaders().getLocation(), equalTo(create("example.com")));
    }

    @Test
    public void should_return_201_status_after_creating() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        InternetServiceProviderResource resource = new InternetServiceProviderResource(isp);
        when(resourceProvider.toResource(isp)).thenReturn(resource);
        ResponseEntity<Void> response = controller.create(isp);
        assertThat(response.getStatusCode(), equalTo(CREATED));
    }

    @Test
    public void should_save_isp_to_repository() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        controller.create(isp);
        verify(repository, times(1)).save(isp);
    }

    @Test
    public void should_return_isp_from_repository() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        when(repository.findOne(ID)).thenReturn(Optional.of(isp));
        controller.get(ID);
        verify(repository, times(1)).findOne(ID);
    }

    @Test
    public void should_return_resource_by_id() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        when(repository.findOne(ID)).thenReturn(Optional.of(isp));
        InternetServiceProviderResource resource = new InternetServiceProviderResource(isp);
        when(resourceProvider.toResource(isp)).thenReturn(resource);
        assertThat(controller.get(ID), equalTo(resource));
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_error_if_isp_id_not_found() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());
        controller.get(ID);
    }

    @Test
    public void should_return_204_status_when_after_deleting_isp() {
        when(repository.exists(any())).thenReturn(true);
        ResponseEntity<Void> response = controller.delete(ID);
        assertThat(response.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_error_if_isp_not_found() {
        when(repository.exists(ID)).thenReturn(false);
        controller.delete(ID);
    }

    @Test
    public void should_delete_isp_from_repository() {
        when(repository.exists(ID)).thenReturn(true);
        controller.delete(ID);
        verify(repository, times(1)).delete(ID);
    }

    @Test
    public void should_query_repository_for_all_isp() {
        when(repository.findAll()).thenReturn(Stream.empty());
        controller.getAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_list_of_resources() {
        InternetServiceProvider isp = InternetServiceProvider.builder().build();
        when(repository.findAll()).thenReturn(Stream.of(isp));
        Resources<InternetServiceProviderResource> resources = controller.getAll();
        assertThat(resources.getContent().size(), equalTo(1));
        assertThat(resources.getContent(), containsInAnyOrder(new InternetServiceProviderResource(isp)));
    }
}