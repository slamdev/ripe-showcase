package com.github.slamdev.ripe.business.isp.page;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateIspPageAssert extends AbstractAssert<CreateIspPageAssert, CreateIspPage> {

    public CreateIspPageAssert(CreateIspPage page) {
        super(page, CreateIspPageAssert.class);
    }

    public CreateIspPageAssert hasCompanyName(String text) {
        assertThat(actual.getCompanyName().getAttribute("value")).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasWebsite(String text) {
        assertThat(actual.getWebsite().getAttribute("value")).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasEmail(String text) {
        assertThat(actual.getEmail().getAttribute("value")).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasEmptyIspFields() {
        return hasCompanyName("").hasWebsite("").hasEmail("");
    }
}
