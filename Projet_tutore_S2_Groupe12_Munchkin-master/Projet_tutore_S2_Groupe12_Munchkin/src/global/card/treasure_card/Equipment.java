package global.card.treasure_card;

import global.card.TreasureCard;
import global.card.treasure_card.enumeration.EquipmentSpecification;
import global.card.treasure_card.enumeration.TreasureCardCategory;

/**
 * 
 * @author Zyra
 *
 */
public class Equipment extends TreasureCard
	{
		private final String category;

		private final int bonus;

		private final int value;

		/**
		 * @param name
		 * @param category
		 */
		public Equipment(EquipmentSpecification equipment)
			{
				super(equipment.getName());
				this.category = TreasureCardCategory.equipment.getName();
				this.bonus = equipment.getBonus();
				this.value = equipment.getValue();

			}
		
		public String getCategory()
			{
				return this.category;
			}

		public int getBonus()
			{
				return this.bonus;
			}

		public int getValue()
			{
				return this.value;
			}

		@Override
		public String toString()
		{
			if(this.getHiddenFace())
				{
					return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( bonus :"+this.bonus +", Valeur :"+this.value+" ) \n";
				}
			else
				{
					return "["+this.getType()+"] \n";
				}
			
		}

//		

	}
