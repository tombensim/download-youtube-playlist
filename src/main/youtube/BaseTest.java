package youtube;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class BaseTest {


    protected WebDriver driver = null;

    @BeforeClass
    public static void setupClass() {
        EdgeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }


    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}


