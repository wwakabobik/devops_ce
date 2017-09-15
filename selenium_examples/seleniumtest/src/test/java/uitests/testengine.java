package uitests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class testengine {
    /* ------- test driver init --------------------------------------------------------------------------------------*/
    private static WebDriver driver;                    // current driver
    private static DesiredCapabilities TestCapability;  // test capabilities for Selenium grid
    private static String OSext = "";                   // default OS filetype extension

    // set OS-dependent capabilities and file extensions
    private static void setOSCapability(int OSType) {

        switch (OSType) {
            case 0:
                // Linux
                //TestCapability.setCapability("platform", "LINUX");
                OSext = "";
                break;
            case 1:
                //TestCapability.setCapability("platform", "WINDOWS");
                OSext = ".exe";
                break;
            case 2:
                //TestCapability.setCapability("platform", "XP");
                OSext = ".exe";
                break;
            case 3:
                //TestCapability.setCapability("platform", "VISTA");
                OSext = ".exe";
                break;
            default:
                break;
        }
    }

    // set browser-dependent capabilities and sets browser itself
    private static void setBrowserCapability(int browserType, int OSType)
    {
        switch (browserType) {
            case 0:
                // Firefox
                TestCapability = DesiredCapabilities.firefox();
                setOSCapability(OSType);
                //TestCapability.setCapability("browserName", "firefox");
                //TestCapability.setCapability("name", "Firefox 55");
                //TestCapability.setCapability("version", "55");
                String FirefoxDriverPath = "src/main/resources/browser/geckodriver" + OSext;

                System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
                driver = new FirefoxDriver(TestCapability);
                break;
            case 1:
                // Chrome
                //TestCapability = DesiredCapabilities.chrome();
                setOSCapability(OSType);
                //TestCapability.setCapability("browserName", "chrome");
                //TestCapability.setCapability("name", "Chrome 60");
                //TestCapability.setCapability("version", "60");
                String ChromeDriverPath = "src/main/resources/browser/chromedriver" + OSext;

                System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
                driver = new ChromeDriver(TestCapability);
                break;
            case 2:
                // Internet Explorer
                //TestCapability = DesiredCapabilities.chrome();
                setOSCapability(OSType);
                //TestCapability.setCapability("browserName", "internet explorer");
                //TestCapability.setCapability("name", "Internet Explorer 11");
                //TestCapability.setCapability("version", "11");
                String IEDriverPath = "src/main/resources/browser/IEDriverServer" + OSext;

                System.setProperty("webdriver.ie.driver", IEDriverPath);
                driver = new InternetExplorerDriver(TestCapability);
                break;
            default:
                break;
        }
    }

    // returns configured driver
    public WebDriver returnDriver()
    {
        return driver;
    }

    // default constructor, set capabilities and init driver depend on params
    testengine(int browserType, int OSType)
    {
        setBrowserCapability(browserType, OSType);
    }
}
