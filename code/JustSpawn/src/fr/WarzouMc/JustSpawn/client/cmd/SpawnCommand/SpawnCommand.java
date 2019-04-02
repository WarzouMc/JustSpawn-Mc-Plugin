package fr.WarzouMc.JustSpawn.client.cmd.SpawnCommand;

import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticleInstance;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticlesPlayer;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;
import fr.WarzouMc.JustSpawn.main;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {

	private static main main;
	
	private ArrayList<FireworkEffect.Type> fT = new ArrayList<>();
	private ArrayList<Color> c = new ArrayList<>();
	
	public SpawnCommand(main main) {this.main = main;}
	
	private static Inventory particle = Bukkit.createInventory(null, 9, "§2JustSpawn particles");
	
	private static ItemStack SlimeBallCMD = new ItemStack(Material.SLIME_BALL);
	private static ItemMeta SlimeBallCMDm = SlimeBallCMD.getItemMeta();

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public boolean onCommand(CommandSender sender, Command msg, String cmd, String[] args) {
		
		Player p = (Player) sender;
		String pn = p.getName();
		
		Location spawn = new Location(p.getWorld(), main.getConfig().getDouble("spawn.Location.x"), main.getConfig().getDouble("spawn.Location.y"), main.getConfig().getDouble("spawn.Location.z"));
		
		if(sender instanceof Player) {
			
			if(cmd.equalsIgnoreCase("spawn")) {
				
				if(args.length == 0) {
					
					p.teleport(spawn);
					
					if(main.getConfig().getBoolean("spawn.cmd.Particles") == true) {
						
						if(!main.getPlayerParticulesConfig().getConfigurationSection("Player.").contains(pn)) {
							
							ParticlesPlayer.Connect(pn);
							ParticlesPlayer.Spawn(pn);
							
						}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Connection")) {
							
							ParticlesPlayer.Connect(pn);
							
						}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Spawn")) {
							
							ParticlesPlayer.Spawn(pn);
							
						}
						
						Types type = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+".Spawn.TYPE"));
						
						main.setType(type);
						
						String path = pn+".Spawn";
						
						ParticleInstance.Instance(p, path, 0, pn);
						
					}
					
					if(main.getConfig().getBoolean("spawn.cmd.Message.Enable") == true) {
						
						String message = main.getConfig().getString("spawn.cmd.Message.msg").replaceAll("&", "§");
						
						if(main.getConfig().getBoolean("spawn.cmd.Message.ForAll") == false) {
							
							p.sendMessage(message);
							
						}else {
							
							Bukkit.broadcastMessage(message);
							
						}
						
					}
					
					String title = main.getConfig().getString("spawn.cmd.Title.title").replaceAll("&", "§");
					String subTitle = main.getConfig().getString("spawn.cmd.Title.subTitle").replaceAll("&", "§");
					
					if(main.getConfig().getBoolean("spawn.cmd.Fireworks") == true) {
						
						Location floc = new Location(p.getWorld(), main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.x"), main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.y"), main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.z"));
						
						fT.add(Type.BALL);
						fT.add(Type.BALL_LARGE);
						fT.add(Type.BURST);
						fT.add(Type.CREEPER);
						fT.add(Type.STAR);
						
						//5

                        c.add(Color.BLACK);
                        c.add(Color.RED);
                        c.add(Color.fromRGB(4, 72, 5));
                        c.add(Color.fromRGB(72, 23, 0));
                        c.add(Color.BLUE);
                        c.add(Color.fromRGB(114, 0, 176));
                        c.add(Color.fromRGB(0, 187, 254));
                        c.add(Color.fromRGB(163, 163, 163));
                        c.add(Color.fromRGB(75, 75, 75));
                        c.add(Color.fromRGB(255, 46, 255));
                        c.add(Color.LIME);
                        c.add(Color.YELLOW);
                        c.add(Color.fromRGB(0, 97, 253));
                        c.add(Color.fromRGB(169, 0, 253));
                        c.add(Color.fromRGB(252, 84, 0));
                        c.add(Color.WHITE);
						
						//17

                        if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FADE") == true){

                            FireworkEffect fe1 = FireworkEffect.builder()
                                    .flicker(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER"))
                                    .withColor(c.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")))
                                    .withFade(c.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")))
                                    .with(fT.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")))
                                    .trail(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL"))
                                    .build();

                            Firework f = (Firework)p.getWorld().spawnEntity(floc, EntityType.FIREWORK);
                            f.detonate();
                            FireworkMeta fM = f.getFireworkMeta();

                            fM.setPower(main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                            fM.addEffect(fe1);
                            f.setFireworkMeta(fM);

                        }else {

                            FireworkEffect fe1 = FireworkEffect.builder()
                                    .flicker(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER"))
                                    .withColor(c.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")))
                                    .with(fT.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")))
                                    .trail(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL"))
                                    .build();

                            Firework f = (Firework)p.getWorld().spawnEntity(floc, EntityType.FIREWORK);
                            f.detonate();
                            FireworkMeta fM = f.getFireworkMeta();

                            fM.setPower(main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                            fM.addEffect(fe1);
                            f.setFireworkMeta(fM);

                        }
						
					}
					
					if(main.getConfig().getInt("spawn.cmd.Title.PlayerNameOn") == 0) {
						
						String Apn = title.substring((main.getConfig().getInt("spawn.cmd.Title.TitlePlayerNamePosition"))-1);
						String Bpn = title.substring(0, main.getConfig().getInt("spawn.cmd.Title.TitlePlayerNamePosition")-1);
						
						p.sendTitle(Bpn+pn+Apn, subTitle);
						
					}else if(main.getConfig().getInt("spawn.cmd.Title.PlayerNameOn") == 1) {
						
						String Apn = subTitle.substring((main.getConfig().getInt("spawn.cmd.Title.SubTitlePlayerNamePosition"))-1);
						String Bpn = subTitle.substring(0, main.getConfig().getInt("spawn.cmd.Title.SubTitlePlayerNamePosition")-1);
						
						p.sendTitle(title, Bpn+pn+Apn);
						
					}else if(main.getConfig().getInt("spawn.cmd.Title.PlayerNameOn") == 2) {
						
						String Apn1 = title.substring((main.getConfig().getInt("spawn.cmd.Title.TitlePlayerNamePosition"))-1);
						String Bpn1 = title.substring(0, main.getConfig().getInt("spawn.cmd.Title.TitlePlayerNamePosition")-1);
						
						String Apn2 = subTitle.substring((main.getConfig().getInt("spawn.cmd.Title.SubTitlePlayerNamePosition"))-1);
						String Bpn2 = subTitle.substring(0, main.getConfig().getInt("spawn.cmd.Title.SubTitlePlayerNamePosition")-1);
						
						p.sendTitle(Bpn1+pn+Apn1, Bpn2+pn+Apn2);
						
					}else if(main.getConfig().getInt("spawn.cmd.Title.PlayerNameOn") == -1) {
						
						p.sendTitle(title, subTitle);
						
					}
					
				}else if(args.length == 1){

					if(args[0].equalsIgnoreCase("help")){

						p.sendMessage("§2>> §6JustSpawn Help :\n" +
								"§2>> §1/spawn : §6Teleport you to the spawn\n" +
								"§2>> §1/spawn option particles : §6Open the Particle Configuration Menu\n" +
								"§2>> §1/spawn help : §6Show this message");

					}else if(args[0].equalsIgnoreCase("option")){

						p.sendMessage("§2>> §4JustSpawn usage :\n" +
								"§2>> §1/spawn option §4particles");

					}

				}else if(args.length == 2) {
					
					if(args[0].equalsIgnoreCase("option")) {
						
						if(args[1].equalsIgnoreCase("particles")) {
							
							ParticleOption(p, pn);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return false;
	}

	public static void ParticleOption(Player p, String pn) {

		if(!main.getPlayerParticulesConfig().getConfigurationSection("Player.").contains(pn)) {

			ParticlesPlayer.Connect(pn);
			ParticlesPlayer.Spawn(pn);

		}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Spawn")){

			ParticlesPlayer.Spawn(pn);

		}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Connection")){

			ParticlesPlayer.Connect(pn);

		}

		if(main.getConfig().getBoolean("spawn.cmd.Particles") == true) {
			
			SlimeBallCMDm.setDisplayName("§1/spawn Particles : enable");
			SlimeBallCMDm.addEnchant(Enchantment.MENDING, 1, true);
			SlimeBallCMDm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			SlimeBallCMD.setItemMeta(SlimeBallCMDm);
			
		}else {
			
			SlimeBallCMDm.setDisplayName("§4/spawn Particles : disable");
			
			SlimeBallCMD.setItemMeta(SlimeBallCMDm);
			
		}
		
		particle.setItem(1, SlimeBallCMD);

		if(main.getConfig().getBoolean("spawn.Connection.Particles") == true) {

			SlimeBallCMDm.setDisplayName("§1Connection Particles : enable");
			SlimeBallCMDm.addEnchant(Enchantment.MENDING, 1, true);
			SlimeBallCMDm.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			SlimeBallCMD.setItemMeta(SlimeBallCMDm);

		}else {

			SlimeBallCMDm.setDisplayName("§4Connection Particles : disable");

			SlimeBallCMD.setItemMeta(SlimeBallCMDm);

		}

		particle.setItem(7, SlimeBallCMD);

		p.openInventory(particle);
		
		
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<>();
		Player p = (Player) sender;
		String pn = p.getName();
		
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			
			if(args.length == 1) {
				
				if(args[0].startsWith("o")) {
					
					l.add("option");
					return l;
					
				}else if(args[0].startsWith("h")) {
					
					l.add("help");
					return l;
					
				}else {

					l.add("help");
					l.add("option");
					return l;

				}
									
			}else if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("option")) {
					
					if(args[1].startsWith("p")) {
						
						l.add("particles");
						return l;
						
					}else {

						l.add("particles");
						return l;
						
					}
					
				}
			
			}
			
		}
		
		return null;
	}

}
