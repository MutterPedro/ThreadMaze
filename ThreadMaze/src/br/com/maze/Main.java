package br.com.maze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		MazeConfig config = MazeConfig.getInstance("configuracao.txt");
		List<Player> players = new ArrayList<Player>();
		
		for(int i=0;i<config.getNumPlayers();i++){
			Player player = new Player("player"+i);
			players.add(player);
			player.start();
		}
		
		

	}

}
