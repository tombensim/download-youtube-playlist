package framework;

import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
import org.openqa.selenium.support.ui.SystemClock;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * download-youtube-playlist
 * Created by tom.ben-simhon on 2/4/2017.
 */
public class FileDirUtils {
    public static final long TEN_SEC = 10000;
    /**
     * download-youtube-playlist
     * Created by tom.ben-simhon on 2/4/2017.
     */
    static SystemClock sc = new SystemClock();

    public static ArrayList<String> getListFromFile(File file) {
        return scanFilesLines(file);
    }

    private static ArrayList<String> scanFilesLines(File file) {
        ArrayList<String> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static boolean downloadAndCopyFile(String songTitle) {
        long downloadStartTime = sc.now();
        final File[] downloadFile = {null};

        while ((downloadFile[0] == null || !downloadFile[0].exists())
                && sc.isNowBefore(downloadStartTime + TEN_SEC)) {

            try (Stream<Path> paths = Files.walk(Paths.get(new File(String.valueOf(Constants.DOWNLOAD_DIR)).toURI()))) {
                paths.forEach((Path filePath) -> {
                    if (Files.isRegularFile(filePath) &&
                            filePath.getFileName().toString().contains(songTitle)) {
                        downloadFile[0] = filePath.toFile();
                    }
                });
            } catch (IOException e) {
                System.out.println(String.format("Couldn't locate downloaded file %s in download dir %s"
                        , songTitle
                        , Constants.DOWNLOAD_DIR));
                e.printStackTrace();
                return false;
            }
        }

        System.out.println("\n Finished Downloading, it took - "
                + (downloadStartTime - sc.now()) / 1000
                + " Seconds");
        if (downloadFile[0] != null) {
            try {
                FileUtils.copyFile(downloadFile[0], new File(FileDirUtils.Constants.COPY_TO_DIR
                        + downloadFile[0].getName()));
                FileUtils.deleteQuietly(downloadFile[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public static boolean writeListToFile(ArrayList<String> urls, String urlsToFile) {
        try {

            File file = new File(urlsToFile);
            if (!file.exists())
                file.createNewFile();

            FileWriter writer = new FileWriter(file);
            for (String url : urls) {
                writer.write(url);
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //TODO : use this
    public static void addTagToMp3(File file) {
        ID3v1 id = new ID3v1();
        id.setArtist(file.getName().split("-")[0]);
        id.setSongTitle(file.getName().split("-")[0]);
        MP3File mp3File = null;
        mp3File = getMp3File(file);

        mp3File.setID3v1Tag(id);
        try {
            mp3File.save();
        } catch (IOException | TagException e) {
            e.printStackTrace();
        }
    }

    private static MP3File getMp3File(File file) {
        MP3File mp3File = null ;
        try {
             mp3File = new MP3File(file);
        } catch (IOException | TagException e) {
            e.printStackTrace();
        }
        return mp3File;
    }

    public enum Constants {

        DOWNLOAD_DIR(System.getProperty("user.home")
                + File.separator
                + "Downloads" + File.separator),
        COPY_TO_DIR(System.getProperty("user.dir")
                + File.separator
                + "Downloads" + File.separator
        );
        final String s;

        Constants(String s) {
            this.s = s;
        }

        public String toString() {
            return this.s;
        }
    }

}
