package global.card.treasure_card.enumeration;
/**
 * a equipment is defined by a name, a category of equipment a bonus and a value.
 * @author Zyra
 *
 */
public enum EquipmentSpecification
	{
		sword_of_bastard("BASTARD OF SWORD",2,400),Lance("LANCE",2,700),sword("EPEE",3,400),passoire("PASSOIRE",2,200),Gant_en_or("GANT EN OR",1,1000),aka47("AKA47",6,600),bague_blin_bling("BAGUE BLING BLING",1,1000),Livre_de_marc_levy("LIVRE DE MARC LEVY",1,3);

		private final String name;
		
		private final int bonus;
		
		private final int value;

		private EquipmentSpecification(String name,int bonus, int value)
			{
				this.name = name;
				this.bonus = bonus;
				this.value = value;
			}

		public String getName()
			{
					return this.name;
			}


		public int getBonus()
			{
					return bonus;
			}

		public int getValue()
			{
					return value;
			}
		
		
	}
