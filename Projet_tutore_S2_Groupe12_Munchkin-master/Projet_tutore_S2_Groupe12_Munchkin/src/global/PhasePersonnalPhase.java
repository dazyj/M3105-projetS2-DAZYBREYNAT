package global;

import global.card.Card;
import global.card.TreasureCard;
import global.card.dungeon_card.Job;
import global.card.dungeon_card.Race;
import global.card.treasure_card.LevelEffect;

import java.util.Scanner;

/**
 * The Personnal phase of a Move.
 * It is the phase where the players must manage their hand to have a maximum of 5 cards in.
 * To do that they can do commerce with the others players or remove cards of their game, or sell cards to the game (to win level).
 * @author dazyj
 *
 */
public class PhasePersonnalPhase
{

	/**
	 * Launch the phase.
	 */
	public static void start()
	{
			PhaseDungeonCard1.setToFight(false);
			boolean test = false;
			while (Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].getHand().getHandPlayer().size() > 5 || test == false)
			{
				
				test = true;
				boolean choice = true;
				while (choice)
				{
					System.out.println("Souhaitez-vous déséquiper des objets ?");
					Scanner sc1 = new Scanner(System.in);
					String ok = sc1.nextLine();
					ok.toUpperCase();
					switch (ok)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner l'équipement à déséquiper (nom carte)");
							String nameCardDesequip = sc1.nextLine();
							nameCardDesequip.toUpperCase();
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].unequip(nameCardDesequip);
						case "NON":
							choice = false;
					}
					
				}
				boolean confirm = true;
				while (confirm)
				{
					System.out.println("Souhaitez-vous commercer avec les autres joueurs ?");
					Scanner sc1 = new Scanner(System.in);
					String answer = sc1.nextLine();
					answer.toUpperCase();
					switch (answer)
					{
						case "OUI":
							System.out.println("Sélectionnez un objet de votre main à échanger (équipement ou objet consommable)");
							String nameItem = sc1.nextLine();
							nameItem.toUpperCase();
							Card card = Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].chooseCardToPut(nameItem);
							TreasureCard cardToTrade = (TreasureCard) card;
							for (int indexOfPlayers = 0; indexOfPlayers < Munchkin.getNbPlayer(); indexOfPlayers++)
							{
								System.out.println(cardToTrade.toString());
								if (indexOfPlayers != Move.getIdPlayersMove())
								{
									System.out.println(Munchkin.getTabOfPlayers()[indexOfPlayers].getPseudo() + " Voulez-vous obtenir cette carte en échange d'une autre ?");
									String approve = sc1.nextLine();
									approve.toUpperCase();
									int compare = approve.compareTo("OUI");
									if (compare == 0)
									{
										System.out.println(Munchkin.getTabOfPlayers()[indexOfPlayers].getHand().getHandPlayer().toString());
										System.out.println("Sélectionnez un objet de votre main à échanger (équipement ou objet consommable)");
										String nameItem2 = sc1.nextLine();
										nameItem2.toUpperCase();
										TreasureCard cardToTrade2 = (TreasureCard) Munchkin.getTabOfPlayers()[indexOfPlayers].chooseCardToPut(nameItem2);
										System.out.println(cardToTrade2.toString());
										System.out.println(Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].getPseudo() + " confirmez-vous l'échange ?");
										approve = sc1.nextLine();
										approve.toUpperCase();
										if (compare == 0)
										{
											Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].sendCardHand(cardToTrade2);
											Munchkin.getTabOfPlayers()[indexOfPlayers].sendCardHand(cardToTrade);
										}
										break;
									}
									
								}
								
							}						
						case "NON":
							confirm = false;
					}
				}
				boolean equip = true;
				while (equip)
				{
					System.out.println("Voulez-vous équiper un objet ?");
					Scanner sc1 = new Scanner(System.in);
					String agree = sc1.nextLine();
					agree.toUpperCase();
					switch (agree)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner l'objet à équiper (nom objet)");
							String objectToEquip = sc1.nextLine();
							objectToEquip.toUpperCase();
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].equipEquipment(objectToEquip);
						case "NON":
							equip = false;
					}
				}
				boolean sell = true;
				while (sell)
				{
					System.out.println("Voulez-vous vendre un objet ? 1000 PO = 1 niveau.");
					Scanner sc1 = new Scanner(System.in);
					String agree = sc1.nextLine();
					agree.toUpperCase();
					switch (agree)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner l'objet a  Vendre (nom objet).");
							String objectToSell = sc1.nextLine();
							objectToSell.toUpperCase();
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].SellEquipment(objectToSell);
						case "NON":
							sell = false;
					}
				}
				boolean trash = true;
				while (trash)
				{
					System.out.println("Voulez-vous jeter un objet ?");
					Scanner sc1 = new Scanner(System.in);
					String putOnTheTrash = sc1.nextLine();
					putOnTheTrash.toUpperCase();
					switch (putOnTheTrash)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner l'objet à jeter (nom objet).");
							String objectToPutOnTheTrash = sc1.nextLine();
							objectToPutOnTheTrash.toUpperCase();
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].discardPerName(objectToPutOnTheTrash);
						case "NON":
							trash = false;
					}
				}
				boolean levelEffect = true;
				while (levelEffect)
				{
					System.out.println("Voulez-vous utiliser une carte gain de niveau ?");
					Scanner sc1 = new Scanner(System.in);
					String gainLevel = sc1.nextLine();
					gainLevel.toUpperCase();
					switch (gainLevel)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner la carte à utiliser (nom objet).");
							String cardLevel = sc1.nextLine();
							cardLevel.toUpperCase();
							Card cardToPlay = Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].chooseCardToPut(cardLevel);
							LevelEffect card = (LevelEffect) cardToPlay;
							System.out.println("Veuillez sélectionner un joueur à qui faire perdre des niveaux (si c'est possible) (nom objet).");
							String nameTargetPlayer = sc1.nextLine();
							nameTargetPlayer.toUpperCase();
							card.effect(Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()], Munchkin.getGameOfMunchkin().identifyPlayerByName(nameTargetPlayer));
						case "NON":
							levelEffect = false;
					}
				}
				boolean job = true;
				while (job)
				{
					System.out.println("Voulez-vous poser une carte de job ?");
					Scanner sc1 = new Scanner(System.in);
					String testJob = sc1.nextLine();
					testJob.toUpperCase();
					switch (testJob)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner la carte à poser (nom objet).");
							String cardJob = sc1.nextLine();
							cardJob.toUpperCase();
							Card cardJobPut = Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].chooseCardToPut(cardJob);
							Job card = (Job) cardJobPut;
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].setJob(card);
						case "NON":
							job = false;
					}
				}
				boolean race = true;
				while (race)
				{
					System.out.println("Voulez-vous poser une carte de race ?");
					Scanner sc1 = new Scanner(System.in);
					String testRace = sc1.nextLine();
					testRace.toUpperCase();
					switch (testRace)
					{
						case "OUI":
							System.out.println("Veuillez sélectionner la carte à poser (nom objet).");
							String cardRace = sc1.nextLine();
							cardRace.toUpperCase();
							Card cardRacePut = Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].chooseCardToPut(cardRace);
							Race card = (Race) cardRacePut;
							Munchkin.getTabOfPlayers()[Move.getIdPlayersMove()].setRace(card);
						case "NON":
							race = false;
					}
				}
			}
	}
}
