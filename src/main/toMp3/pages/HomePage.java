package toMp3.pages;

import framework.BasePage;
import framework.FileDirUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class HomePage extends BasePage {

    public static final String SUCCESS_XPATH = "//*[text()='Video successfully converted to mp3']";
    public static final String YOUTUBE_URL = "youtube-url";
    public static final String SUBMIT = "submit";
    public static final String INVALID_URL = "//*[text()[contains(.,'There is some Maintenance going on.')]] | //*[text()[contains(.,'Invalid URL')]]";

    WebElement downloadButton;
    WebElement searchEdit;
    WebElement submitButton;
    WebElement title;

    public HomePage(WebDriver driver) {
        super(driver);
        searchEdit = wd.findElement(By.id(YOUTUBE_URL));
        submitButton = wd.findElement(By.id(SUBMIT));
    }

    public void download() {
        title = wd.findElement(By.id("title"));
        String songTitle = title.getText().split(":")[1].substring(1);
        System.out.println("Downloading " + songTitle + "\n");
        FileDirUtils.downloadAndCopyFile(songTitle);
    }

    public boolean search(String songURL) throws InterruptedException {

        searchEdit.click();
        searchEdit.sendKeys(songURL);
        Thread.sleep((long) new Random().nextInt(1000));
        submitButton.click();
        try {
            toDownload();
        } catch (TimeoutException elementException) {
            wd.findElement(By.xpath(INVALID_URL));
            System.out.println(songURL +
                    " Didn't produce any title - so called :" +
                    "Maintanance issue");
            return false;
        }
        return true;

    }

    private void toDownload() {
        wdwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUCCESS_XPATH)));
        downloadButton = wd.findElement(By.linkText("Download"));
        downloadButton.click();
    }


}
