package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;
import java.util.List;

import static com.github.slamdev.ripe.WebDriverUtil.waitOneSecond;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.PageFactory.initElements;

@RequiredArgsConstructor
@Getter
public class IndexPage {

    private final WebDriver driver;

    @FindBy(id = "index-table")
    private WebElement table;

    @FindBy(id = "index-create")
    private WebElement createButton;

    public IndexPageAssert assertThat() {
        return new IndexPageAssert(this);
    }

    public CreateIspPage goToCreateIspPage() {
        createButton.click();
        waitOneSecond();
        return initElements(driver, CreateIspPage.class);
    }

    public List<InternetServiceProvider> getIsps() {
        List<WebElement> elements = driver.findElements(xpath("(//td[starts-with(@id,'index-id-')])"));
        return elements.stream().map(WebElement::getText).map(this::getIspById).collect(toList());
    }

    private InternetServiceProvider getIspById(String id) {
        return InternetServiceProvider.builder()
                .id(Long.valueOf(id))
                .companyName(text("index-companyName-" + id))
                .website(URI.create(text("index-website-" + id)))
                .email(text("index-email-" + id))
                .build();
    }

    private String text(String id) {
        return driver.findElement(id(id)).getText();
    }

    public ViewIspPage goToViewIspPage(InternetServiceProvider isp) {
        if (isp.getId() == null) {
            updateIspId(isp);
        }
        WebElement company = driver.findElement(id("index-companyName-" + isp.getId()));
        company.click();
        waitOneSecond();
        return initElements(driver, ViewIspPage.class);
    }

    private void updateIspId(InternetServiceProvider isp) {
        getIsps().stream()
                .filter(e -> e.getCompanyName().equals(isp.getCompanyName()))
                .filter(e -> e.getEmail().equals(isp.getEmail()))
                .filter(e -> e.getWebsite().equals(isp.getWebsite()))
                .findAny().ifPresent(e -> isp.setId(e.getId()));
    }

    public void removeIsp(InternetServiceProvider isp) {
        WebElement removeButton = driver.findElement(id("index-remove-" + isp.getId()));
        removeButton.click();
        waitOneSecond();
    }
}
