package global.card.treasure_card;

import global.Player;
import global.card.TreasureCard;
import global.card.treasure_card.enumeration.LevelEffectSpecification;
import global.card.treasure_card.enumeration.TreasureCardCategory;

/**
 * the levelEffect impact the {@link Player} level.
 * 
 * @author Rhuarc
 *
 */
public class LevelEffect extends TreasureCard
	{
		/**
		 * the category of {@link LevelEffect} is level_effect
		 */
		private final String category;
		/**
		 * number of level who gain the {@link Player}.
		 */
		private final int LevelNumberUp;
		/**
		 * number of level who other {@link Player} can lose.
		 */
		private final int levelNumberLess;


		public LevelEffect(LevelEffectSpecification name)
			{
				super(name.getName());
				this.category = TreasureCardCategory.level_effect.getName();
				this.LevelNumberUp = name.getLevelNumberUp();
				this.levelNumberLess = name.getLevelNumberDown();
			}

		public String getCategory()
			{
				return category;
			}



		public void effect(Player player, Player targetPlayer)
			{
				player.setLevel(player.getLevel() + this.LevelNumberUp);
				if(targetPlayer.getLevel() - this.levelNumberLess >= 1)
					{
						targetPlayer.setLevel(targetPlayer.getLevel() - this.levelNumberLess);
					}
				else
					{
						targetPlayer.setLevel(1);
					}
				
			}
		 public String toString()
             {
                     if(this.getHiddenFace())
                             {
                                     return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( Augmentation de niveaux :"+this.LevelNumberUp +", Suppression de niveaux :"+this.levelNumberLess+" ) \n";
                             }
                     else
                             {
                                     return "["+this.getType()+"] \n";
                             }
                     
             }

	}
