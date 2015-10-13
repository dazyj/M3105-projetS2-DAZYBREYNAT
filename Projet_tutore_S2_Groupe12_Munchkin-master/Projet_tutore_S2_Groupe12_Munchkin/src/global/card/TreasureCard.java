package global.card;


/**
 * the treasure card
 * 
 * @author Rhuarc
 *
 */
public abstract class TreasureCard extends Card
	{
		/**
		 * Type of the card
		 */

		private final CardType type;

		/**
		 * all the {@link TreasureCard} treasure have a treasure for type;
		 * 
		 * @param name
		 */
		public TreasureCard(String name)
			{
				super(name);
				this.type = CardType.treasure;
			}

		public CardType getType()
			{
				return type;
			}

	}
