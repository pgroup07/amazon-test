package testsuite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite extends Utility {
    String baseurl = "https://www.amazon.co.uk/";

    @Before
    public void setUp() {
        // 1. Open the URL https://www.amazon.co.uk/
        openBrowser(baseurl);
    }

    @Test
    public void verifyDellLaptop() throws InterruptedException {
        //  2. Type "Dell Laptop" in the search box and press enter or click on search Button.
        sendTextToElement(By.xpath("//input[@id='twotabsearchtextbox']"), "Dell Laptop");
        clickOnElement(By.xpath("//input[@id='nav-search-submit-button']"));
        // Accept the Cookies
        clickOnElement(By.xpath("//input[@id='sp-cc-accept']"));
        Thread.sleep(1000);
        // 3. Click on the checkbox brand Dell on the left side.
        clickOnElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[3]"));
        Thread.sleep(5000);
        // 4. Verify that the  30(May be different) products are displayed on the page.
        List<WebElement> products = driver.findElements(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
        int expectedMsg = 30;
        int actualMsg = products.size();
        // verifyTwoMessage(expectedMsg, actualMsg);
        Assert.assertEquals(expectedMsg, actualMsg);
        for (WebElement element : products) {
            System.out.println(element.getText());
        }

        findLaptop("Dell Inspiron 16-5620 Laptop i7-1255U");
        Thread.sleep(1000);
        String expectedMsg1 = "Dell Inspiron 16-5620 Laptop i7-1255U";
        String actualMsg1 = getTextFromElement(By.id("productTitle"));
        String[] actmsg = actualMsg1.split(",");
        Assert.assertEquals(expectedMsg1, actmsg[0]);

    }

    public void findLaptop(String value) {
        boolean flag = true;
        while (flag) {
            //boolean flag=true
            List<WebElement> products1 = driver.findElements(By.xpath("//*[@class='a-size-medium a-color-base a-text-normal']"));
            //

            ArrayList<String> arrayList = new ArrayList<>();
            for (WebElement e : products1) {
                String productname = e.getText();
                String[] laptopName = productname.split(",");
                arrayList.addAll(Arrays.asList(laptopName[0]));

            }
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i));
                if (arrayList.get(i).equalsIgnoreCase(value)) {
                    clickOnElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'" + value + "')]"));
                    // break;
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("=======================================");
                clickOnElement(By.xpath("//a[normalize-space()='Next']"));
            }

        }

    }
}
