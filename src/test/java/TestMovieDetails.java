import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.IMDb;
import pages.Wiki;
import resourceLoader.PropertyLoader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestMovieDetails extends BaseTest
{
    WebDriver driver;
    Properties prop;
    String wDate;
    String iDate;
    String wCountry;
    String iCountry;

    @BeforeMethod
    public void setup() throws IOException
    {
        prop = PropertyLoader.getProperties();
        driver = getWebDriver();
    }

    @Test(priority = 1)
    public void wiki()
    {
        driver.get(prop.getProperty("wiki.weburl"));
        Wiki w = new Wiki(driver);
        w.searchMovie(prop.getProperty("movieName"));
        wDate = w.getReleaseDate();
        wCountry = w.getCountryName();


    }

    @Test(priority = 2)
    public void imdB() throws ParseException
    {
        driver.get(prop.getProperty("imdb.weburl"));
        IMDb i = new IMDb(driver);
        i.searchMovie(prop.getProperty("movieName"));
        iDate = i.getReleaseDate().replace(",","").split("\\(")[0];
        iCountry = i.getCountryName();
//        SimpleDateFormat date = new SimpleDateFormat("dd MMM yyy").parse(iDate);
//        date.format(iDate);

        //String temp = iDate.replace(",","");
        Date date = new SimpleDateFormat("MMMM dd yyyy").parse(iDate);
        iDate = new SimpleDateFormat("dd MMMM yyyy").format(date);

    }

    @Test(priority = 3)
    public void validate()
    {
        Assert.assertEquals(wCountry, iCountry, "Countrie is not matching");
        Assert.assertEquals(wDate, iDate, "Date is not matching");
    }

    @AfterMethod
    public void afterMethod()
    {
        if(driver != null)
            driver.quit();
    }
}
