package global;

import global.card.Card;
import global.card.dungeon_card.CardCurse;
import global.card.dungeon_card.Job;
import global.card.dungeon_card.Monster;
import global.card.dungeon_card.MonsterCurse;
import global.card.dungeon_card.Race;

import java.util.Random;

/**
 * The phase Dungeon Card 1 of a Move.
 * This phase threat the events about the putting of the first Dungeon Card.
 * IF it is a monster, we launch a fight.
 * If it is a CardCurse, we apply the curse to the player.
 * If it is a MonsterCurse, a Job or a Race, the player can put the card in his hand.
 * @author Jérémie
 *
 */
public class PhaseDungeonCard1
{
	/**
	 * Boolean that determine if there had a fight during the phase.
	 */
	private static boolean toFight ;
	
	/**
	 * Launch the phase.
	 */
	public static void start()
	{
		System.out.println("Vous Ouvrez la porte !");
		Player[] tabOfPlayers = Munchkin.getTabOfPlayers();
		Card firstCardDungeon = (Card) Munchkin.getGameOfMunchkin().getDungeonHeap().RemoveFirstCard();
		System.out.println(firstCardDungeon.toString());
		if (firstCardDungeon instanceof Monster)
		{
			System.out.println("vous êtes attaquer par un monstre");
			Monster monster = (Monster) firstCardDungeon;
			Munchkin.getGameOfMunchkin().fight(monster);
		}
		if (firstCardDungeon instanceof Job || firstCardDungeon instanceof Race || firstCardDungeon instanceof MonsterCurse)
		{
			System.out.println("la carte est ajouter a votre main");
			tabOfPlayers[Move.getIdPlayersMove()].sendCardHand(firstCardDungeon);
		}
		if (firstCardDungeon instanceof CardCurse)
		{
			System.out.println("vous prenez une malediction dans la tronche !");
			for(int numberDiscardCard = 0; numberDiscardCard <= ((CardCurse) firstCardDungeon).getNumberCardRemove(); numberDiscardCard++);
			{	
			Random randomGenerator = new Random();
			tabOfPlayers[Move.getIdPlayersMove()].discard(randomGenerator.nextInt(tabOfPlayers[Move.getIdPlayersMove()].getHand().getHandPlayer().size()));
			}		
		}
	}
	
	/**
	 * return toFight
	 * @return
	 */
	public static boolean isToFight()
	{
		return toFight;
	}
	
	/**
	 * edit toFight.
	 * @param toFight
	 */
	public static void setToFight(boolean toFight)
	{
		PhaseDungeonCard1.toFight = toFight;
	}
}
