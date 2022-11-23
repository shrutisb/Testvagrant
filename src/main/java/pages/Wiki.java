package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Wiki
{
    WebDriver driver;
    By search = By.xpath("//input[@id='searchInput']");
    //    By title = By.xpath("//a[@title='Pushpa: The Rise']");
    By releaseDate = By.xpath("//span[contains(@class,'published updated')]/../..");
    By country = By.xpath("//th[text()='Country']/following-sibling::td");

    public Wiki(WebDriver driver)
    {
        this.driver = driver;
    }

    public By getTitleXpath(String movie)
    {
        return By.xpath("//a[@title='" + movie + "']");
    }

    public void searchMovie(String movie)
    {
        driver.findElement(search).sendKeys(movie + Keys.ENTER);
        driver.findElement(getTitleXpath(movie)).click();
    }

    public String getReleaseDate()
    {
        return driver.findElement(releaseDate).getText();
    }

    public String getCountryName()
    {
        return driver.findElement(country).getText();
    }
}
