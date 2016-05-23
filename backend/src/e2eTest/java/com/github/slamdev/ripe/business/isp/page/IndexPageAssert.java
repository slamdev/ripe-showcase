package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPageAssert extends AbstractAssert<IndexPageAssert, IndexPage> {

    public IndexPageAssert(IndexPage page) {
        super(page, IndexPageAssert.class);
    }

    public IndexPageAssert hasEmptyTable() {
        assertThat(actual.getIsps()).isEmpty();
        return this;
    }

    public IndexPageAssert hasIsp(InternetServiceProvider isp) {
        assertThat(actual.getIsps()).contains(isp);
        return this;
    }
}
