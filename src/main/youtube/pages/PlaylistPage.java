package youtube.pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import youtube.tests.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class PlaylistPage extends BasePage {

    WebElement playlist;
    String relativePlaylist;
    String youtube = "https://www.youtube.com";

    public PlaylistPage(WebDriver driver) {
        super(driver);
        playlist = driver.findElement(By.id("playlist-autoscroll-list"));
    }

    public PlaylistPage(WebDriver driver, String relativePlaylist) {
        this(driver);
        this.relativePlaylist = relativePlaylist;
    }

    public ArrayList<String> getAllPlaylistURL() {
        List<WebElement> listItems = playlist.findElements(By.xpath("//li/a"));
        ArrayList<String> urls = new ArrayList<>();
        Actions actions = new Actions(wd);
        listItems.forEach(item ->
        {
            String linkToPlaylistItem = item.getAttribute("href");
            if (linkToPlaylistItem.contains("index"))
                urls.add(linkToPlaylistItem);
        });
        return urls;
    }

}
