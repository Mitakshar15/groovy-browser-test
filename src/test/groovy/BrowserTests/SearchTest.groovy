package BrowserTests

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class SearchTest {

    WebDriver chromeDriver;

    @BeforeMethod
    void setupChromeDriver(){
        //Configure the driver object to Chrome Driver, WebDriverManager automatically downloads the required Drivers
        WebDriverManager.chromedriver().driverVersion("latest").setup()

        //Set-up options for the chrome driver
        ChromeOptions options = new ChromeOptions()
        options.addArguments("--headless")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")

        chromeDriver = new ChromeDriver(options)
        chromeDriver.manage().window().maximize()
    }

    @Test
    void testGoogleSearch(){
        chromeDriver.get("https://www.google.com")
        def searchBox = chromeDriver.findElement(By.name("q"))
        searchBox.sendKeys("Alt Digital")
        searchBox.submit()
        Thread.sleep(2000)
        assert chromeDriver.title.contains("Alt"): "Title should contain 'Alt'"
    }

    @AfterMethod
    void tearDown() {
        chromeDriver.quit()
    }

}
