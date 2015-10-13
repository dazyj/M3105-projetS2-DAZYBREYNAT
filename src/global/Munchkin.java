package global;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import global.card.DungeonCard;
import global.card.treasure_card.Equipment;
import global.card.treasure_card.enumeration.EquipmentSpecification;

//TODO JAAVADOC
//TODO TOSTRING
//TODO TESTS
//TODO CREER CARTES

/**
 * The application of the Munchkin.
 * It create a game, create the cards and launch a game.
 * @author dazyj
 *
 */

public class Munchkin
{

	/**
	 * The number of player in the game.
	 */
	private static int nbPlayer;
	
	/**
	 * The table that contains all the player of the game.
	 */
	private static Player[] tabOfPlayers;
	
	/**
	 * The game.
	 */
	private static Game gameOfMunchkin;
	
	/**
	 * The execution of the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Bienvenue dans le Munchkin, nous allons procéder à la création d'une nouvelle partie.");
		nbPlayer = IHMPlayer.selectNbPlayers();
		createGame();
	}

	/**
	* The processus that create the Game and launch it.  
	*/
	public static void createGame()
	{
		gameOfMunchkin = new Game();
		tabOfPlayers = new Player[Munchkin.nbPlayer];
		
		for (int i = 0; i < Munchkin.nbPlayer; i++)
		{	
			String name = IHMPlayer.selectPseudo();
			Player temp = new Player(name);
			tabOfPlayers[i] = temp;
			tabOfPlayers[i].asertId(i);
		}
		CreateHeapDungeon();
		Collections.shuffle(gameOfMunchkin.getDungeonHeap().getDeck());
		CreateHeapTreasure();
		Collections.shuffle(gameOfMunchkin.getTreasureHeap().getDeck());
		gameOfMunchkin.startGame();
	}
	
	/**
	 * return the number of players in the game.
	 * @return
	 */
	public static int getNbPlayer()
	{
		return nbPlayer;
	}
	
	/**
	 * return the table of the players.
	 * @return
	 */
	public static Player[] getTabOfPlayers()
	{
		return tabOfPlayers;
	}
	
	/**
	 * return the game.
	 * @return
	 */
	public static Game getGameOfMunchkin()
	{
		return gameOfMunchkin;
	}
	
	public static void  CreateHeapDungeon()
        {
        	gameOfMunchkin.createAllCardCurse();
            gameOfMunchkin.createAllCardJob();
            gameOfMunchkin.createAllCardMonster();
            gameOfMunchkin.createAllCardMonsterCurse();
            gameOfMunchkin.createAllCardRace();        
        }
        
        public static void CreateHeapTreasure()
        {
            gameOfMunchkin.createAllCardEquipment();
            gameOfMunchkin.createAllCardConsumableItem();
            gameOfMunchkin.createAllCardLevelEffect();
        }
}
