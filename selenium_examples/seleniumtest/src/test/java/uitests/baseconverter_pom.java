package uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class baseconverter_pom {

    private static WebDriver driver;    // default driver

    // clears&fills source field
    public void fillSource(String input)
    {
        String elementName = "source";
        driver.findElement(By.name(elementName)).clear();
        driver.findElement(By.name(elementName)).sendKeys(input);
    }

    // fills source base field
    public void fillSourceBase(String input)
    {
        String elementName = "sourcebase";
        driver.findElement(By.name(elementName)).clear();
        driver.findElement(By.name(elementName)).sendKeys(input);
    }

    // clears&fills target base field
    public void fillTargetBase(String input)
    {
        String elementName = "targetbase";
        driver.findElement(By.name(elementName)).clear();
        driver.findElement(By.name(elementName)).sendKeys(input);
    }

    // clicks on calculate button
    public void clickCalculate()
    {
        String elementCSS = "#dialogv5985caf0d271e_button_text";
        driver.findElement(By.cssSelector(elementCSS)).click();
    }

    // fills whole form (source, source base, target base) and click calculate
    public void fillData(String source, String sourcebase, String targetbase)
    {
        this.fillSource(source);
        this.fillSourceBase(sourcebase);
        this.fillTargetBase(targetbase);
        this.clickCalculate();
    }

    // returns contents of error
    public static String GetError()
    {
        return driver.findElement(By.cssSelector("#error_box_v5985caf0d271e")).getText().toString();
    }

    // returns calculated result
    public static String getData()
    {
        // move to result display
        Actions action = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("#dialogv5985caf0d271e_outputs0 > div:nth-child(1)")));
        action.moveToElement(driver.findElement(By.className("textoutput"))).build().perform();
        return driver.findElement(By.cssSelector("#dialogv5985caf0d271e_target")).getText().toString();
    }

    // driver setter
    public static void setDriver(WebDriver test_driver)
    {
        driver = test_driver;
    }

    // default constructor, point to current driver
    public baseconverter_pom(WebDriver test_driver)
    {
        setDriver(test_driver);
    }

}
