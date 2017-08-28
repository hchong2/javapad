package mp3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Scratch {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

	String jumbleString = "¿­µÎ½Ã ¹Ý";

	final Charset cp1252 = Charset.forName("windows-1252");
	final Charset utf8 = Charset.forName("UTF-8");
	final Charset utf16 = Charset.forName("UTF-16");

	File out = new File("out.txt");
	FileWriter a = new FileWriter(out);

	FileWriter encodingMapping = new FileWriter(new File("encodingMapping"));

	for (Map.Entry<String, Charset> entry : Charset.availableCharsets().entrySet()) {
	    Charset value = entry.getValue();

	    for (Map.Entry<String, Charset> entry2 : Charset.availableCharsets().entrySet()) {
		try {
		    Charset value2 = entry2.getValue();

		    byte[] byteArray = jumbleString.getBytes(value);
		    String str = new String(byteArray, value2);
		    if (str.equals("열두시 반")) {
			a.write(value.toString() + "\t\t" + value2.toString() + "\t\t" + str + " - Back to UTF-16 - "
				+ new String(str.getBytes(), utf8) + "\n");
			encodingMapping.write(value.toString() + "," + value2.toString() + "\n");
		    }
		} catch (Exception e) {
		    System.out.println("ERROR" + e.getStackTrace().toString());
		}
	    }
	}
	a.flush();
	a.close();
	encodingMapping.flush();
	encodingMapping.close();

	// System.out.println(System.getProperty("file.encoding"));
	// System.out.println(Charset.defaultCharset());
	// System.out.println(new InputStreamReader(null).getEncoding());
    }

    public static void method() throws UnsupportedTagException, InvalidDataException, IOException {
	File folder = new File("/home/charles/Music/OldMusic/버스커버스커1집/");
	File[] listOfFiles = folder.listFiles();

	for (File f : listOfFiles) {

	    // Mp3File mp3file = new
	    // Mp3File("/home/charles/Music/real_emotion.mp3");
	    Mp3File mp3file = new Mp3File(f);
	    System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
	    System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
	    System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
	    System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
	    System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
	    System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));

	    if (!mp3file.hasId3v1Tag()) {
		ID3v1 id3v1Tag = mp3file.getId3v1Tag();
		System.out.println("Track: " + id3v1Tag.getTrack());
		System.out.println("Artist: " + id3v1Tag.getArtist());
		System.out.println("Title: " + id3v1Tag.getTitle());
		System.out.println("Album: " + id3v1Tag.getAlbum());
		System.out.println("Year: " + id3v1Tag.getYear());
		System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
		System.out.println("Comment: " + id3v1Tag.getComment());
	    }
	    if (mp3file.hasId3v2Tag()) {
		ID3v2 id3v2Tag = mp3file.getId3v2Tag();
		System.out.println("Track: " + id3v2Tag.getTrack());
		System.out.println("Artist: " + id3v2Tag.getArtist());
		System.out.println("Title: " + id3v2Tag.getTitle());
		System.out.println("Album: " + id3v2Tag.getAlbum());
		System.out.println("Year: " + id3v2Tag.getYear());
		System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
		System.out.println("Comment: " + id3v2Tag.getComment());
		System.out.println("Composer: " + id3v2Tag.getComposer());
		System.out.println("Publisher: " + id3v2Tag.getPublisher());
		System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
		System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
		System.out.println("Copyright: " + id3v2Tag.getCopyright());
		System.out.println("URL: " + id3v2Tag.getUrl());
		System.out.println("Encoder: " + id3v2Tag.getEncoder());
		byte[] albumImageData = id3v2Tag.getAlbumImage();
		if (albumImageData != null) {
		    System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
		    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
		}
	    }
	}
    }

}
