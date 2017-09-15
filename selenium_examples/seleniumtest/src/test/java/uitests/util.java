package uitests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class util {

    private enum browser { Firefox, Chrome, IE }    // define browser pseudonames

    private static WebDriver driver;                // current driver

    // default timeout/sleep method
    public static void TimeOut(long seconds)
    {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    // default take screenshot method
    public static void GetScreenshot(String testName) throws Exception
    {
        String filepath = "";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        util.TimeOut(10);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String parsedDate = dtf.format(date).toString();
        String fileName = filepath + testName + "_" + parsedDate + ".png";
        File fileTo = new File(fileName);
        FileUtils.copyFile(screenshot,fileTo);
        util.TimeOut(10);
    }

    // take screenshot method and adds browser description
    public static void GetScreenshot(String testName, int browserType) throws Exception
    {
        String filepath = "";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        util.TimeOut(10);
        LocalDateTime date = LocalDateTime.now();
        String browserName = browser.values()[browserType].toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String parsedDate = dtf.format(date).toString();
        String fileName = filepath + testName + "_" + browserName + "_" + parsedDate + ".png";
        File fileTo = new File(fileName);
        FileUtils.copyFile(screenshot,fileTo);
        util.TimeOut(10);
    }

    // driver setter
    public static void setDriver(WebDriver test_driver)
    {
        driver = test_driver;
    }
}
