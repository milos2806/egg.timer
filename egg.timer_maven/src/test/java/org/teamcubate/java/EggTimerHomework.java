package org.teamcubate.java;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/* Test cases:
        1. Verify that the tooltip shows on the mouse hover over "Start" button
        2. Try to start the timer without entering the value in the text box
        3. Try to start timer adding negative number in text box (this test will fail)
        4. Start timer adding valid data in tex box
        5. Stop countdown and return to home page
        6. Navigate to @edotggtimer twitter account
        7. Mute alert sound
        8. Change timer theme
*/


public class EggTimerHomework {
    private WebDriver driver;

    //before every test
    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Milos QA\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // After every test
    @AfterEach
    public void tearDown(){
        // Close the browser
        driver.quit();
    }

    @Test
    public void tooltipOnStartButton () {
        // Open application
        driver.navigate().to("https://e.ggtimer.com/");

        // Mouse hover
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//*[@currentitem='false']"))).perform();

        // Wait for tooltip to shows
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='startpage']")));

        // Get text form tooltip, verify and print
        String helpText = driver.findElement(By.xpath("//*[@id='startpage']")).getText();
        Assertions.assertTrue(helpText.contains("Please enter a valid time. Try \"15 minutes\" or \"9:00pm\""));
        System.out.println("Message appears: " +helpText);

    }


    @Test
    public void tryToStartTimerWithEmptyTextBox () {
        // Open application
        driver.navigate().to("https://e.ggtimer.com/");

        // Verifying the page title
        String expPageTitle = "e.ggtimer - a simple countdown timer";
        if (driver.getTitle().equalsIgnoreCase(expPageTitle)) {
            // This method will return True when the page title matches with specified string
            System.out.println("Page title matched");
        }
        else
            Assertions.fail("Page title is not matching with expected");


        // Click on start button
        driver.findElement(By.xpath("//*[@currentitem='false']")).click();

        // Verify that page url didn't changed
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://e.ggtimer.com/");
        System.out.println("Url is as expected: " +URL);

    }

    @Test
    public void startTimerAddingNegativNumber (){
        driver.navigate().to("https://e.ggtimer.com/");

        // Verify that EggTimer logo exist on the page
        WebElement searchEggTimerLogo = driver.findElement(By.className("EggTimer-start-icon"));
        Assertions.assertTrue(searchEggTimerLogo.isDisplayed());
        System.out.println("EggTimer logo is displayed - Assert passed");

        // Add value in text box
        WebElement textBox = driver.findElement(By.id("EggTimer-start-time-input-text"));
        textBox.sendKeys("-1");

        // Click on start button
        driver.findElement(By.xpath("//*[@currentitem='false']")).click();

        // verifying the URL
        String expPageUrl = "https://e.ggtimer.com/";
        if (driver.getCurrentUrl().equalsIgnoreCase(expPageUrl)) {
            // This method will return True when the page title matches with specified string
            System.out.println("Page URL matched");
        }
        else
            Assertions.fail("Page URL is not matching with expected, countdown has begun");

    }

    @Test
    public void startTimerByAddingValidValue () throws InterruptedException {
        driver.navigate().to("https://e.ggtimer.com/");

        // Verify that EggTimer logo exist on the page
        WebElement searchEggTimerLogo = driver.findElement(By.className("EggTimer-start-icon"));
        Assertions.assertTrue(searchEggTimerLogo.isDisplayed());
        System.out.println("EggTimer logo is displayed - Assert passed");

        // Add value in text box
        WebElement textBox = driver.findElement(By.id("EggTimer-start-time-input-text"));
        textBox.sendKeys("3");

        // Click on start button
        driver.findElement(By.xpath("//*[@currentitem='false']")).click();

        // Verify that page url changed
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://e.ggtimer.com/3");
        System.out.println("Url is as expected:" +URL);

        Thread.sleep(4000);

        // Click on the ‘OK’ button of the alert.
        driver.switchTo().alert().accept();

        // Verify mesagge "Time Expired!"
        String mesagge = driver.findElement(By.xpath("//p[@class='ClassicTimer-time']")).getText();
        Assertions.assertTrue(mesagge.contains("Time Expired!"));
        System.out.println("The message appears: " +mesagge);

    }

    @Test
    public void  stopCountdownAndReturnToHomePage() throws InterruptedException {
        driver.navigate().to("https://e.ggtimer.com/");

        // Verify that home page contains six predefined sequence of timers
        List<WebElement> results = driver.findElements(By.xpath("//a[@data-for='startpage']"));
        Assertions.assertEquals(6, results.size());

        // Print six predefined sequence in console
        results.forEach(element -> {
                    String text = element.getText();
                    System.out.println(text);
        });


        // Start five minute timer
        driver.findElement(By.xpath("//div[@class='EggTimer-start-quick-time']//a[@title='5 minutes']")).click();

        WebElement timer = driver.findElement(By.xpath("//p[@class='ClassicTimer-time']//span['4']"));
        Assertions.assertTrue(timer.isDisplayed());
        System.out.println("The countdown has begun, more than 4 minutes left until the end");
        Thread.sleep(1500);

        // Click on return button
        driver.findElement(By.xpath("//a[@title='Stop timer and return to home page']")).click();

        // Confirm that the home page has opened successfully
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://e.ggtimer.com/");
        System.out.println("Countdown was stoped, Url is as expected: " +URL);

    }

    @Test
    public void navigateToTwitterAccount () {
        driver.navigate().to("https://e.ggtimer.com/");

        // Click on contact link @edotggtimer
        driver.findElement(By.linkText("@edotggtimer")).click();

        // Switch to new tab
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));

        // Verify URL of twitter Edotggtimer account
        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://twitter.com/edotggtimer");
        System.out.println("Url is as expected: " +URL);

        // Closing the new tab
        driver.close();

    }

    @Test
    public void muteAllertSound () {
        // Open application
        driver.navigate().to("https://e.ggtimer.com/");

        // Click on Help and Settings button
        driver.findElement(By.xpath("//button[@data-tip='Help and Settings']")).click();

        // Validate that Help&Settings page have heading "Help"
        String h1 = driver.findElement(By.xpath("//div[@class='EggTimer-help section']//*")).getText();
        Assertions.assertTrue(h1.contains("Help"));
        System.out.println("Heading on Help&Settings page is: " +h1);
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.xpath("//div[@class='EggTimer-help section']//*"))).perform();

        // Scroll
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollTop = arguments[1];", driver.findElement(By.xpath("//div[@class='EggTimer-settings-and-help-content']")), 4000);

        // Verify sound status by default
        String alertSoundStatus = driver.findElement(By.xpath("//span[@class='value']")).getText();
        Assertions.assertTrue(alertSoundStatus.contains("Not Muted"));
        System.out.println("Alert sound by default is: " +alertSoundStatus);

        //click to mute alert sound
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='slider round'][1]")));
        driver.findElement(By.xpath("//span[@class='slider round'][1]")).click();

        // Verify that label is changed to: Mute
        String alertSoundStatusAfterChanging = driver.findElement(By.xpath("//span[@class='value']")).getText();
        Assertions.assertTrue(alertSoundStatusAfterChanging.contains("Mute"));
        System.out.println("Alert sound after changing is: " +alertSoundStatusAfterChanging);

    }

    @Test

    public void changeTheme () throws InterruptedException {
        // Open application
        driver.navigate().to("https://e.ggtimer.com/");

        // Click on Help and Settings button
        driver.findElement(By.xpath("//button[@data-tip='Help and Settings']")).click();

        // Validate that Help&Settings page have heading "Help"
        String h1 = driver.findElement(By.xpath("//div[@class='EggTimer-help section']//*")).getText();
        Assertions.assertTrue(h1.contains("Help"));
        System.out.println("Heading on Help&Settings page is: " +h1);
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(By.xpath("//div[@class='EggTimer-help section']//*"))).perform();

        // Scroll
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollTop = arguments[1];", driver.findElement(By.xpath("//div[@class='EggTimer-settings-and-help-content']")), 2800);
        Thread.sleep(1000);

        // Verify which theme is set by default
        String alertSound = driver.findElement(By.xpath("//span[@class='label']")).getText();
        Assertions.assertTrue(alertSound.contains("Classic"));
        System.out.println("Default theme is: " +alertSound);


        // Click on drop-down list
        driver.findElement(By.id("theme-select")).click();
        // Chose digital theme
        driver.findElement(By.xpath("//option[@value='gg_timer_digital']")).click();

        // Verify which theme is set by default
        String theme = driver.findElement(By.xpath("//span[@class='label']")).getText();
        Assertions.assertTrue(theme.contains("Digital"));
        System.out.println("After changing: " +theme);

    }

}

