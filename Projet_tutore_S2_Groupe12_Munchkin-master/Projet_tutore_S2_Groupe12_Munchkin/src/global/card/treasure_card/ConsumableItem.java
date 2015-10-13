package global.card.treasure_card;

import global.card.TreasureCard;
import global.card.treasure_card.enumeration.ConsumableItemSpecification;
import global.card.treasure_card.enumeration.TreasureCardCategory;

/**
 * {@link ConsumableItem} have different effect
 * @author Rhuarc
 *
 */
public class ConsumableItem extends TreasureCard
	{
		/**
		 * name of the card
		 */
		
		private final int bonus;

		private final String category;

		public ConsumableItem(ConsumableItemSpecification name)
			{
				super(name.getName());
				this.category = TreasureCardCategory.item.getName();
				this.bonus = name.getBonus();
			}

		public String getCategory()
			{
					return this.category;
			}


		public int getBonus()
			{
					return bonus;
			}
		
		@Override
        public String toString()
        {
                if(this.getHiddenFace())
                        {
                                return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( bonus :"+this.bonus +") \n";
                        }
                else
                        {
                                return "["+this.getType()+"] \n";
                        }
                
        }
		

	}
