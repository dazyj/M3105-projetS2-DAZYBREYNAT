package global;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import global.card.treasure_card.*;

/**
 * The Equipment of the player.
 * It give him a bonus in the fights and can help us to win a monster.
 * @author Rhuarc
 *
 */
public class PlayerEquipment
	{
		/**
		 * A list of equipments.
		 */
		private List<Equipment> playerEquipement;
		
		/**
		 * the total bonus of the equipment.
		 */
		private int bonusTotal;
		
		/**
		 * create the equipment of a player.
		 */
		public PlayerEquipment()
			{
				this.playerEquipement = new ArrayList<Equipment>();
				this.setBonusTotal(0);
			}

		/**
		 * return the equipment.
		 * @return
		 */
		public List<Equipment> getEquipment()
			{
				return playerEquipement;
			}

		/**
		 * retur, the bonus total.
		 * @return
		 */
		public int getBonusTotal()
			{
					return bonusTotal;
			}

		/**
		 * edit the bonus total
		 * @param bonusTotal
		 */
		public void setBonusTotal(int bonusTotal)
			{
					this.bonusTotal = bonusTotal;
			}
		
		public String toString()
			{
				String ch ="";
				ListIterator<Equipment> iterator = this.playerEquipement.listIterator();
				while(iterator.hasNext())
					{
						ch = ch + "|" +iterator.next().toString() + "|";
					}
				return ch;

			}


	}
