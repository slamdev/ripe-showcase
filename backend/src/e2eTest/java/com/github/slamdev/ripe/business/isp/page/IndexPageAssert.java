package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class IndexPageAssert extends AbstractAssert<IndexPageAssert, IndexPage> {

    public IndexPageAssert(IndexPage page) {
        super(page, IndexPageAssert.class);
    }

    public IndexPageAssert hasNoIsps() {
        assertThat(actual.getIsps()).isEmpty();
        return this;
    }

    public IndexPageAssert hasIsp(InternetServiceProvider isp) {
        List<InternetServiceProvider> isps = actual.getIsps().stream().map(this::clone).collect(toList());
        assertThat(isps).contains(clone(isp));
        return this;
    }

    private InternetServiceProvider clone(InternetServiceProvider isp) {
        return InternetServiceProvider.builder()
                .website(isp.getWebsite())
                .email(isp.getEmail())
                .companyName(isp.getCompanyName())
                .build();
    }
}
