package br.com.maze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		MazeConfig config = MazeConfig.getInstance("configuracao.txt");
		List<Player> players = new ArrayList<Player>();
		
		for(int i=0;i<config.getNumPlayers();i++){
			Player player = new Player("player"+i);
			players.add(player);			
		}
		
		for(int i=0;i<config.getNumPlayers();i++){
			players.get(i).start();			
		}
		
		Player winner = new Player("winner");
		winner.setNumberOfMoves(Long.MAX_VALUE);
		for(int i=0;i<config.getNumPlayers();i++){
			players.get(i).join();			
			if(players.get(i).getNumberOfMoves() < winner.getNumberOfMoves()){
				winner = players.get(i);
			}
		}
		
		System.out.println("O vencedor foi o "+winner.getPlayerName()+" com "+ winner.getNumberOfMoves()+ " movimentos.");
		
		

	}

}
