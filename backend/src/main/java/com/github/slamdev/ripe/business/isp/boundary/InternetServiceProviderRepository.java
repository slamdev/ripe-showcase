package com.github.slamdev.ripe.business.isp.boundary;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface InternetServiceProviderRepository extends Repository<InternetServiceProvider, Long> {

    Optional<InternetServiceProvider> findOne(Long id);

    InternetServiceProvider save(InternetServiceProvider entity);

    void delete(Long id);

    Stream<InternetServiceProvider> findAll();

    boolean exists(Long id);
}
