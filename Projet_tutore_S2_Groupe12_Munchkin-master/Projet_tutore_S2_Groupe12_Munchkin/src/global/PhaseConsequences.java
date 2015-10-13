package global;


/**
 * The Phase Consequences of a Move.
 * It is the phase just after a fight or the putting of the first Dungeon Card.
 * So the phase threat the gains of the player in a combat, their defect (in the case of a fight); 
 * and the putting of a second Dungeon card (in the case of a non-fight).
 * @author Jérémie
 *
 */
public class PhaseConsequences
{
	
	/**
	 * Launch the phase.
	 */
	public static void start()
	{
		Player[] tabOfPlayers = Munchkin.getTabOfPlayers();
		if (PhaseDungeonCard1.isToFight())
		{
			if (FightTab.readIsWin())
			{
				if(FightTab.readHelper() == null)
				{
					System.out.println("vous avez gagnez :"+ FightTab.readMonster().getTreasureGain()+ "trésors.");
					for (int numberTreasure = 0; numberTreasure < FightTab.readMonster().getTreasureGain(); numberTreasure++)
					{
						tabOfPlayers[Move.getIdPlayersMove()].sendCard(Munchkin.getGameOfMunchkin().getTreasureHeap());
					}
					if (FightTab.readMonster().getLevelGain() != 0)
					{
						System.out.println("vous avez gagnez "+ FightTab.readMonster().getLevelGain()+ "niveaux");
						tabOfPlayers[Move.getIdPlayersMove()].updateLevel(FightTab.readMonster().getLevelGain());
					}
				}
				else
				{
					int GainLevelPlayer = Munchkin.getGameOfMunchkin().calculateGainPlayer(FightTab.readMonster().getLevelGain(), tabOfPlayers[Move.getIdPlayersMove()]);
					int GainCardPlayer = Munchkin.getGameOfMunchkin().calculateGainPlayer(FightTab.readMonster().getTreasureGain(),tabOfPlayers[Move.getIdPlayersMove()]);
					System.out.println("vous avez gagnez le combat avec "+FightTab.readHelper().getPseudo() +" !");
					System.out.println("vous gagnez "+GainCardPlayer+"trésors et "+GainLevelPlayer+"level");
					tabOfPlayers[Move.getIdPlayersMove()].updateLevel(Munchkin.getGameOfMunchkin().calculateGainPlayer(FightTab.readMonster().getLevelGain(), tabOfPlayers[Move.getIdPlayersMove()]));
					for (int numberCardForPlayer = 0; numberCardForPlayer <= Munchkin.getGameOfMunchkin().calculateGainPlayer(FightTab.readMonster().getTreasureGain(),tabOfPlayers[Move.getIdPlayersMove()]); numberCardForPlayer++)
					{
						tabOfPlayers[Move.getIdPlayersMove()].sendCard(Munchkin.getGameOfMunchkin().getTreasureHeap());
					}
					int gainCardHelper =Munchkin.getGameOfMunchkin().calculateGainHelper(FightTab.readMonster().getTreasureGain(),FightTab.readHelper());
					int gainLevelHelper =Munchkin.getGameOfMunchkin().calculateGainHelper(FightTab.readMonster().getLevelGain(),FightTab.readHelper());
					FightTab.readHelper().updateLevel(gainCardHelper);
					for (int numberCardForHelper = 0; numberCardForHelper <= gainCardHelper; numberCardForHelper++)
					{
						FightTab.readHelper().sendCard(Munchkin.getGameOfMunchkin().getTreasureHeap());
					}				
					System.out.println(FightTab.readHelper().getPseudo()+" a gagner" +gainCardHelper+"trésors et "+gainLevelHelper+"niveaux");					
				}			
			} 
			else
			{
				System.out.println("vous tentez de fuir !");
				if(Munchkin.getGameOfMunchkin().tryFlee(tabOfPlayers[Move.getIdPlayersMove()]))
				{
					System.out.println("vous avez réussi à fuir !");
				}
				else
				{
					System.out.println("le monstre vous rattrape et vous cogne !");
					if(tabOfPlayers[Move.getIdPlayersMove()].getLevel() <= 2)
					{
						System.out.println("vous êtes mort !");
						Munchkin.getGameOfMunchkin().deathPlayer(tabOfPlayers[Move.getIdPlayersMove()]);
						for (int k = 0; k < 4; k++)
							{
								Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].sendCard(Munchkin.getGameOfMunchkin().getDungeonHeap());
								Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].sendCard(Munchkin.getGameOfMunchkin().getTreasureHeap());
								Munchkin.getGameOfMunchkin().verifVoidHeap();
							}
					}
					else
					{
						System.out.println("vous perdez deux niveaux !");
						tabOfPlayers[Move.getIdPlayersMove()].updateLevel(-2);
					}
				}
				if(FightTab.readHelper() != null)
				{
					if(Munchkin.getGameOfMunchkin().tryFlee(FightTab.readHelper()))
					{
						System.out.println(FightTab.readHelper().getPseudo()+" réussis à fuir !");
					}
					else
					{
						System.out.println("le monstre rattrape et vous cogne !");
						if(FightTab.readHelper().getLevel() <= 2)
						{
							System.out.println("vous êtes mort !");
							Munchkin.getGameOfMunchkin().deathPlayer(FightTab.readHelper());
						}
						else
						{
							System.out.println("vous perdez deux niveaux !");
							FightTab.readHelper().updateLevel(-2);
						}
					}
				}
			}
		}
		else
		{
			System.out.println("vous pillez la pièce !");
			Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].getHand().getHandPlayer().add(Munchkin.getGameOfMunchkin().getDungeonHeap().RemoveFirstCard());
		}
	}
}