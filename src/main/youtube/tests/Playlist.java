package youtube.tests;

import framework.FileDirUtils;
import framework.TestBase;
import org.junit.Before;
import org.junit.Test;
import youtube.pages.PlaylistPage;

import java.io.File;
import java.util.ArrayList;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class Playlist extends TestBase {
    PlaylistPage page;
    String playlist = "/watch?v=q8nIQvwiO7I&list=RDq8nIQvwiO7I";
    String youTube = "https://www.youtube.com";

    @Before
    public void setUpPlaylist() {
        driver.get(youTube + playlist);
        page = new PlaylistPage(driver, playlist);
    }
    @Test
    public void getItems() {
        ArrayList<String> urls = page.getAllPlaylistURL();
        urls.forEach(url -> System.out.println(url));
        String file = FileDirUtils.Constants.COPY_TO_DIR + File.separator + "list.txt";
        FileDirUtils.writeListToFile(urls, file);

    }

}