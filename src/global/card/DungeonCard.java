package global.card;




/**
 * the Dungeon Card are a specify type of the card
 * 
 * @author Zyra
 *
 */
public abstract class DungeonCard extends Card
	{
		
		private final String type;

		public DungeonCard(String name)
			{
				super(name);
				this.type = CardType.dungeon.getType();
			}


		public String getType()
			{
				return this.type;
			}

	}
