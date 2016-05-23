package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class ViewIspPageAssert extends AbstractAssert<ViewIspPageAssert, ViewIspPage> {

    public ViewIspPageAssert(ViewIspPage page) {
        super(page, ViewIspPageAssert.class);
    }

    public ViewIspPageAssert hasId(String text) {
        assertThat(actual.getId()).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasCompanyName(String text) {
        assertThat(actual.getCompanyName()).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasWebsite(String text) {
        assertThat(actual.getWebsite()).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasEmail(String text) {
        assertThat(actual.getEmail()).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasIspFields(InternetServiceProvider isp) {
        return hasId(isp.getId().toString()).hasCompanyName(isp.getCompanyName()).hasWebsite(isp.getWebsite()
                .toASCIIString()).hasEmail(isp.getEmail());
    }
}
