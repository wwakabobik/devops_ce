package uitests;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class baseconverter_test {

    // Rule to resolve test name
    @Rule public TestName name = new TestName();

    // Input parameter - type of browser { Firefox, Chrome, IE }
    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[]{0, 1, 2});
    }

    private static WebDriver driver;                // default driver
    private static baseconverter_pom baseconverter; // page object class, needed for current test suite
    private static int browserType;                 // browser type test parameter
    private static int OSType;                      // OS type test parameter

    // Constructor for test class, should be exactly one for JUnit test set
    // therefore one of them (see bellow) commented-out

    // default constructor, sets parameters for test suite
    public baseconverter_test(int browserType)
    {
        this.browserType = browserType; // depends on param
        this.OSType = 1;                // Windows, hardcoded
    }

    /*public baseconverter_test()
    {
        this.browserType = 1;           // Chrome, hardcoded
        this.OSType = 1;                // Windows, hardcoded
    }*/

    // initialize test environment: test driver depends on params, point test page objects to driver, point utils to driver
    public static void initTestframework(int browserType, int OSType)
    {
        testengine te = new testengine(browserType, OSType);
        driver = te.returnDriver();
        baseconverter = new baseconverter_pom(driver);
        util.setDriver(driver);
    }

   /* ------- before-after tests commands ----------------------------------------------------------------------------*/

    // init test environment and open site under test
    @Before
    public void openSite()
    {
        initTestframework(browserType,OSType);  // depends on param
        util.TimeOut(10);
        driver.get("http://planetcalc.ru/375");
        util.TimeOut(10);
    }

    // capture screenshot and close browser window
    @After
    public void closeSite()
    {
        try {
            util.GetScreenshot(name.getMethodName());
        }
        catch(Exception e) {
            System.err.println("Exception " + e.toString());
        }
        driver.close();
    }

    // close browser instance
    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }

    /* ------- Tests -------------------------------------------------------------------------------------------------*/
    @Test
    @Features("Selenium_Test")
    @Stories("219 Hex2Dec")
    public void testHexToDec()
    {
        String expected, actual;

        baseconverter.fillData("F0","16","10");

        expected = "240";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    @Features("Selenium_Test")
    @Stories("220 Hex2Oct")
    public void testHexToOct()
    {
        String expected, actual;

        baseconverter.fillData("D00D","16","8");

        expected = "150015";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    @Features("Selenium_Test")
    @Stories("221 Hex2Bin")
    public void testHexToBin()
    {
        String expected, actual;

        baseconverter.fillData("65","16","2");

        expected = "1100101";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    @Features("Selenium_Test")
    @Stories("222 Hex2Hex")
    public void testHexToHex()
    {
        String expected, actual;

        baseconverter.fillData("DEADBEEF","16","16");

        expected = "DEADBEEF";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDecToBin()
    {
        String expected, actual;

        baseconverter.fillData("789","10","2");

        expected = "1100010101";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDecToOct()
    {
        String expected, actual;

        baseconverter.fillData("65","10","8");

        expected = "101";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDecToHex()
    {
        String expected, actual;

        baseconverter.fillData("1023","10","16");

        expected = "3FF";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDecToDec()
    {
        String expected, actual;

        baseconverter.fillData("9999","10","10");

        expected = "9999";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBinToOct()
    {
        String expected, actual;

        baseconverter.fillData("1010101","2","8");

        expected = "125";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBinToDec()
    {
        String expected, actual;

        baseconverter.fillData("1111","2","10");

        expected = "15";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBinToHex()
    {
        String expected, actual;

        baseconverter.fillData("10001","2","16");

        expected = "11";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBinToBin()
    {
        String expected, actual;

        baseconverter.fillData("101101101","2","2");

        expected = "101101101";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLeadingZero()
    {
        String expected, actual;

        baseconverter.fillData("101","2","16");

        expected = "5";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleIncorrectInput()
    {
        String expected, actual;

        baseconverter.fillData("F","15","8");

        expected = "?";
        util.TimeOut(30);
        actual = baseconverter.getData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLimitsSourceBaseUpper()
    {
        String expected, actual;

        baseconverter.fillData("505","37","2");

        expected = "Значение должно быть в диапазоне [2 .. 36]";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLimitsSourceBaseLower()
    {
        String expected, actual;

        baseconverter.fillData("101q","1","36");

        expected = "Значение должно быть в диапазоне [2 .. 36]";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLimitsTargetBaseUpper()
    {
        String expected, actual;

        baseconverter.fillData("dEDa","36","37");

        expected = "Значение должно быть в диапазоне [2 .. 36]";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLimitsTargetBaseLower()
    {
        String expected, actual;

        baseconverter.fillData("quda42","2","1");

        expected = "Значение должно быть в диапазоне [2 .. 36]";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSymbolsSource()
    {
        String expected, actual;

        baseconverter.fillSource("feDAz-");

        expected = "Введен неверный символ. Допустимые символы:'0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm'.";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSymbolsSourceBase()
    {
        String expected, actual;

        baseconverter.fillSourceBase("10-+/10");

        expected = "Ожидается положительное целое число.";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSymbolsTargetBase()
    {
        String expected, actual;

        baseconverter.fillTargetBase("sigma");

        expected = "Ожидается положительное целое число.";
        util.TimeOut(30);
        actual = baseconverter.GetError();

        Assert.assertEquals(expected, actual);
    }
}
