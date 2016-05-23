package com.github.slamdev.ripe.business.isp.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.github.slamdev.ripe.WebDriverUtil.waitOneSecond;

@RequiredArgsConstructor
@Getter
public class ViewIspPage {

    private final WebDriver driver;

    @FindBy(id = "view-id")
    private WebElement id;

    @FindBy(id = "view-companyName")
    private WebElement companyName;

    @FindBy(id = "view-website")
    private WebElement website;

    @FindBy(id = "view-email")
    private WebElement email;

    @FindBy(id = "view-close")
    private WebElement closeButton;

    public ViewIspPageAssert assertThat() {
        return new ViewIspPageAssert(this);
    }

    public void close() {
        closeButton.click();
        waitOneSecond();
    }
}
