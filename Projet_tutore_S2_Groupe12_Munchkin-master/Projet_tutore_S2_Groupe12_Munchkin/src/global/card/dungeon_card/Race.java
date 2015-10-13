package global.card.dungeon_card;

import global.card.Card;
import global.card.DungeonCard;
import global.card.dungeon_card.enumeration.DungeonCardCategory;
import global.card.dungeon_card.enumeration.RaceSpecification;

/**
 * {@link Race} is a race for a player.
 * 
 * @author Zyra
 *
 */
public class Race extends DungeonCard
	{
		/**
		 * category of this {@link Card}
		 */
		private final String category;

		public Race(RaceSpecification specification)
			{
				super(specification.getName());
				this.category = DungeonCardCategory.race.getName();
			}


		public String getCategory()
			{
				return this.category;
			}
		
		@Override
		public String toString()
		{
			if(this.getHiddenFace())
				{
					switch(this.getName())
					{
						case ("ELF") :return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( vous gagnez plus 1 niveaux par combat ) \n";
						case ("DWARF") :return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( vous gagnez plus 1 de force en combat ) \n";
						case ("HUMAN") :return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( vous êtes nul ) \n";
						case ("NOOB") :return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( le nom parle de lui même )\n";
					}
						
					
				}
			else
				{
					return "["+this.getType()+"] \n";
				}
			return null;
		}

	}
