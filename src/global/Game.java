package global;

import java.util.Random;
import java.util.Scanner;

import global.card.*;
import global.card.dungeon_card.*;
import global.card.dungeon_card.enumeration.*;
import global.card.treasure_card.*;
import global.card.treasure_card.enumeration.*;

/**
 * The main object of the Munchkin.
 * This class allow the Player to play a game of Munchkin. It contains all the methods that control the game's phases or the actions relatives to the game (fight, etc...)
 * 
 * @author dazyj
 *
 */

public class Game
	{
		
		private Heap cardsDungeon;

		private Heap cardsTreasure;

		public Game()
			{
				this.cardsDungeon = new Heap(CardType.dungeon);
				this.cardsTreasure = new Heap(CardType.treasure);
			}

		/**
		 * Start the game, and verify before each move of the game, if a player has won the game.
		 */
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
			
			System.out.println("Félicitations " + Munchkin.getTabOfPlayers()[idPlayerWhoWin].getPseudo() + "Vous avez remporté la partie !");
		}

		/**
		 * Initialize the game.
		 * It distribute the cards to the players at the begin of the game.
		 */
		public void initializeGame()
			{
				for (int j = 0; j < Munchkin.getNbPlayer(); j++)
					{
						for (int k = 0; k < 4; k++)
						{
							Munchkin.getTabOfPlayers()[j].sendCard(getDungeonHeap());
							Munchkin.getTabOfPlayers()[j].sendCard(getTreasureHeap());
							this.verifVoidHeap();
						}
					}
			}

		/**
		 * Give the Dungeon Heap.
		 * @return
		 */
		public Heap getDungeonHeap()
			{
				return this.cardsDungeon;
			}

		/**
		 * Give the Treasure Heap.
		 * @return
		 */
		public Heap getTreasureHeap()
			{
				return this.cardsTreasure;
			}

		/**
		 * It identify a player with his id.
		 * The method use the if of the player to find it in the tabOfPlayers of the game and to return it.
		 * @param id
		 * @return
		 */
		public Player identifyPlayerById(int id)
			{
				for (int i = 0; i < Munchkin.getNbPlayer(); i++)
					{
						Player player = Munchkin.getTabOfPlayers()[i];
						if (player.getId() == id)
							return player;
					}
				return null;
			}
		
		public Player identifyPlayerByName(String name)
			{
				for (int i = 0; i < Munchkin.getNbPlayer(); i++)
					{
						Player player = Munchkin.getTabOfPlayers()[i];
						int compare = name.compareTo(player.getPseudo());
						if (compare == 0)
							return player;
					}
				return null;
			}

		/**
		 * Add buffs to Monster at the begin of a fight.
		 * We consult all the player to know if they want put a card to improve the monster.
		 * @param monster
		 */
		public void addBuffToMonster(Monster monster)
		{
			for (int indexOfPlayer = 0; indexOfPlayer < Munchkin.getNbPlayer(); indexOfPlayer++)
				{
					String iString = String.valueOf(indexOfPlayer);
					Scanner sc1 = new Scanner(System.in);
					String answer = "OUI";
					int compare = "OUI".compareTo(answer);
					while (compare == 0)
					{
						System.out.println("Bonjour ,"+Munchkin.getTabOfPlayers()[indexOfPlayer].getPseudo()+ " Voulez-vous ajouter un bonus au monstre ?");
						System.out.println(Munchkin.getTabOfPlayers()[indexOfPlayer].getHand().toString());
						answer = sc1.nextLine();
						answer.toUpperCase();
						switch (answer)
						{
							case "OUI":
								System.out.println("Choisissez une carte à poser. Rentrer le nom de la carte.");
								String name = sc1.nextLine();
								name.toUpperCase();
								Card card = Munchkin.getTabOfPlayers()[indexOfPlayer].chooseCardToPut(name);
								if (card instanceof ConsumableItem)
								{
									ConsumableItem itemCard = (ConsumableItem) card;
									FightTab.readMonster().setLevel(FightTab.readMonster().getLevel() + itemCard.getBonus());
								}
								if (card instanceof MonsterCurse)
								{
									MonsterCurse monsterCurseCard = (MonsterCurse) card;
									FightTab.readMonster().setLevel(
									FightTab.readMonster().getLevel() + monsterCurseCard.getMonsterLevelEffect());
									FightTab.readMonster().setTreasureGain(FightTab.readMonster().getTreasureGain() + monsterCurseCard.getTreasureCardEffect());
								}
								System.out.println(FightTab.readMonster().toString());
								compare = "OUI".compareTo(answer);
							case "NON":
								compare = "OUI".compareTo(answer);
						}
					}
				}
		}
		
		/**
		 * Add buffs to a player.
		 * We consult the player 2 times to ask him if he want add a buff different?
		 * The consumable item : it adds a value to the strength of the player that is considered just for the fight.
		 * The class Bonus : If the player's class is different from "human", he can remove a number of cards to add a bonus to his strength.
		 * If the player is a dwarf, we add him a bonus of strength.
		 * @param player
		 */
		public void addBufferToPlayer(Player player)
		{
			String response = "OUI";
			int compare = "OUI".compareTo(response);
			FightTab.setLevelBeforeP(FightTab.readPlayer().getLevel());
			if (FightTab.readHelper() != null)
				FightTab.setLevelBeforeH(FightTab.readHelper().getLevel());
			while (compare == 0)
			{
				String answer;
				System.out.println(player.getPseudo()+ ", Voulez-vous rajouter des bonus à vos dégâts ?");
				Scanner sc1 = new Scanner(System.in);
				answer = sc1.nextLine();
				answer.toUpperCase();
				switch (answer)
				{
					case "OUI":
						System.out.println("Voulez-vous ajouter un bonus de carte ? (consommable)?");
						String bonus = sc1.nextLine();
						bonus.toUpperCase();
						int test = "OUI".compareTo(bonus);
						if (test == 0)
						{
							System.out.println("Choisissez une carte à poser. Rentrer le nom de la carte.");
							String name = sc1.nextLine();
							name.toUpperCase();
							Card card = player.chooseCardToPut(name);
							if (card instanceof ConsumableItem)
							{
								ConsumableItem itemCard = (ConsumableItem) card;
								player.setStrength(player.getStrength() + itemCard.getBonus());
								
							}
						}
						if (player.getJob().getName() != null)
						{
							System.out.println("Voulez-vous ajouter un bonus de classe (Guerrier, Voleur, Prêtre)?");
							bonus = sc1.nextLine();
							bonus.toUpperCase();
							test = "OUI".compareTo(bonus);
							if (test == 0)
							{
								String maxString = String.valueOf(player.getJob().getNbMaxCardBurnable());
								String bonusString = String.valueOf(player.getJob().getBonus());
								System.out.println("Vous pouvez défausser " + maxString + " cartes utilisant chacune un bonus de " + bonusString);
								if (player.getJob().getNbMaxCardBurnable() != 0)
								{
									System.out.println("Combien de cartes voulez-vous défausser ?");
									int nbCardToBurn = sc1.nextInt();
									if (nbCardToBurn < player.getJob().getNbMaxCardBurnable())
									{
										int bonusHit = player.getJob().getBonus() * nbCardToBurn;
										player.setStrength(player.getStrength() + bonusHit);
									}
								}
							}
						}
						String nameRace = player.getRace().getName();
						int test2 = nameRace.compareTo("DWARF");
						if (test2 > 0)
						{
							FightTab.readMonster().setLevel(FightTab.readMonster().getLevel() - 1);
						}							
						System.out.println(player.toString());
						compare = "OUI".compareTo(response);
					case "NON":
						response = "NON";
						compare = "OUI".compareTo(response);
			
				}
			}
		}
		
		/**
		 * Ask to the player gave from the player of the fight if he wants help him.
		 * If he accept, we apply the buff adder to them and see the issue of the fight.
		 * If he refuse we apply the buff adder to the player of the fight alone and see the issue of the fight. 
		 * @param help
		 * @param monster
		 */
		public void askHelpToFight(String help, Monster monster)
		{
			Player playerOfTheFight = FightTab.readPlayer();
			switch (help)
			{
			case "OUI":
				System.out.println("Indiquez le pseudo du joueur concerné.");
				Scanner sc1 = new Scanner(System.in);
				String nickname;
				nickname = sc1.nextLine();
				for (int i = 0; i < Munchkin.getNbPlayer(); i++)
				{
					int compare2 = nickname.compareTo(Munchkin.getTabOfPlayers()[i].getPseudo());
					if (compare2 == 0)
						{
							System.out.println(nickname + ", êtes vous OK pour aider " + playerOfTheFight.getPseudo() + "?");
							String ok = sc1.nextLine();
							switch (ok)
							{
								case "OUI":
									FightTab.editHelper(Munchkin.getTabOfPlayers()[i]);
								case "NON":
									FightTab.editHelper(null);
							}
						}
						
				}
				if (FightTab.readHelper() != null)
				{
					Player helperOfTheFight = FightTab.readHelper();
					this.addBufferToPlayer(playerOfTheFight);
					this.addBufferToPlayer(helperOfTheFight);
					if (playerOfTheFight.getStrength() + helperOfTheFight.getStrength() > monster.getLevel())
						{
							FightTab.editIsWin(true);
						}
					else
						{
							FightTab.editIsWin(false);
						}
					String jobPlayer = playerOfTheFight.getClass().getName();
					String jobHelper = helperOfTheFight.getClass().getName();
					int test3 = jobPlayer.compareTo("Warrior");
					int test4 = jobHelper.compareTo("Warrior");
					if (test3 == 0 || test4 == 0)
						{
							if (playerOfTheFight.getStrength() == monster.getLevel())
								{
									FightTab.editIsWin(true);
								}
						}
					FightTab.readPlayer().setLevel(FightTab.getLevelBeforeP());
					FightTab.readHelper().setLevel(FightTab.getLevelBeforeH());
				}
				else
				{
					this.addBufferToPlayer(playerOfTheFight);
					if (playerOfTheFight.getStrength() > monster.getLevel())
						{
							FightTab.editIsWin(true);
						}
						else
						{
							FightTab.editIsWin(false);
						}
					String jobPlayer = playerOfTheFight.getClass().getName();
					int test4 = jobPlayer.compareTo("Warrior");
					if (test4 == 0)
					{
						if (playerOfTheFight.getStrength() == monster.getLevel())
						{
							FightTab.editIsWin(true);
						}
					}
					Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].setLevel(FightTab.getLevelBeforeP());	
				}
				PhaseDungeonCard1.setToFight(true);
				
			case "NON":
				FightTab.editHelper(null);
				this.addBufferToPlayer(playerOfTheFight);
				if (playerOfTheFight.getStrength() > monster.getLevel())
					{
						FightTab.editIsWin(true);
					}
					else
					{
						FightTab.editIsWin(false);
					}
				String jobPlayer = playerOfTheFight.getClass().getName();
				int test4 = jobPlayer.compareTo("Warrior");
				if (test4 == 0)
				{
					if (playerOfTheFight.getStrength() == monster.getLevel())
					{
						FightTab.editIsWin(true);
					}
				}
				Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].setLevel(FightTab.getLevelBeforeP());
				PhaseDungeonCard1.setToFight(true);
			}
		}
		
		/**
		 * Launch a fight against a monster.
		 * We ask if someone want help the player.
		 * @param monster
		 */
		public void fight(Monster monster)
		{
			System.out.println("Le Combat commence.");
			FightTab.editIsWin(false);
			FightTab.editPlayer(this.identifyPlayerById(Move.getIdPlayersMove()));
			FightTab.editMonster(monster);
			System.out.println(monster.toString());
			this.addBuffToMonster(monster);
			
			System.out.println("Voulez-vous qu'un joueur vous aide ?");
			Scanner sc1 = new Scanner(System.in);
			String help;
			help = sc1.nextLine();
			help.toUpperCase();
			this.askHelpToFight(help, monster);
		}
		
		/**
		 * Kill a player. 
		 * If a player is killed his level come back to the first and his hand is removed.
		 * @param playerdeath
		 */
		public void deathPlayer(Player playerdeath)
		{
			while (!playerdeath.getHand().getHandPlayer().isEmpty())
			{
				playerdeath.getHand().getHandPlayer().remove(0);
			}
			playerdeath.setLevel(1);
		}
		
		/**
		 * Calculate the gain of the helper after the victory of a fight.
		 * @param monsterGain
		 * @param helper
		 * @return
		 */
		public int calculateGainHelper(int monsterGain ,Player helper)
		{
			if(helper.getRace().getName() == "ELF")
				return (monsterGain/2) +1;
			return monsterGain/2;
		}
		/**
		 * Calculate the gain of a player after the victory of a fight.
		 * @param monsterGain
		 * @param player
		 * @return
		 */
		public int calculateGainPlayer(int monsterGain, Player player)
		{
			if(player.getRace().getName() == "Elf")
				return (monsterGain - (monsterGain/2)) + 1;
			return monsterGain - (monsterGain/2);
		}
		/**
		 * The method that try for a player to flee if he lose a fight.
		 * @param player
		 * @return
		 */
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
            /**
             * 
             */
            public void createAllCardCurse()
                    {
                            CardCurseSpecification[] myPossibleCardCurse =CardCurseSpecification.values();
                            for(int indexTableau = 0; indexTableau < myPossibleCardCurse.length; indexTableau++ )
                            {
                                    Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new CardCurse(myPossibleCardCurse[indexTableau]));
                            }
                    }
            /**
             * 
             */
            public void createAllCardJob()
                    {
                            JobSpecification[] myPossibleJob =JobSpecification.values();
                            for(int indexTableau = 0; indexTableau < myPossibleJob.length; indexTableau++ )
                            {
                                    Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new Job(myPossibleJob[indexTableau]));
                            }
                    }
            /**
             * 
             */
            public void createAllCardMonsterCurse()
                    {
                            MonsterCurseSpecification[] myPossibleMonsterCurse =MonsterCurseSpecification.values();
                            for(int indexTableau = 0; indexTableau < myPossibleMonsterCurse.length; indexTableau++ )
                            {
                                    Munchkin.getGameOfMunchkin().getDungeonHeap().getDeck().add(new MonsterCurse(myPossibleMonsterCurse[indexTableau]));
                            }
                    }
            /**
             * 
             */
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
            /**
             * 
             */
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


