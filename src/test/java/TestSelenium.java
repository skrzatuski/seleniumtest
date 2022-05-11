import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;


public class TestSelenium {
    public WebDriver driver;
    public String testName;
    @Before
    public void getDriver(){
        System.setProperty("webdriver.edge.driver", "C:/Users/Artur Pietraszewski/Desktop/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://programautomatycy.pl/test-page/");
        acceptCookies();
    }
    @After
    public void takeAScreenshot() throws IOException {
        String dateTime = getDate();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Artur Pietraszewski\\Desktop\\seleniumtest" + "\\screenshots\\" + dateTime + "-" + testName + ".jpg"));
        driver.quit();
    }
    @Test
    public void delayedButtonTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if delayed button is displayed, when displayed click it */
        WebElement delayedButton = driver.findElement(By.id("delay-button"));
        Assert.assertFalse(delayedButton.isDisplayed());
        /* Wait command */
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.visibilityOf(delayedButton));
        Assert.assertTrue(delayedButton.isDisplayed());
        delayedButton.click();
    }
    @Test
    public void bookTitleTest(){
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Book title fill with text */
        WebElement bookTitle = driver.findElement(By.id("book-text"));
        Assert.assertTrue(bookTitle.isEnabled());
        bookTitle.sendKeys("Zaczarowana zagroda");
    }
    @Test
    public void movieTitleTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Movie title fill with text */
        WebElement movieTitle = driver.findElement(By.id("movie-text"));
        Assert.assertTrue(movieTitle.isEnabled());
        movieTitle.sendKeys("Piraci z karaibow");
    }
    @Test
    public void chooseNumberTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if all of number are enable to select */
        List<WebElement> listOfNumbers = driver.findElements(By.xpath("//*[@id=\"number-radio\"]//input"));
        for(WebElement number:listOfNumbers){
            number.click();
            Assert.assertTrue(number.isSelected());
        }
    }
    @Test
    public void animalSelectTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if animal can be selected multiple and check all animals posibility to select */
        Select chooseAnimal = new Select(driver.findElement(By.id("animal-select")));
        Assert.assertFalse(chooseAnimal.isMultiple());
        chooseAnimal.selectByVisibleText("Kot");
        Assert.assertEquals("Kot", chooseAnimal.getFirstSelectedOption().getText());
        chooseAnimal.selectByVisibleText("Pies");
        Assert.assertEquals("Pies", chooseAnimal.getFirstSelectedOption().getText());
        chooseAnimal.selectByVisibleText("Chomik");
        Assert.assertEquals("Chomik", chooseAnimal.getFirstSelectedOption().getText());
        chooseAnimal.selectByVisibleText("Królik");
        Assert.assertEquals("Królik", chooseAnimal.getFirstSelectedOption().getText());
    }
    @Test
    public void colorSelectTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if color can be selected multiple and check colors posibility to select */
        Select chooseColor = new Select(driver.findElement(By.id("colour-select-multiple")));
        Assert.assertTrue(chooseColor.isMultiple());
        chooseColor.selectByVisibleText("Czerwony");
        chooseColor.selectByVisibleText("Zielony");
        chooseColor.selectByVisibleText("Żółty");
        List<WebElement> listOfColours = chooseColor.getAllSelectedOptions();
        for (WebElement color : listOfColours) {
            Assert.assertTrue(color.isSelected());
        }
        chooseColor.deselectAll();
        for (WebElement color : listOfColours) {
            Assert.assertFalse(color.isSelected());
        }
    }
    @Test
    public void someTextTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if its enable to write text */
        WebElement someText = driver.findElement(By.id("text-text"));
        Assert.assertTrue(someText.isEnabled());
        someText.sendKeys("Blabliblubla");
        System.out.println(someText.getText());
    }
    @Test
    public void clickableLinkTest() {
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if the link is clickable */
        WebElement clickLink = driver.findElement(By.id("courses-url"));
        clickLink.click();
        driver.navigate().back();
    }
    @Test
    public void checkboxesTest(){
        testName = new Throwable(){}
                .getStackTrace()[0]
                .getMethodName();
        /* Check if checkbox is enabled and can be selected */
        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"good-checkbox\"]/span/input"));
        Assert.assertTrue(checkbox1.isEnabled());
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        /* Check if checkbox is enabled and can be selected */
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"poorly-checkbox\"]/span/input"));
        Assert.assertTrue(checkbox2.isEnabled());
        checkbox2.click();
        Assert.assertTrue(checkbox2.isSelected());
        /* Check if checkbox is enabled and can be selected */
        WebElement checkbox3 = driver.findElement(By.xpath("//*[@id=\"average-checkbox\"]/span/input"));
        Assert.assertTrue(checkbox3.isEnabled());
        checkbox3.click();
        Assert.assertTrue(checkbox3.isSelected());
        /* Check if button is clickable */
        WebElement button = driver.findElement(By.xpath("//*[@id=\"click-submit\"]"));
        button.click();
    }
    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void acceptCookies() {
        /* Cookie accept */
        WebElement acceptCookies = driver.findElement(By.xpath("//*[@id=\"cn-accept-cookie\"]"));
        acceptCookies.click();
    }
}
