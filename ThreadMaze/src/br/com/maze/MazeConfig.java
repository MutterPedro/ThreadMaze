package br.com.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeConfig {
	
	private int numPlayers;
	public static String[][] MAZEMAP;
	
	public int getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	public String[][] getMazeMap() {
		return MAZEMAP;
	}
	
	private MazeConfig(int numPlayers, String[][] mazeMap) {
		super();
		this.numPlayers = numPlayers;
		this.MAZEMAP = mazeMap;
	}
	
	public static MazeConfig getInstance(String filename) throws IOException{
		MazeConfig config = null;		
		File file = new File(filename);	
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
		int num = Integer.parseInt(line);
		List<String> map = new ArrayList<String>();
		while((line = reader.readLine()) != null){
			map.add(line);
		}
		reader.close();
		
		String stringMap[][] = new String[map.get(0).length()][map.size()];
		config = new MazeConfig(num,stringMap);
		
		return config;
	}
	
	
	
	
	
}
