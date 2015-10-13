package global.card.dungeon_card;

import global.card.DungeonCard;
import global.card.dungeon_card.enumeration.DungeonCardCategory;
import global.card.dungeon_card.enumeration.MonsterCurseSpecification;

public class MonsterCurse extends DungeonCard
	{

		private final String category;

		private final int monsterLevelEffect;

		private final int treasureCardEffect;

		public MonsterCurse(MonsterCurseSpecification Specification)
			{
				super(Specification.getName());
				this.category = DungeonCardCategory.monster_curse.getName();
				this.monsterLevelEffect = Specification.getMonsterLevelEffect();
				this.treasureCardEffect = Specification.getTreasureCardEffect();
			}

		public String getCategory()
			{
				return this.category;
			}

		public int getMonsterLevelEffect()
			{
				return monsterLevelEffect;
			}

		public int getTreasureCardEffect()
			{
				return treasureCardEffect;
			}

		/**
		 * this card can be change the level monster for a fight and gain.
		 * 
		 * @param monster
		 */
		public void useCurse(Monster monster)
			{
				monster.setLevel(monster.getLevel() + this.monsterLevelEffect);
				monster.setTreasureGain(monster.getTreasureGain()
						+ this.treasureCardEffect);
			}
		
		@Override
		public String toString()
		{
			if(this.getHiddenFace())
				{
					return "[" +this.getType()+" :"+this.category+"] "+this.getName()+"( bonus du monstre :"+this.monsterLevelEffect +", bonus en gain :"+this.treasureCardEffect+") \n";
				}
			else
				{
					return "["+this.getType()+"]";
				}
		}

	}
