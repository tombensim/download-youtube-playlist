package toMp3.tests;

import framework.FileDirUtils;
import framework.TestBase;
import org.junit.Before;
import org.junit.Test;
import toMp3.pages.HomePage;

import java.io.File;
import java.util.ArrayList;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class Download extends TestBase {
    HomePage page;

    @Before
    public void setUp() {
        driver.get("http://www.youtube-mp3.org/");
        page = new HomePage(driver);
    }

    @Test
    public void DownloadAllItems() throws InterruptedException {
        ArrayList<String> urls = FileDirUtils.getListFromFile(new File(FileDirUtils.Constants.COPY_TO_DIR +
                File.separator + "list.txt"));
        for (String songURL : urls) {
            processURL(songURL);
        }
    }

    private void processURL(String songURL) throws InterruptedException {
        if (page.search(songURL)) {
             System.out.println("Starting Download of url (URL : " + songURL);
             page.download();
         }
         else
        {
            System.out.println("Skipped download for : " + songURL);
        }
    }

}
