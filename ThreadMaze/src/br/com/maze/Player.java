package br.com.maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player extends Thread{
	
	private String playerName;
	private int row;
	private int col;
	private Map<String,int[]> moves;
	
	private volatile boolean running = true;

    public void terminate() {
        running = false;
    }
	
	public Player(String name) {
		super();
		this.playerName = name;
		moves = new HashMap<String,int[]>();
		int[] mov = new int[2];
		mov[0] = -1;
		mov[1] = 0;
		moves.put("UP", mov);
		mov[0] = 1;
		mov[1] = 0;
		moves.put("DOWN", mov);
		mov[0] = 0;
		mov[1] = 1;
		moves.put("RIGHT", mov);
		mov[0] = 0;
		mov[1] = -1;
		moves.put("LEFT", mov);
	}
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getMove(int x){
		switch(x){
			case 0:
				return "UP";
			case 1:
				return "RIGHT";
			case 2: 
				return "DOWN";
			case 3:
				return "LEFT";
			default:
				return "invalid move";
		}			
	}
	
	private void walk(){
		Random rand = new Random();
		int x = rand.nextInt(4);
		int mov[] = moves.get(this.getMove(x));
		while(((row+mov[0]) >= MazeConfig.MAZEMAP.length) ||
				((row+mov[0]) < 0) ||
				((col+mov[1]) < 0) ||
				((col+mov[1]) >= MazeConfig.MAZEMAP.length) ||
				(!MazeConfig.MAZEMAP[row+mov[0]][col+mov[1]].equals("0") &&
				!MazeConfig.MAZEMAP[row+mov[0]][col+mov[1]].equals("S"))){
			mov = moves.get(this.getMove(x));
		}
		MazeConfig.MAZEMAP[row][col] = "0";
		this.row += mov[0];
		this.col += mov[1];		
	}

	@Override
	public void run(){
		File file = new File(this.getPlayerName()+"_log.txt");
		
		try {
			if(!file.exists())
				file.createNewFile();
			
			PrintWriter writer = new PrintWriter(file);
			int i=0,j=0;
			while(!MazeConfig.MAZEMAP[i][j].equals("i")){
				if((j+1)>= MazeConfig.MAZEMAP.length){
					j=0;
					i++;	
				}
				else
					j++;
				if(i >= MazeConfig.MAZEMAP.length){
					System.out.println("Jogador "+this.getPlayerName()+" não achou ponto de inicio.");
					running = false;
					return;					
				}					
			}
			
			System.out.println("Jogador "+this.getPlayerName()+" achou ponto de inicio em "+i+","+j);
			this.row = i;
			this.col = j;
			MazeConfig.MAZEMAP[i][j] = "P";			
					
			while(running){
				writer.println("["+row+","+col+"]");
				walk();				
				if(MazeConfig.MAZEMAP[row][col].equals("S")){
					writer.write("Achou a saída\n");
					System.out.println("Jogador "+this.getPlayerName()+" achou saida.");
					running = false;
				}
				else
					MazeConfig.MAZEMAP[row][col] = String.valueOf(this.getId());
			}
			
			writer.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}
}
