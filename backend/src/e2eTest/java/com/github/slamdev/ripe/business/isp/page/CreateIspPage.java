package com.github.slamdev.ripe.business.isp.page;

import com.github.slamdev.ripe.business.isp.entity.InternetServiceProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.github.slamdev.ripe.WebDriverUtil.waitOneSecond;

@RequiredArgsConstructor
@Getter
public class CreateIspPage {

    private final WebDriver driver;

    @FindBy(id = "edit-companyName")
    private WebElement companyName;

    @FindBy(id = "edit-website")
    private WebElement website;

    @FindBy(id = "edit-email")
    private WebElement email;

    @FindBy(id = "edit-close")
    private WebElement closeButton;

    @FindBy(id = "edit-save")
    private WebElement saveButton;

    public CreateIspPageAssert assertThat() {
        return new CreateIspPageAssert(this);
    }

    public void fillIspFields(InternetServiceProvider isp) {
        companyName.sendKeys(isp.getCompanyName());
        website.sendKeys(isp.getWebsite().toASCIIString());
        email.sendKeys(isp.getEmail());
        waitOneSecond();
    }

    public void saveIsp() {
        saveButton.click();
        waitOneSecond();
    }
}
