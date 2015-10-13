package global.card.dungeon_card;

import global.*;
import global.card.*;
import global.card.dungeon_card.*;
import global.card.dungeon_card.enumeration.*;
import global.card.treasure_card.*;
import global.card.treasure_card.enumeration.*;

/**
 * the {@link Player} can fight against the {@link Monster} for win a level.
 * 
 * @author Rhuarc
 *
 */
public class Monster extends DungeonCard
	{
		/**
		 * category of this card
		 */
		private final String category;
		/**
		 * the level is not final, because the player can be up or down the
		 * level.
		 */
		private int level;

		private int levelGain;

		private int treasureGain;

		public Monster(MonsterSpecification monsterName)
			{
				super(monsterName.getName());
				this.category = DungeonCardCategory.monster.getName();
				this.level = monsterName.getLevel();
				this.levelGain = monsterName.getLevelGain();
				this.treasureGain = monsterName.getTreasureGain();
			}

		public String getCategory()
			{
				return this.category;
			}

		public int getLevel()
			{
				return this.level;
			}

		public void setLevel(int level)
			{
				this.level = level;
			}

		public int getLevelGain()
			{
				return this.levelGain;
			}

		public void setLevelGain(int levelGain)
			{
				this.levelGain = levelGain;
			}

		public int getTreasureGain()
			{
				return treasureGain;
			}

		public void setTreasureGain(int treasureGain)
			{
				this.treasureGain = treasureGain;
			}
		
		@Override
		public String toString()
		{
			String ch = "";
			if (this.getHiddenFace())
			{
				return ch = ch + " [" +this.category + "] : " +this.getName() + "("+this.level+") [Treasure Gain : " +this.treasureGain+", LevelGain : "+this.levelGain+"] \n";
			}
			else
			{
				return ch = ch + "["+this.getType()+"] \n";	
			}
			
		}
	}
