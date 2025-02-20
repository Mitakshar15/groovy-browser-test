package BrowserTests

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testng.Assert
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
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")

        chromeDriver = new ChromeDriver(options)
        chromeDriver.manage().window().maximize()
    }

    //Once Setting Up The Driver Write the Testcases

    @Test
    void testBingSearch(){
        chromeDriver.get("https://www.bing.com")
        def searchBox = chromeDriver.findElement(By.name("q"))
        searchBox.sendKeys("Alt Digital")
        searchBox.submit()
        Thread.sleep(2000)
        assert chromeDriver.title.contains("Alt"): "Title should contain 'Alt'"
    }

    @Test
     void testYouTubeSearch() {
        // Navigate to YouTube
        chromeDriver.get("https://www.youtube.com");

        // Find the search box by name attribute and perform a search
        WebElement searchBox = chromeDriver.findElement(By.name("search_query"));
        searchBox.sendKeys("Java Tutorial");
        searchBox.submit();

        // Wait briefly for results (replace with WebDriverWait in production)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the page title contains the search term
        Assert.assertTrue(chromeDriver.getTitle().contains("Java Tutorial"),
                "Page title should contain 'Java Tutorial'");
    }
    @Test
    public void testPlayYouTubeVideo() {
        // Navigate to YouTube and search
        chromeDriver.get("https://www.youtube.com");
        WebElement searchBox = chromeDriver.findElement(By.name("search_query"));
        searchBox.sendKeys("Python Basics");
        searchBox.submit();

        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the first video result (assuming it's the first link with class 'yt-uix-tile-link')
        WebElement firstVideo = chromeDriver.findElement(By.cssSelector("#video-title"));
        String videoTitle = firstVideo.getText();
        firstVideo.click();

        // Wait for video page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify video title appears in the page title
        Assert.assertTrue(chromeDriver.getTitle().contains(videoTitle),
                "Video title should appear in the page title");
    }
    @Test
    public void testYouTubeSuggestions() {
        // Navigate and search
        chromeDriver.get("https://www.youtube.com");
        WebElement searchBox = chromeDriver.findElement(By.name("search_query"));
        searchBox.sendKeys("React Crash Course");
        searchBox.submit();

        // Wait and click first video
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement firstVideo = chromeDriver.findElement(By.cssSelector("#video-title"));
        firstVideo.click();

        // Wait for video page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check for suggested videos section (using ID 'related')
        WebElement suggestions = chromeDriver.findElement(By.id("related"));
        Assert.assertTrue(suggestions.isDisplayed(),
                "Suggested videos section should be visible");
    }
    @AfterMethod
    void tearDown() {
        chromeDriver.quit()
    }

}
