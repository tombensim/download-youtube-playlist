package framework;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class TestBase {


    public static final long WAIT_FOR_DOWNLOAD_TO_COMPLETE = 15000;
    protected WebDriver driver = null;
    protected FileDirUtils fdu;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        fdu = new FileDirUtils();
    }

    @After
    public void teardown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(WAIT_FOR_DOWNLOAD_TO_COMPLETE);
            driver.quit();
        }
    }
}


