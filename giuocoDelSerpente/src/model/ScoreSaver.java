package model;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;

public class ScoreSaver {
	private static final String PATH=System.getenv("APPDATA")+"\\SnakeJavaGame";
	private static final String FILEPATH_S=PATH+"\\scoresS.snk";
	private static final String FILEPATH_M=PATH+"\\scoresM.snk";
	//salva dentro il file Singleplayer
	public static void salvaS(String nick,int initSize,int score,int area,int vel,boolean w) throws IOException {
		Files.createDirectories(Paths.get(PATH));
		File file = new File(FILEPATH_S);
		file.createNewFile();
		FileWriter f = new FileWriter(file.getAbsolutePath(),true);
		String s="";
		if(w) {
			s="on";
		}
		else {
			s="off";
		}
		f.write(nick+"×"+initSize+"×"+vel+"×"+score+"×"+s+"×"+area+"\n");
		f.close();
	}
	//controlla se esiste il file
	public static boolean hasFileS() {
		File f1 = new File(FILEPATH_S);
		return f1.exists();
	}
	public static boolean hasFileM() {
		File f2 = new File(FILEPATH_M);
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
		//prendi i dati dal file e li sposta in un array
		ArrayList<String[]> data = new ArrayList<String[]>();
		String str = Files.readString(Paths.get(path));
		for(String s : str.split("\n")) {
			data.add(s.split("\\×"));
		}
		if(!b) {
			// bubblesort
			for(int j=0;j<data.size()-1;j++) {
			for(int i=0;i<data.size()-1;i++) {
				if(Integer.valueOf(data.get(i)[3]) < Integer.valueOf(data.get(i+1)[3])) {
				    String[] temp = data.get(i); 
				    data.set(i, data.get(i+1));   
				    data.set(i+1, temp);      
				}
			}
		}
	}
		return data;
	}
	//salva dentro il file Multiplayer
	public static void salvaM(String p1,String p2,int vincitore) throws IOException {
		Files.createDirectories(Paths.get(PATH));
		File file = new File(FILEPATH_M);
		file.createNewFile();
		FileWriter f = new FileWriter(file.getAbsolutePath(),true);
		String winner = "";
		switch(vincitore) {
		case 0:{
			winner="Pareggio";
		}break;
		case 1:{
			winner=p2;
		}break;
		case 2:{
			winner=p1;
		}break;
		}
		f.write(p1+"×"+p2+"×"+winner+"\n");
		f.close();
		
		
	}
	
	
}
