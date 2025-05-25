package model;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;

public class ScoreSaver {
	private static final String PATH=System.getenv("APPDATA")+"\\SnakeJavaGame";
	private static final String FILEPATH_S=PATH+"\\scoresS.txt";
	private static final String FILEPATH_M=PATH+"\\scoresM.txt";
	public static void salvaS(String nick,int initSize,int score,int area,boolean w) throws IOException {
		Files.createDirectories(Paths.get(PATH));
		File file = new File(FILEPATH_S);
		file.createNewFile();
		String before = Files.readString(Paths.get(FILEPATH_S));
		FileWriter f = new FileWriter(file.getAbsolutePath());
		String s="";
		if(w) {
			s="on";
		}
		else {
			s="off";
		}
		f.write(nick+"×"+initSize+"×"+score+"×"+s+"×"+area+"\n"+before);
		f.close();
		
		
	}
	public static boolean hasFileS() {
		File f1 = new File(FILEPATH_S);
		return f1.exists();
	}
	public static boolean hasFileM() {
		File f2 = new File(FILEPATH_S);
		return f2.exists();
	}
	public static ArrayList<String[]> get(boolean b) throws IOException {
		String path="";
		if(b) {
			path = FILEPATH_M;
		}
		else {
			path = FILEPATH_S;
		}
		ArrayList<String[]> data = new ArrayList<String[]>();
		String str = Files.readString(Paths.get(path));
		for(String s : str.split("\n")) {
			data.add(s.split("\\×"));
		}
		if(!b) {
			for(int j=0;j<data.size()-1;j++) {
			for(int i=0;i<data.size()-1;i++) {
				// Inside your inner loop where you compare:
				if(Integer.valueOf(data.get(i)[2]) < Integer.valueOf(data.get(i+1)[2])) {
				    String[] temp = data.get(i); // Temporarily store the current element
				    data.set(i, data.get(i+1));   // Replace current with the next (larger) element
				    data.set(i+1, temp);          // Put the original current element into the next position
				}
			}
		}
			for(int i = 0;i< data.size();i++) {
				System.out.println(data.get(i)[2]);
			}
	}
		return data;
	}
	public static void salvaM(String p1,String p2,int vincitore) throws IOException {
		Files.createDirectories(Paths.get(PATH));
		File file = new File(FILEPATH_M);
		file.createNewFile();
		String before = Files.readString(Paths.get(FILEPATH_M));
		FileWriter f = new FileWriter(file.getAbsolutePath());
		String winner = "";
		switch(vincitore) {
		case 0:{
			winner="Pareggio";
		}break;
		case 1:{
			winner="Vince " + p2;
		}break;
		case 2:{
			winner="Vince " + p1;
		}break;
		}
		f.write(p1+"×"+p2+"×"+winner+"\n"+before);
		f.close();
		
		
	}
	
	
}
