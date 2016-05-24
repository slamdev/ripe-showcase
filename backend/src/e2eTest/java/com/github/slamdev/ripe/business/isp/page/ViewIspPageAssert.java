package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class ViewIspPageAssert extends AbstractAssert<ViewIspPageAssert, ViewIspPage> {

    public ViewIspPageAssert(ViewIspPage page) {
        super(page, ViewIspPageAssert.class);
    }

    public ViewIspPageAssert hasId(String text) {
        assertThat(value(actual.getId())).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasCompanyName(String text) {
        assertThat(value(actual.getCompanyName())).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasWebsite(String text) {
        assertThat(value(actual.getWebsite())).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasEmail(String text) {
        assertThat(value(actual.getEmail())).isEqualTo(text);
        return this;
    }

    public ViewIspPageAssert hasIspFields(InternetServiceProvider isp) {
        return hasCompanyName(isp.getCompanyName()).hasWebsite(isp.getWebsite()
                .toASCIIString()).hasEmail(isp.getEmail());
    }

    private static String value(WebElement element) {
        return element.getAttribute("value");
    }
}
