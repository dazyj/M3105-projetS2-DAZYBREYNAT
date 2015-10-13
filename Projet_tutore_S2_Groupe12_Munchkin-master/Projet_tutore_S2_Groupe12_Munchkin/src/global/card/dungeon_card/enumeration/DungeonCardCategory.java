package global.card.dungeon_card.enumeration;

public enum DungeonCardCategory
	{
		race("RACE"), job("JOB"), monster("MONSTER"), monster_curse(
				"MONSTER CURSE"), card_curse("CARD CURSE"),equipment("EQUIPEMENT");

		private final String name;

		private DungeonCardCategory(String name)
			{
				this.name = name;
			}

		public String getName()
			{
				return name;
			}
	}
