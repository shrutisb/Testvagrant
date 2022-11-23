package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDb
{
    WebDriver driver;
    By search = By.xpath("//input[@id='suggestion-search']");
    By releaseDate = By.xpath("//a[text()='Release date']/.. //ul //a");
    By country = By.xpath("//button[normalize-space()='Country of origin']/.. //ul //a");

    public IMDb(WebDriver driver)
    {
        this.driver = driver;
    }

    public By getMovieXpath(String movie)
    {
        return By.xpath("//a[@role='button'][contains(normalize-space(),'" + movie + "')]");
    }

    public void searchMovie(String movie)
    {
        driver.findElement(search).sendKeys(movie + Keys.ENTER);
        driver.findElement(getMovieXpath(movie)).click();
    }

    public String getReleaseDate()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(releaseDate)));
        //December 17, 2021 (India)
        return driver.findElement(releaseDate).getText();
    }

    public String getCountryName()
    {
        return driver.findElement(country).getText();
    }
}
