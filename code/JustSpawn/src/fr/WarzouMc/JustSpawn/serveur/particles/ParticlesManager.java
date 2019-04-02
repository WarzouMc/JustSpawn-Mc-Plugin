package fr.WarzouMc.JustSpawn.serveur.particles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.TypeGeo;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;

public class ParticlesManager implements Listener {

	private static main main;

	public ParticlesManager(main main) {this.main = main;}

	private static Inventory spawnParticle = Bukkit.createInventory(null, 27, "§2/spawn particles option");
	private Inventory spawnHelixInv = Bukkit.createInventory(null, 18, "§2/spawn helix particles option");

	private static ItemStack helixFi = new ItemStack(Material.FIREWORK_CHARGE);
	private static ItemMeta helixFiM = helixFi.getItemMeta();

	private static ItemStack BallFi = new ItemStack(Material.SLIME_BALL);
	private static ItemMeta BallFiM = BallFi.getItemMeta();

	private ItemStack redGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
	private ItemMeta redGlassM = redGlass.getItemMeta();

	private ItemStack greenGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)5);
	private ItemMeta greenGlassM = greenGlass.getItemMeta();

	private static ItemStack barrier = new ItemStack(Material.BARRIER);
	private static ItemMeta barrierM = barrier.getItemMeta();

	private static ItemStack Arrow = new ItemStack(Material.ARROW);
	private static ItemMeta ArrowM = Arrow.getItemMeta();

	@EventHandler
	public void onInventory(InventoryClickEvent e) {

		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		String pn = p.getName();

		ItemStack current = e.getCurrentItem();

		redGlassM.setDisplayName("§4Disable");
		redGlass.setItemMeta(redGlassM);

		greenGlassM.setDisplayName("§2Enable");
		greenGlass.setItemMeta(greenGlassM);

		if(inv.getName().equalsIgnoreCase("§2JustSpawn particles")) {

			e.setCancelled(true);

			if(current.getType() == Material.SLIME_BALL && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§1/spawn Particles : enable")) {

				p.closeInventory();

				SpawnParticles(p, "Spawn", "§2/spawn particles option");

			}else if(current.getType() == Material.SLIME_BALL && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§1Connection Particles : enable")) {

				p.closeInventory();

				SpawnParticles(p, "Connection", "§2Connection particles option");

			}

		}

	}

	public static void SpawnParticles(Player p, String path, String name) {

		String pn = p.getName();

		TypeGeo typeGeo = TypeGeo.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".TYPEGEO"));

		//helix

		spawnParticle = Bukkit.createInventory(null, 27, name);

		if(typeGeo == TypeGeo.HELIX) {

			helixFiM.setDisplayName("§2Helix");
			helixFiM.setLore(Arrays.asList("§1Enable", "§5§oHelix particle option !"));
			helixFiM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);

			BallFiM.setDisplayName("§4Ball");
			BallFiM.setLore(Arrays.asList("§4Disable", "§5§oBall particle option !"));
			BallFiM.removeEnchant(Enchantment.FIRE_ASPECT);

		}else if(typeGeo == TypeGeo.BALL){

			BallFiM.setDisplayName("§2Ball");
			BallFiM.setLore(Arrays.asList("§1Enable", "§5§oBall particle option !"));
			BallFiM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);

			helixFiM.setDisplayName("§4Helix");
			helixFiM.setLore(Arrays.asList("§4Disable", "§5§oHelix particle option !"));
			helixFiM.removeEnchant(Enchantment.FIRE_ASPECT);

		}

		helixFiM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		BallFiM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		helixFi.setItemMeta(helixFiM);
		BallFi.setItemMeta(BallFiM);

		spawnParticle.setItem(0, helixFi);
		spawnParticle.setItem(1, BallFi);

		barrierM.setDisplayName("§4Exit");
		barrier.setItemMeta(barrierM);

		ArrowM.setDisplayName("§ePrevious page");
		Arrow.setItemMeta(ArrowM);

		spawnParticle.setItem(26, Arrow);
		spawnParticle.setItem(18, barrier);

		p.openInventory(spawnParticle);

	}

}
