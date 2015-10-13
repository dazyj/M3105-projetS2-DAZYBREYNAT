package global.card.dungeon_card.enumeration;

public enum JobSpecification
	{
		Noob("NOOB",0,0),
		Warrior("WARRIOR",2,3),
		Rogue("ROGUE",3,3),
		priest("PRIEST",1,3);
		
		private final String name;
		
		private final int bonus;
		
		private final int nbMaxCardBurnable;
		
		private JobSpecification(String name, int bonus, int nbMaxCard)
		{
			this.name = name;
			this.bonus= bonus;
			this.nbMaxCardBurnable = nbMaxCard;
		}

		public String getName()
			{
					return name;
			}

		public int getNbMaxCardBurnable()
			{
					return nbMaxCardBurnable;
			}

		public int getBonus()
			{
					return bonus;
			}
	}
