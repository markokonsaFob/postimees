package com.fobsolutions.postimees.implementation.home;

import io.cify.framework.PageObjects;
import io.cify.framework.core.Device;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by FOB Solutions
 */
public class HomePage extends PageObjects {

    @FindAll({
            @FindBy(className = "article"),
            @FindBy(className = "frontArticle"),
            @FindBy(className = "article-summary-wrapper")
    })
    private List<WebElement> article;

    public HomePage(Device device) {
        super(device, 0);
    }

    /**
     * Gets articles
     */
    public List<WebElement> getArticles() {
        return article;
    }
}
