package global.card.treasure_card.enumeration;

import global.Player;

public enum LevelEffectSpecification
	{
		Steal_Level("VOL DE NIVEAUX", 1, 1), thousand_Gold_Piece("1000 PIECE", 1, 0), retrograder("RETROGRADER",0,1), Champignon1up("CHAMPIGNON",3,0),GRAAL("GRAAL",10,0);

		private final String name;
		/**
		 * number of level who gain the {@link Player}.
		 */
		private final int levelNumberUp;
		/**
		 * number of level who other {@link Player} can lose.
		 */
		private final int levelNumberDown;

		private LevelEffectSpecification(String name, int levelNumberUp,
				int levelNumberDown)
			{
				this.name = name;

				this.levelNumberUp = levelNumberUp;

				this.levelNumberDown = levelNumberDown;
			}

		public int getLevelNumberUp()
			{
				return levelNumberUp;
			}

		public int getLevelNumberDown()
			{
				return levelNumberDown;
			}

		public String getName()
			{
					return name;
			}

	}
