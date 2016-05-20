package com.github.slamdev.ripe.business.isp.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Builder
@Data
@Entity
public class InternetServiceProvider {

    @Id
    @GeneratedValue
    private final Long id;

    @NotBlank
    private final String companyName;

    @NotNull
    private final URI website;

    @NotBlank
    @Email
    private final String email;
}
