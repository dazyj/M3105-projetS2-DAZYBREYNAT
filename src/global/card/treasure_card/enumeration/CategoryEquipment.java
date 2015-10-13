package global.card.treasure_card.enumeration;

public enum CategoryEquipment
	{
		weapon_two_hand("Weapon two hand"), weapon_one_hand("Weapon one Hand"), 
		shoes("shoes"), Armor("Armor"), small_item("small item");
		
		private final String name;

		private CategoryEquipment(String name)
			{
				this.name = name;
			}

		public String getName()
			{
					return name;
			}
		
		

	}
