package global.card.dungeon_card.enumeration;

import global.*;
import global.card.*;
import global.card.dungeon_card.*;
import global.card.dungeon_card.enumeration.*;
import global.card.treasure_card.*;
import global.card.treasure_card.enumeration.*;

public enum MonsterSpecification
	{
		bailiff("HUISSIER",6,1,2),Gobelin("GOBELIN",3,1,1),licorn("LICORNE",5,2,2),Orc("ORC",9,3,4),Balrog("BALROG",25,6,8),fée("FEE",1,1,1),pepito("PEPITO",5,1,1),DEMON("DEMON",7,2,2),DRAGON("DRAGON",15,2,1),PAPATISSIER("PAPATISSIER",2,2,2),Geant("GEANT",7,3,2),Pikachu("PIKACHU",7,2,1);
		
		private final String name;
		
		private final int level;
		
		private final int levelGain;
		
		private final int treasureGain;
		
		private MonsterSpecification(String name, int level, int levelGain, int treasureGain)
		{
			this.name = name;
			this.level = level;
			this.levelGain = levelGain;
			this.treasureGain = treasureGain;
		}

		public int getLevel()
			{
					return this.level;
			}

		public int getLevelGain()
			{
					return this.levelGain;
			}

		public int getTreasureGain()
			{
					return this.treasureGain;
			}

		public String getName()
			{
					return this.name;
			}

		

	}
