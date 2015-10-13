package global;
import java.util.Scanner;

/**
 * The class that give possibility to the application to create the game by asking to the player informations about him.
 * The game is not created when we call the class.
 * @author dazyj
 *
 */

public class IHMPlayer
{
	
	/**
	 * Ask to the user the number of player for a Game.
	 * 
	 * @return
	 */
	public static int selectNbPlayers()
	{
		System.out.println("Sélectionnez le nombre de joueurs à intégrer à la partie.");
		Scanner sc1 = new Scanner(System.in);
		int nbPlayer;
		nbPlayer = sc1.nextInt();
		return nbPlayer;
	}
	
	/**
	 * Ask to the user his pseudo to create the Game.
	 * 
	 * @return
	 */
	public static String selectPseudo()
	{
		System.out.println("Sélectionnez le pseudo du joueur.");
		Scanner sc2 = new Scanner(System.in);
		String nickname;
		nickname = sc2.nextLine();
		return nickname;
		
	}
	
}
