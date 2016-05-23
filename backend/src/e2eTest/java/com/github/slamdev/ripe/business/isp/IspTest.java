package com.github.slamdev.ripe.business.isp;

import com.github.slamdev.ripe.Application;
import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import com.github.slamdev.ripe.business.isp.page.CreateIspPage;
import com.github.slamdev.ripe.business.isp.page.IndexPage;
import com.github.slamdev.ripe.business.isp.page.ViewIspPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;

import static org.openqa.selenium.support.PageFactory.initElements;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
@TestPropertySource("/application-test.properties")
@ActiveProfiles("IspTest")
public class IspTest {

    private static final InternetServiceProvider ISP = InternetServiceProvider.builder()
            .companyName("some name")
            .website(URI.create("http://example.com"))
            .email("some@email.com").build();

    @Autowired
    private WebDriver driver;

    @Autowired
    private String baseUrl;

    private IndexPage page;

    @Before
    public void setUp() {
        driver.get(baseUrl + "/index.html");
        page = initElements(driver, IndexPage.class);
    }

    @Test
    public void should_crud_isp() {
        page.assertThat().hasNoIsps();
        CreateIspPage createIspPage = page.goToCreateIspPage();
        createIspPage.assertThat().hasEmptyIspFields();
        createIspPage.fillIspFields(ISP);
        createIspPage.saveIsp();
        page.assertThat().hasIsp(ISP);
        ViewIspPage viewIspPage = page.goToViewIspPage(ISP);
        viewIspPage.assertThat().hasIspFields(ISP);
        viewIspPage.close();
        page.removeIsp(ISP);
        page.assertThat().hasNoIsps();
    }
}
