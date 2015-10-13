package global;

import global.card.*;
import global.card.dungeon_card.*;
import global.card.dungeon_card.enumeration.*;
import global.card.treasure_card.*;
import java.util.*;

/**
 * A player of the game. It represent the person that play to the game AND the
 * personage that they are in the game. A player has some stats and
 * characteristics that represents him and resolve many situations.
 * 
 * @author dazyj
 *
 */
public class Player
	{
		/**
		 * The name that the player use during the Game.
		 */
		private final String pseudo;

		/**
		 * The hand of the player where he stocks the cards that he hasn't shown
		 * to the others.
		 */
		private HandPlayer hand;

		/**
		 * The strenghth of the player. It is used in a fight against a monster.
		 */
		private int strength;

		/**
		 * The level of the player. If the level = 10, the player win the game.
		 */
		private int level;

		/**
		 * The race of the player.
		 */
		private Race race;

		/**
		 * The job of the player.
		 */
		private Job job;

		/**
		 * The id of the player. It identify him in the table of the players.
		 */
		private int idPlayer;

		/**
		 * The money of the player. 1000 = 1 level.
		 */
		private int money;

		/**
		 * The equipment of a player. It can increase his strength during a
		 * fight.
		 */
		private PlayerEquipment playerEquipment;

		/**
		 * Create a new player with his pseudo.
		 * 
		 * @param pseudo
		 */
		public Player(String pseudo)
			{
				/**
				 * The name that the player use during the Game.
				 */
				this.race = new Race(RaceSpecification.human);
				this.pseudo = pseudo;
				this.hand = new HandPlayer();
				this.level = 1;
				this.strength = this.level;
				this.money = 0;
				this.playerEquipment = new PlayerEquipment();
				this.job = new Job(JobSpecification.Noob);
			}

		/**
		 * The method that return the card that the player want to put.
		 * 
		 * @return
		 */
		public Card chooseCardToPut(String name)
			{
				ListIterator<Card> iterator = this.hand.getHandPlayer()
						.listIterator();
				while (iterator.hasNext())
					{
						int compare = name.compareTo(iterator.next().getName());
						if (compare == 0)
							{
								return this.hand.getHandPlayer().remove(
										iterator.previousIndex());
							}
					}
				return null;

			}

		/**
		 * The method that send a card to the hand of the player.
		 * 
		 * @param heap
		 */
		public void sendCardHand(Card card)
			{
					{
						card.setHiddenFace(true);
						this.hand.getHandPlayer().add(card);
					}

			}

		/**
		 * discard a card to the hand of player
		 * 
		 * @param indexCard
		 */
		public Card discard(int indexCard)
			{
				return this.hand.getHandPlayer().remove(indexCard);
			}

		/**
		 * The method that send a card from the heap to the hand of the player.
		 * 
		 * @param heap
		 */
		public void sendCard(Heap heap)
			{
				if (this.hand.getHandPlayer().size() < this.hand.getNbMaxCard())
					{
						Card cardRemove = heap.RemoveFirstCard();
						cardRemove.setHiddenFace(true);
						this.hand.getHandPlayer().add(cardRemove);
						Munchkin.getGameOfMunchkin().verifVoidHeap();
					}
			}

		public void updateLevel(int numberLevelChange)
			{
				this.level = (this.level + numberLevelChange);
			}

		/**
		 * this method can be search a card in the {@link HandPlayer}, move in
		 * the {@link PlayerEquipment}. this method update the bonusTotal to
		 * player
		 * 
		 * @param name
		 */
		public void equipEquipment(String name)
			{
				ListIterator<Card> iterator = this.hand.getHandPlayer().listIterator();
				while (iterator.hasNext())
					{
						int compare = name.compareTo(iterator.next().getName());
						if (compare == 0)
							{
								if (this.hand.getHandPlayer().get(iterator.previousIndex()) instanceof Equipment)
									{
										Equipment currentCard = (Equipment) this.hand.getHandPlayer().get(iterator.previousIndex());
										this.playerEquipment.getEquipment().add(currentCard);
										this.playerEquipment.setBonusTotal(this.playerEquipment.getBonusTotal() + currentCard.getBonus());
										this.updateStrength(currentCard.getBonus());
										this.hand.getHandPlayer().remove(currentCard);
										return;
									}

							}
					}
			}

		/**
		 * update the Strenght of the player when he equip or sell his item.
		 * 
		 * @param StrengthEffect
		 */
		public void updateStrength(int strengthEffect)
			{
				this.setStrength(this.getStrength() + strengthEffect);
			}

		/**
		 * this method search a {@link Card} with a name, and sell it against
		 * the money this method update the level to a player when he have 1000
		 * money.
		 * 
		 * @param name
		 */
		public void SellEquipment(String name)
			{
				ListIterator<Equipment> iterator = this.playerEquipment
						.getEquipment().listIterator();

				while (iterator.hasNext())
					{
						int compare = name.compareTo(iterator.next().getName());
						if (compare == 0)
							{
								Equipment currentCard = (Equipment) this.hand
										.getHandPlayer().get(
												iterator.previousIndex());
								this.money += +iterator.previous().getValue();
								this.updateStrength(-currentCard.getBonus());
								if (this.money >= 1000)
									{
										this.level += 1;
										this.money -= 1000;

									}
								iterator.remove();
								return;
							}
					}
			}

		/**
		 * Unequip a player from an equipment.
		 * 
		 * @param name
		 */
		public void unequip(String name)
			{
				ListIterator<Equipment> iterator = this.playerEquipment
						.getEquipment().listIterator();

				while (iterator.hasNext())
					{
						int compare = name.compareTo(iterator.next().getName());
						if (compare == 0)
							{
								Equipment currentCard = (Equipment) this
										.getPlayerEquipment().getEquipment()
										.remove(iterator.previousIndex());
								this.updateStrength(-currentCard.getBonus());
								this.hand.getHandPlayer().add(currentCard);

							}
					}
			}

		/**
		 * Discard a card in the hand of the player by using his name.
		 * 
		 * @param name
		 */
		public void discardPerName(String name)
			{
				ListIterator<Card> iterator = this.hand.getHandPlayer().listIterator();

				while (iterator.hasNext())
					{
						int compare = name.compareTo(iterator.next().getName());
						if (compare == 0)
							{
								iterator.remove();
							}
					}
			}

		/**
		 * return the id of a player
		 * 
		 * @return
		 */
		public int getId()
			{
				return this.idPlayer;
			}

		/**
		 * asert a id to the player.
		 * 
		 * @param i
		 */
		public void asertId(int i)
			{
				this.idPlayer = i;
			}

		/**
		 * return the level.
		 * 
		 * @return
		 */
		public int getLevel()
			{
				return this.level;
			}

		/**
		 * return the race
		 * 
		 * @return
		 */
		public Race getRace()
			{
				return this.race;
			}

		/**
		 * return the job.
		 * 
		 * @return
		 */
		public Job getJob()
			{
				return this.job;
			}

		/**
		 * set the race.
		 * 
		 * @param race
		 */
		public void setRace(Race race)
			{
				this.race = race;
			}

		/**
		 * set the job
		 * 
		 * @param job
		 */
		public void setJob(Job job)
			{
				this.job = job;
			}

		/**
		 * return the pseudo.
		 * 
		 * @return
		 */
		public String getPseudo()
			{
				return pseudo;
			}

		/**
		 * return the hand.
		 * 
		 * @return
		 */
		public HandPlayer getHand()
			{
				return hand;
			}

		/**
		 * return the strength.
		 * 
		 * @return
		 */
		public int getStrength()
			{
				return strength;
			}

		/**
		 * set the strength.
		 * 
		 * @param strength
		 */
		public void setStrength(int strength)
			{
				this.strength = strength;
			}

		/**
		 * return the equipment.
		 * 
		 * @return
		 */
		public PlayerEquipment getPlayerEquipment()
			{
				return this.playerEquipment;
			}

		/**
		 * set the level.
		 * 
		 * @param newLevel
		 */
		public void setLevel(int newLevel)
			{
				this.level = newLevel;

			}

		private int getMoney()
			{
				return this.money;
			}

		@Override
		public String toString()
			{
				String ch = "";
				ch = ch + " [" + this.getPseudo() + "] : Strength : ("+ this.getStrength() + ") Level : (" + this.getLevel()+ ") Race : (" + this.getRace().getName() + ") Job : ("+ this.getJob().getName() + ") Money : ("+ this.getMoney() + ") \n";
				ch = ch + " [" + this.getPseudo() + "] : Equipment : "+ this.getPlayerEquipment().toString();
				return ch;

			}

	}
