package markisha.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class GiveXP implements Listener {

	private List<Material> ores = new ArrayList<>(List.of(
				Material.DEEPSLATE_IRON_ORE,
				Material.IRON_ORE,
				Material.DEEPSLATE_GOLD_ORE,
				Material.GOLD_ORE,
				Material.DEEPSLATE_COPPER_ORE,
				Material.COPPER_ORE
			));
	
	private List<Material> pickaxes = new ArrayList<Material>(List.of(
				Material.DIAMOND_PICKAXE,
				Material.GOLDEN_PICKAXE,
				Material.IRON_PICKAXE,
				Material.NETHERITE_PICKAXE,
				Material.STONE_PICKAXE,
				Material.WOODEN_PICKAXE
			));
	
	private boolean holdingPickaxe(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();
		
		for (Material m : pickaxes) {
			if (i.getType().equals(m)) {
				if (i.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
					return false;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onOreBreak(BlockBreakEvent e) {
		if (!holdingPickaxe(e)) 
			return;
		
		Block b = e.getBlock();
		Material m = b.getType();
		World w = b.getWorld();
		
		for (int i = 0; i < ores.size(); i++) {
			if (m.equals(ores.get(i))) {
				double xp = 0;
				
				if (i == 0 || i == 1) {
					// iron - 2-4 xp
					xp = Math.random() * 3 + 2;
				
				} else if (i == 2 || i == 3) {
					// gold - 3-5 xp
					xp = Math.random() * 3 + 3;
					
				} else {
					// copper - 0-2 xp
					xp = Math.random() * 3;
					
				}
				
				if ((int) xp != 0)
					w.spawn(b.getLocation(), ExperienceOrb.class).setExperience((int) xp);
			}
		}
	}
	
}
