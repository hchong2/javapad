package com.charles.mp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Conv {
	
	public static List<Tuple2<Charset, Charset>> encodings = new ArrayList<Tuple2<Charset, Charset>>();
	public static String output = "output.txt";
	public static FileWriter fw = null;
	
	public static void main(String[] args) throws Exception{
		getEncodings();
		fw = new FileWriter(new File(output));
		
		String baseDir = "/home/charles/Music/OldMusic_dup/a_to_convert";
		process(new File(baseDir));
		
		fw.flush();
		fw.close();
	}
	
	public static void process(File file) throws Exception{
		System.out.println(file.getName());
		if(file.isDirectory()){
			File [] files = file.listFiles();
			for(File f: files){
				process(f);
			}
		}
		
		if(file.isFile()){
			if(checkAudioFile(file)){
				
				for(Tuple2<Charset, Charset> t: encodings){
					printMp3Metadata(file, t.getFirst(), t.getSecond());
//					break;
				}
			}
		}
	}
	
	public static boolean checkAudioFile(File file){
		List<String> acceptedExtensions = Arrays.asList("mp3");
		
		String filename = file.getName();
		String ext = FilenameUtils.getExtension(filename);
		
		return acceptedExtensions.contains(ext);
	}
	
	public static void printMp3Metadata(File mp3, Charset from, Charset to) throws UnsupportedTagException, InvalidDataException, IOException{
		try{
			Mp3File mp3file = new Mp3File(mp3);
			if (mp3file.hasId3v1Tag()) {
				ID3v1 id3v1Tag = mp3file.getId3v1Tag();
				String title = id3v1Tag.getTitle();
				String restored = restoreStringEncoding(title, from, to);
				String m = title + "\t" + restored + "\t" + from + "\t" + to + "\n";
				System.out.println(m);
				fw.write(m);
				
//				System.out.println("Track: " + id3v1Tag.getTrack());
//				System.out.println("Artist: " + id3v1Tag.getArtist());
//				System.out.println("Title: " + id3v1Tag.getTitle());
//				System.out.println("Album: " + id3v1Tag.getAlbum());
//				System.out.println("Year: " + id3v1Tag.getYear());
//				System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
//				System.out.println("Comment: " + id3v1Tag.getComment());
			}
//			String name = mp3.getName();
//			String restored = restoreStringEncoding(mp3.getName(), from, to);
//			String m = name + "\t" + restored + "\t" + from + "\t" + to + "\n";
//			if(name.equals(restored)){
//				System.out.println(m);
//				fw.write(m);
//				
//			}
		}
		catch(Exception e){
//			System.err.println("Error occurred with this file - " + mp3.getName());
		}
		
	}
	
	public static String restoreStringEncoding(String s, Charset from, Charset to){
		byte [] b = s.getBytes(from);
		String s_ = new String(b, to);
		return s_;
	}
	
//	public static List<Tuple2<Charset, Charset>> getEncodings() throws Exception{
		public static void getEncodings() throws Exception{
//		File f = new File("encodingMapping");
//		Path p = Paths.get(f.getPath());
//		List<Tuple2<Charset, Charset>> en = new ArrayList<Tuple2<Charset, Charset>>();
//		try{
//			InputStream in = Files.newInputStream(p);
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			
//			String line = null;
//			while( (line = br.readLine()) != null){
//				if(line.length() > 0 && line.contains(",")){
//					String [] split = line.split(",");
//					Tuple2<String, String> tuple = new Tuple2<String, String>(split[0], split[1]);
//					encodings.add(tuple);
//				}
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
		for(Map.Entry<String, Charset> entry: Charset.availableCharsets().entrySet()){
			Charset value = entry.getValue();
			
			for(Map.Entry<String, Charset> entry2: Charset.availableCharsets().entrySet()){
				try{
					Charset value2 = entry2.getValue();
					if(!value.equals(value2)){
						Tuple2<Charset, Charset> tuple = new Tuple2<Charset, Charset>(value, value2);
						encodings.add(tuple);
					}
				}
				catch(Exception e){
					System.out.println("ERROR" + e);
				}
			}
		}
		System.out.println("callmemaybe");
//		return en;
	}

}
