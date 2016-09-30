package com.fobsolutions.postimees.implementation.home;

import io.cify.framework.PageObjects;
import io.cify.framework.core.models.Device;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by FOB Solutions
 */
public class HomePage extends PageObjects {

    public HomePage(Device device) {
        super(device, 0);
    }

    @FindAll({
            @FindBy(className = "article"),
            @FindBy(className = "frontArticle"),
            @FindBy(className = "article-summary-wrapper")
    })
    private List<WebElement> article;

    /**
     * Gets articles
     */
    public List<WebElement> getArticles() {
        return article;
    }
}
