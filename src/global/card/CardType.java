package global.card;
/**
 * 
 * @author Zyra
 *
 */
public enum CardType
	{
		/**
		 * first type of card
		 */
		treasure("Treasure card"),
		/**
		 * secondly type of card
		 */
		dungeon("Dungeon card");
		
		private final String type;
		
		private CardType(String cardType)
		{
			this.type = cardType;
		}

		public String getType()
			{
					return type;
			}
		
	}
