package global;

import java.util.Random;
import java.util.Scanner;
import global.card.*;
import global.card.dungeon_card.*;
import global.card.dungeon_card.enumeration.*;
import global.card.treasure_card.*;
import global.card.treasure_card.enumeration.*;

public class Game
	{
		
		private Heap cardsDungeon;
		private Heap cardsTreasure;
		
		public Game()
		{
			this.cardsDungeon = new Heap(CardType.dungeon);
			this.cardsTreasure = new Heap(CardType.treasure);
		}

		public void startGame()
		{
			if (Move.getNbMove() == 0)
			{
				this.initializeGame();
			}
			boolean gameIsWon = false;
			int idPlayerWhoWin = 0;
			for (int i = 0; i < Munchkin.getNbPlayer(); i++)
			{
				if (Munchkin.getTabOfPlayers()[i].getLevel() == 10)
				{
					gameIsWon = true;
					idPlayerWhoWin = i;
				}
			}
			while (!gameIsWon)
			{
				Move.start();
			}
			
			System.out.println("F�licitations " + Munchkin.getTabOfPlayers()[idPlayerWhoWin].getPseudo() + "Vous avez remport� la partie !");
		}

		public void initializeGame()
		{
			for (int idPlayers = 0; idPlayers < Munchkin.getNbPlayer(); idPlayers++)
				{
					giveStartCardsToOnePlayer(idPlayers);
				}
		}

		private void giveStartCardsToOnePlayer(int idPlayers)
		{
			for (int nbCardByHeapToPull = 0; nbCardByHeapToPull < 4; nbCardByHeapToPull++)
			{
				Munchkin.getTabOfPlayers()[idPlayers].sendCard(getDungeonHeap());
				Munchkin.getTabOfPlayers()[idPlayers].sendCard(getTreasureHeap());
				this.verifVoidHeap();
			}
		}

		public Heap getDungeonHeap()
		{
			return this.cardsDungeon;
		}

		public Heap getTreasureHeap()
		{
			return this.cardsTreasure;
		}

		public Player identifyPlayerById(int id)
		{
			for (int idPlayer = 0; idPlayer < Munchkin.getNbPlayer(); idPlayer++)
			{
				Player player = Munchkin.getTabOfPlayers()[idPlayer];
				if (player.getId() == id)
					return player;
			}
			return null;
		}
		
		public Player identifyPlayerByName(String name)
		{
			for (int idPlayer = 0; idPlayer < Munchkin.getNbPlayer(); idPlayer++)
			{
				Player player = Munchkin.getTabOfPlayers()[idPlayer];
				int compareNameWithPlayerSPseudo = name.compareTo(player.getPseudo());
				if (compareNameWithPlayerSPseudo == 0)
					return player;
			}
			return null;
		}

		public void addBuffToMonster(Monster monster)
		{
			for (int indexOfPlayer = 0; indexOfPlayer < Munchkin.getNbPlayer(); indexOfPlayer++)
			{
				Scanner scanner1 = new Scanner(System.in);
				String answer = "OUI";
				int compareAnswerWithOUI = "OUI".compareTo(answer);
				while (compareAnswerWithOUI == 0)
				{
					questionToPlayerAddBuffMonster(indexOfPlayer, scanner1);
				}
			}
		}

		private void questionToPlayerAddBuffMonster(int indexOfPlayer,
				Scanner scanner1)
			{
				String answer;
				System.out.println("Bonjour ,"+Munchkin.getTabOfPlayers()[indexOfPlayer].getPseudo()+ " Voulez-vous ajouter un bonus au monstre ?");
				System.out.println(Munchkin.getTabOfPlayers()[indexOfPlayer].getHand().toString());
				answer = scanner1.nextLine();
				answer.toUpperCase();
				consequencesOfAnswer(indexOfPlayer, scanner1, answer);
			}

		private void consequencesOfAnswer(int indexOfPlayer, Scanner scanner1,
				String answer)
			{
				int compareAnswerWithOUI;
				switch (answer)
				{
					case "OUI":
						System.out.println("Choisissez une carte � poser. Rentrer le nom de la carte.");
						String name = scanner1.nextLine();
						name.toUpperCase();
						chooseCardAndTestTypeOfCard(indexOfPlayer, name);
						System.out.println(FightTab.readMonster().toString());
						compareAnswerWithOUI = "OUI".compareTo(answer);
					case "NON":
						compareAnswerWithOUI = "OUI".compareTo(answer);
				}
			}

		private void chooseCardAndTestTypeOfCard(int indexOfPlayer, String name)
			{
				Card cardToPut = Munchkin.getTabOfPlayers()[indexOfPlayer].chooseCardToPut(name);
				if (cardToPut instanceof ConsumableItem)
				{
					ConsumableItem itemCard = (ConsumableItem) cardToPut;
					FightTab.readMonster().setLevel(FightTab.readMonster().getLevel() + itemCard.getBonus());
				}
				if (cardToPut instanceof MonsterCurse)
				{
					MonsterCurse monsterCurseCard = (MonsterCurse) cardToPut;
					FightTab.readMonster().setLevel(
					FightTab.readMonster().getLevel() + monsterCurseCard.getMonsterLevelEffect());
					FightTab.readMonster().setTreasureGain(FightTab.readMonster().getTreasureGain() + monsterCurseCard.getTreasureCardEffect());
				}
			}
		
		public void addBufferToPlayer(Player player)
		{
			String response = "OUI";
			int compareWithOUI = "OUI".compareTo(response);
			FightTab.setLevelBeforeP(FightTab.readPlayer().getLevel());
			if (FightTab.readHelper() != null)
				FightTab.setLevelBeforeH(FightTab.readHelper().getLevel());
			while (compareWithOUI == 0)
			{
				String answer;
				System.out.println(player.getPseudo()+ ", Voulez-vous rajouter des bonus � vos d�g�ts ?");
				Scanner scanner1 = new Scanner(System.in);
				answer = scanner1.nextLine();
				answer.toUpperCase();
				switch (answer)
				{
					case "OUI":
						questionToPlayerAddBuffToPlayerTypeConsumable(player, scanner1);
						questionToPlayerAddBuffToPlayerTypeClass(player, scanner1);
						addBuffToPlayerDwarf(player);							
						System.out.println(player.toString());
						compareWithOUI = "OUI".compareTo(response);
					case "NON":
						response = "NON";
						compareWithOUI = "OUI".compareTo(response);	
				}
			}
		}

		private void addBuffToPlayerDwarf(Player player)
			{
				String nameRace = player.getRace().getName();
				int testCompWithDWARF = nameRace.compareTo("DWARF");
				if (testCompWithDWARF > 0)
				{
					FightTab.readMonster().setLevel(FightTab.readMonster().getLevel() - 1);
				}
			}

		private void questionToPlayerAddBuffToPlayerTypeClass(Player player, Scanner scanner1)
		{
			String bonusToAdd;
			int testCompWithOUI;
			if (player.getJob().getName() != null)
			{
				System.out.println("Voulez-vous ajouter un bonus de classe (Guerrier, Voleur, Pr�tre)?");
				bonusToAdd = scanner1.nextLine();
				bonusToAdd.toUpperCase();
				testCompWithOUI = "OUI".compareTo(bonusToAdd);
				if (testCompWithOUI == 0)
				{
					String stringMaxCardBurnable = String.valueOf(player.getJob().getNbMaxCardBurnable());
					String stringBonus = String.valueOf(player.getJob().getBonus());
					System.out.println("Vous pouvez d�fausser " + stringMaxCardBurnable + " cartes utilisant chacune un bonus de " + stringBonus);
					if (player.getJob().getNbMaxCardBurnable() != 0)
					{
						questionNumberCardsToPutToAddBuff(player, scanner1);
					}
				}
			}
		}

		private void questionNumberCardsToPutToAddBuff(Player player, Scanner scanner1)
		{
			System.out.println("Combien de cartes voulez-vous d�fausser ?");
			int nbCardToBurn = scanner1.nextInt();
			if (nbCardToBurn < player.getJob().getNbMaxCardBurnable())
			{
				int bonusHit = player.getJob().getBonus() * nbCardToBurn;
				player.setStrength(player.getStrength() + bonusHit);
			}
		}
		
		private void questionToPlayerAddBuffToPlayerTypeConsumable(
			Player player, Scanner scanner1)
		{
			System.out.println("Voulez-vous ajouter un bonus de carte ? (consommable)?");
			String bonusToAdd = scanner1.nextLine();
			bonusToAdd.toUpperCase();
			int testCompWithOUI = "OUI".compareTo(bonusToAdd);
			if (testCompWithOUI == 0)
			{
				System.out.println("Choisissez une carte � poser. Rentrer le nom de la carte.");
				String nameCardToPut = scanner1.nextLine();
				nameCardToPut.toUpperCase();
				Card card = player.chooseCardToPut(nameCardToPut);
				if (card instanceof ConsumableItem)
				{
					ConsumableItem itemCard = (ConsumableItem) card;
					player.setStrength(player.getStrength() + itemCard.getBonus());
					
				}
			}
		}
		
		public void askHelpToOtherPlayerToFight(String help, Monster monster)
		{
			Player playerInFight = FightTab.readPlayer();
			switch (help)
			{
			case "OUI":
				askHelp(playerInFight);
				actInFight(monster, playerInFight);
				PhaseDungeonCard1.setToFight(true);		
			case "NON":
				FightTab.editHelper(null);
				fightAlone(monster, playerInFight);
				PhaseDungeonCard1.setToFight(true);
			}
		}

		private void fightAlone(Monster monster, Player playerInFight)
		{
			this.addBufferToPlayer(playerInFight);
			if (playerInFight.getStrength() > monster.getLevel())
				{
					FightTab.editIsWin(true);
				}
				else
				{
					FightTab.editIsWin(false);
				}
			String jobPlayer = playerInFight.getClass().getName();
			int testCompPlayerWithWarrior = jobPlayer.compareTo("Warrior");
			if (testCompPlayerWithWarrior == 0)
			{
				if (playerInFight.getStrength() == monster.getLevel())
				{
					FightTab.editIsWin(true);
				}
			}
			Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].setLevel(FightTab.getLevelBeforeP());
		}

		private void actInFight(Monster monster, Player playerInFight)
		{
			if (FightTab.readHelper() != null)
			{
				fightTogether(monster, playerInFight);
			}
			else
			{
				fightAlone(monster, playerInFight);	
			}
		}
		
		private void fightTogether(Monster monster, Player playerInFight)
		{
			Player helperInFight = FightTab.readHelper();
			this.addBufferToPlayer(playerInFight);
			this.addBufferToPlayer(helperInFight);
			if (playerInFight.getStrength() + helperInFight.getStrength() > monster.getLevel())
				{
					FightTab.editIsWin(true);
				}
			else
				{
					FightTab.editIsWin(false);
				}
			String jobPlayer = playerInFight.getClass().getName();
			String jobHelper = helperInFight.getClass().getName();
			int testCompPlayerWithWarrior = jobPlayer.compareTo("Warrior");
			int testCompHelperWithWarrior = jobHelper.compareTo("Warrior");
			if (testCompPlayerWithWarrior == 0 || testCompHelperWithWarrior == 0)
				{
					if (playerInFight.getStrength() == monster.getLevel())
						{
							FightTab.editIsWin(true);
						}
				}
			FightTab.readPlayer().setLevel(FightTab.getLevelBeforeP());
			FightTab.readHelper().setLevel(FightTab.getLevelBeforeH());
		}
		
		private void askHelp(Player playerInFight)
		{
			System.out.println("Indiquez le pseudo du joueur concern�.");
			Scanner scanner1 = new Scanner(System.in);
			String nickname;
			nickname = scanner1.nextLine();
			for (int i = 0; i < Munchkin.getNbPlayer(); i++)
			{
				int compare2 = nickname.compareTo(Munchkin.getTabOfPlayers()[i].getPseudo());
				if (compare2 == 0)
				{
					System.out.println(nickname + ", �tes vous OK pour aider " + playerInFight.getPseudo() + "?");
					String ok = scanner1.nextLine();
					switch (ok)
					{
						case "OUI":
							FightTab.editHelper(Munchkin.getTabOfPlayers()[i]);
						case "NON":
							FightTab.editHelper(null);
					}
				}			
			}
		}
		
		public void fight(Monster monster)
		{
			System.out.println("Le Combat commence.");
			FightTab.editIsWin(false);
			FightTab.editPlayer(this.identifyPlayerById(Move.getIdPlayersMove()));
			FightTab.editMonster(monster);
			System.out.println(monster.toString());
			this.addBuffToMonster(monster);
			
			System.out.println("Voulez-vous qu'un joueur vous aide ?");
			Scanner scanner1 = new Scanner(System.in);
			String help;
			help = scanner1.nextLine();
			help.toUpperCase();
			this.askHelpToOtherPlayerToFight(help, monster);
		}
		
		public void deathPlayer(Player playerdeath)
		{
			while (!playerdeath.getHand().getHandPlayer().isEmpty())
			{
				playerdeath.getHand().getHandPlayer().remove(0);
			}
			playerdeath.setLevel(1);
		}
		
		public int calculateGainHelper(int monsterGain ,Player helper)
		{
			if(helper.getRace().getName() == "ELF")
				return (monsterGain/2) +1;
			return monsterGain/2;
		}

		public int calculateGainPlayer(int monsterGain, Player player)
		{
			if(player.getRace().getName() == "Elf")
				return (monsterGain - (monsterGain/2)) + 1;
			return monsterGain - (monsterGain/2);
		}

		public boolean tryFlee(Player player)
		{
			Random thimble = new Random();
			int thimbledodge = thimble.nextInt(6) + 1;
			if(player.getJob().getName() == "Priest")
			{
				thimbledodge += 1;
			}
			if(thimbledodge >=5)
			{
					return true;
			}
			else
				return false;
		}
		
		public void createAllCardMonster()
        {
        	MonsterSpecification[] myPossibleMonster =MonsterSpecification.values();
            	for(int indexTableau = 0; indexTableau < myPossibleMonster.length; indexTableau++ )
                {
                	Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new Monster(myPossibleMonster[indexTableau]));
                }
        }

        public void createAllCardCurse()
        {
        	CardCurseSpecification[] myPossibleCardCurse =CardCurseSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleCardCurse.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new CardCurse(myPossibleCardCurse[indexTableau]));
            }
        }

        public void createAllCardJob()
        {
        	JobSpecification[] myPossibleJob =JobSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleJob.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new Job(myPossibleJob[indexTableau]));
            }
        }
        
        public void createAllCardMonsterCurse()
        {
        	MonsterCurseSpecification[] myPossibleMonsterCurse =MonsterCurseSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleMonsterCurse.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new MonsterCurse(myPossibleMonsterCurse[indexTableau]));
            }
        }
        
        public void createAllCardRace()
        {
        	RaceSpecification[] myPossibleRace =RaceSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleRace.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new Race(myPossibleRace[indexTableau]));
            }
        }
        
        public void createAllCardConsumableItem()
        {
        	ConsumableItemSpecification[] myPossibleCards = ConsumableItemSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleCards.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getTreasureHeap().getDeck().add(new ConsumableItem(myPossibleCards[indexTableau]));
            }
        }
            
        public void createAllCardEquipment()
        {
        	EquipmentSpecification[] myPossibleCards = EquipmentSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleCards.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getTreasureHeap().getDeck().add(new Equipment(myPossibleCards[indexTableau]));
            }
        }
            
        public void createAllCardLevelEffect()
        {
        	LevelEffectSpecification[] myPossibleCards = LevelEffectSpecification.values();
            for(int indexTableau = 0; indexTableau < myPossibleCards.length; indexTableau++ )
            {
            	Munchkin.getGameOfMunchkin().getTreasureHeap().getDeck().add(new LevelEffect(myPossibleCards[indexTableau]));
            }
        }
            
        public void setDungeon(Heap dungeon)
        {
        	this.cardsDungeon = dungeon;
        }
            
        public void setTreasure(Heap treasure)
        {
         	this.cardsTreasure = treasure;
        }
            
        public void verifVoidHeap()
    	{
    		if(Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().isEmpty())
    		{
    			Munchkin.CreateHeapDungeon();
    		}
    		if(Munchkin.getGameOfMunchkin().getTreasureHeap().getDeck().isEmpty())
    		{
    			Munchkin.CreateHeapTreasure();
    		}
    	}
}