package global;

import global.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * The hand of a player. 
 * It contains the cards of a player that are not equiped and that the player can put.
 * @author dazyj
 *
 */

public class HandPlayer
	{
		/**
		 * the {@link HandPlayer} is a list of {@link Card}
		 */
		private List<Card> handPlayer;
		
		/**
		 * the maximum number of cards that a hand can contain. 
		 */
		private final int maximumNumberCard;

		/**
		 * create the hand of a player.
		 */
		public HandPlayer()
			{
				this.maximumNumberCard = 5;
				this.handPlayer = new ArrayList<Card>();
			}
		
		/**
		 * return the hand of a player.
		 * @return
		 */
		public List<Card> getHandPlayer()
			{
					return handPlayer;
			}
		
		/**
		 * return the number maximum of cards in a hand.
		 * @return
		 */
		public int getNbMaxCard()
		{
			return this.maximumNumberCard;
		}
		
		@Override
		public String toString()
		{
			String ch ="";
			ListIterator<Card> iterator = this.getHandPlayer().listIterator();
			while(iterator.hasNext())
				{
					ch = ch +iterator.next().toString()  ;;
				}
			return ch;
		}


	}
