package global;


/**
 * A move is an iteration of a scenario constitued of 3 phases.
 * 1. The first dungeon card'as phase
 * 2. The consequences phase
 * 3. The personal phase
 * A Move is applied for each player in iteration. 
 * The iteration stop when the game is won by one of the players.
 * 
 * @see PhaseDungeonCard1
 * @see PhaseConsequences
 * @see PhasePersonnalPhase
 * @see Game
 * @author dazyj
 *
 */
public class Move
	{
		/**
		 * The number of the move. 0 at the start.
		 */
		private static int nbMove = 0;
		
		/**
		 * The id of the player that play at the current move.
		 */
		private static int idPlayersMove = -1;
		
		/**
		 * start a move.
		 */
		public static void start()
		{
			nbMove++;
			if (idPlayersMove < Munchkin.getNbPlayer() - 1)
				idPlayersMove++;
			else 
				idPlayersMove = 0;
			Player playerMove = Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()];
			System.out.println("---------------- TOUR de "+playerMove.getPseudo()+" ----------------------- \n\n");
			System.out.println(playerMove.toString());
			System.out.println(playerMove.getHand().toString());
			PhaseDungeonCard1.start();
			System.out.println(playerMove.getHand().toString());
			PhaseConsequences.start();
			System.out.println(playerMove.getHand().toString());
			PhasePersonnalPhase.start();
			System.out.println(playerMove.getHand().toString());
			System.out.println(playerMove.toString());

		}
		
		/**
		 * return the number of the move.
		 * @return
		 */
		public static int getNbMove()
		{
			return nbMove;
		}
		
		/**
		 * return the id of the player that play at this move.
		 * @return
		 */
		public static int getIdPlayersMove()
		{
				return idPlayersMove;
		}
	}
