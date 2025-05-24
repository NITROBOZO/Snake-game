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
	public static void main(String[] args) throws IOException {
		ScoreSaver.salvaS("pippo",6, 3, 3);
		ScoreSaver.salvaM("pippo","pluto", 1);
		ScoreSaver.get(true);	
	}
	public static void salvaS(String nick,int initSize,int score,int area) throws IOException {
		Files.createDirectories(Paths.get(PATH));
		File file = new File(FILEPATH_S);
		file.createNewFile();
		String before = Files.readString(Paths.get(FILEPATH_S));
		FileWriter f = new FileWriter(file.getAbsolutePath());
		f.write(nick+"."+initSize+"."+score+"."+area+"\n"+before);
		f.close();
		
		
	}
	public static ArrayList<String[]> get(boolean b) throws IOException {
		String path="";
		if(b) {
			path = FILEPATH_M;
		}
		else {
			path = FILEPATH_S;
		}
		File f = new File(path);
		ArrayList<String[]> data = new ArrayList<String[]>();
		if(!f.exists()) {
		}
		String str = Files.readString(Paths.get(path));
		for(String s : str.split("\n")) {
			data.add(s.split("\\."));
		}
		if(!b) {
			for(int j=0;j<data.size();j++) {
			for(int i=0;i<data.size()-1;i++) {
				if(Integer.valueOf(data.get(i)[2])<Integer.valueOf(data.get(i+1)[2])) {
					data.set(i, new String[] {data.get(i+1)[0],data.get(i+1)[1],data.get(i+1)[2],data.get(i+1)[3]});
				}
			}
		}
	}
		
		System.out.println(data.get(0)[2]);
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
			winner=p2;
		}break;
		case 2:{
			winner=p1;
		}break;
		}
		f.write(p1+"."+p2+"."+winner+"\n"+before);
		f.close();
		
		
	}
	
	
}
