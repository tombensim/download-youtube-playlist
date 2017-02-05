package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class BasePage {

    protected WebDriver wd;
    protected WebDriverWait wdwait;
    protected SystemClock sc = new SystemClock();

    public BasePage(WebDriver wd) {
        this.wd = wd;
        wdwait = new WebDriverWait(wd, 10);
    }

}
