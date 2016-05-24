package com.github.slamdev.ripe.business.isp.page;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateIspPageAssert extends AbstractAssert<CreateIspPageAssert, CreateIspPage> {

    public CreateIspPageAssert(CreateIspPage page) {
        super(page, CreateIspPageAssert.class);
    }

    public CreateIspPageAssert hasCompanyName(String text) {
        assertThat(value(actual.getCompanyName())).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasWebsite(String text) {
        assertThat(value(actual.getWebsite())).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasEmail(String text) {
        assertThat(value(actual.getEmail())).isEqualTo(text);
        return this;
    }

    public CreateIspPageAssert hasEmptyIspFields() {
        return hasCompanyName("").hasWebsite("").hasEmail("");
    }

    private static String value(WebElement element) {
        return element.getAttribute("value");
    }
}
