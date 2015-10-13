package global.card.dungeon_card;

import global.card.DungeonCard;
import global.card.dungeon_card.enumeration.DungeonCardCategory;
import global.card.dungeon_card.enumeration.JobSpecification;



public class Job extends DungeonCard
	{
		/**
		 * category of this card
		 */
		private final String category;
		
		private final int bonus;
				
		private final int nbMaxCardBurnable;

		public Job(JobSpecification name)
			{
				super(name.getName());
				this.category = DungeonCardCategory.job.getName();
				this.bonus = name.getBonus();
				this.nbMaxCardBurnable = name.getNbMaxCardBurnable();
			}


		public int getBonus()
		{
			return this.bonus;
		}
		
		public int getNbMaxCardBurnable()
		{
			return this.nbMaxCardBurnable;
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
					return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( bonus :"+this.bonus +","+this.nbMaxCardBurnable+" carte defaussable en combat ) \n";
				}
			else
				{
					return "["+this.getType()+"] \n";
				}
		}

	}
