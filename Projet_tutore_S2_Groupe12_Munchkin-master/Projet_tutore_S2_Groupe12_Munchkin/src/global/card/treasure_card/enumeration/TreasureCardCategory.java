package global.card.treasure_card.enumeration;

public enum TreasureCardCategory
	{

		equipment("equipment"),
		item("item"),
		level_effect("level effect"),
		bonus("bonus");
		 private final String name;

		private TreasureCardCategory(String name)
			{
				this.name = name;
			}

		public String getName()
			{
					return name;
			}
		 
		 
	}
