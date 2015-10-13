package global.card.dungeon_card.enumeration;

public enum MonsterCurseSpecification
	{
		mad("MAD",5, 1), huge("HUGE",10, 2), baby("BABY",-5, -1),rajeunnissement("Rajeunnissement",3,0),Furie("FURIE",3,1);
		private final String name;
		
		private final int monsterLevelEffect;
		
		private final int treasureCardEffect;
		
		private MonsterCurseSpecification(String name,int monsterLevelEffect, int treasureCardEffect)
		{
			this.name = name;
			this.monsterLevelEffect = monsterLevelEffect;
			this.treasureCardEffect = treasureCardEffect;
		}

		public int getMonsterLevelEffect()
			{
					return this.monsterLevelEffect;
			}

		public int getTreasureCardEffect()
			{
					return this.treasureCardEffect;
			}

		public String getName()
			{
					return name;
			}

	}
