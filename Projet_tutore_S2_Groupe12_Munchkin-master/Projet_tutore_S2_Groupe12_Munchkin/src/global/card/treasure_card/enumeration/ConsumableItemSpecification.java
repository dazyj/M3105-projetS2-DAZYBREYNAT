package global.card.treasure_card.enumeration;

public enum ConsumableItemSpecification
	{
		explosive_potion("EXPLOSIVE POTION",3),magic_missile("MAGIC MISSILE",5),BOMBE("BOMBE",3),CITRON("CITRON",0),spatule("Spatule",3),potion_de_rage("POTION DE RAGE",5),Processeuri7("PROCESSEUR I7",3);
		
		private final String name;
		
		private final int bonus;

		private ConsumableItemSpecification(String name, int bonus)
			{
				this.name = name;
				this.bonus = bonus;
			}

		public String getName()
			{
					return name;
			}

		public int getBonus()
			{
					return bonus;
			}

		

	}
