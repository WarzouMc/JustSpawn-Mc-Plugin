package fr.WarzouMc.JustSpawn.serveur.plugin;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticleInstance;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticlesPlayer;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;

public class PluginManager implements Listener {

	private main main;

	public PluginManager(main main) {this.main = main;}

	private ArrayList<FireworkEffect.Type> fT = new ArrayList<>();
	private ArrayList<Color> c = new ArrayList<>();
	private ArrayList<GameMode> gm = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {

		Player p = e.getPlayer();
		String pn = p.getName();

		if(!main.getChatConfig().contains(pn)){

		    main.getChatConfig().set(pn, 0);

		    main.getChatConfig().save(main.getChat());

            YamlConfiguration.loadConfiguration(main.getChat());

        }

		gm.add(GameMode.SURVIVAL);
		gm.add(GameMode.CREATIVE);
		gm.add(GameMode.ADVENTURE);
		gm.add(GameMode.SPECTATOR);

		if(main.getConfig().getInt("spawn.Connection.GameMode") >= 0 && main.getConfig().getInt("spawn.Connection.GameMode") <= 3) {

			p.setGameMode(gm.get(main.getConfig().getInt("spawn.Connection.GameMode")));

		}

		Location floc = new Location(p.getWorld(), main.getConfig().getDouble("spawn.Connection.FireworksConfig.Location.x"), main.getConfig().getDouble("spawn.Connection.FireworksConfig.Location.y"), main.getConfig().getDouble("spawn.Connection.FireworksConfig.Location.z"));

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

		if(main.getConfig().getBoolean("spawn.Connection.Fireworks") == true) {

		    if(main.getConfig().getBoolean("spawn.Connection.FireworksConfig.FADE") == true){

                FireworkEffect fe1 = FireworkEffect.builder()
                        .flicker(main.getConfig().getBoolean("spawn.Connection.FireworksConfig.FLICKER"))
                        .withColor(c.get(main.getConfig().getInt("spawn.Connection.FireworksConfig.COLOR")))
                        .withFade(c.get(main.getConfig().getInt("spawn.Connection.FireworksConfig.FADECOLOR")))
                        .with(fT.get(main.getConfig().getInt("spawn.Connection.FireworksConfig.TYPE")))
                        .trail(main.getConfig().getBoolean("spawn.Connection.FireworksConfig.TRAIL"))
                        .build();

                Firework f = (Firework)p.getWorld().spawnEntity(floc, EntityType.FIREWORK);
                f.detonate();
                FireworkMeta fM = f.getFireworkMeta();

                fM.setPower(main.getConfig().getInt("spawn.Connection.FireworksConfig.POWER"));
                fM.addEffect(fe1);
                f.setFireworkMeta(fM);

            }else{

                FireworkEffect fe1 = FireworkEffect.builder()
                        .flicker(main.getConfig().getBoolean("spawn.Connection.FireworksConfig.FLICKER"))
                        .withColor(c.get(main.getConfig().getInt("spawn.Connection.FireworksConfig.COLOR")))
                        .with(fT.get(main.getConfig().getInt("spawn.Connection.FireworksConfig.TYPE")))
                        .trail(main.getConfig().getBoolean("spawn.Connection.FireworksConfig.TRAIL"))
                        .build();

                Firework f = (Firework)p.getWorld().spawnEntity(floc, EntityType.FIREWORK);
                f.detonate();
                FireworkMeta fM = f.getFireworkMeta();

                fM.setPower(main.getConfig().getInt("spawn.Connection.FireworksConfig.POWER"));
                fM.addEffect(fe1);
                f.setFireworkMeta(fM);

            }

		}

		Location spawn = new Location(p.getWorld(), main.getConfig().getDouble("spawn.Location.x"), main.getConfig().getDouble("spawn.Location.y"), main.getConfig().getDouble("spawn.Location.z"));

		if(main.getConfig().getBoolean("spawn.Connection.TelepotAtConnection") == true) {

			p.teleport(spawn);

		}

		if(main.getConfig().getBoolean("spawn.Connection.CoMsgEnable") == true) {

			String comsg = main.getConfig().getString("spawn.Connection.CoMsg").replaceAll("&", "§");

			String Apn = comsg.substring((main.getConfig().getInt("spawn.Connection.PlayerNamePosition"))-1);
			String Bpn = comsg.substring(0, main.getConfig().getInt("spawn.Connection.PlayerNamePosition")-1);

			e.setJoinMessage(Bpn+pn+Apn);

		}

		if(main.getConfig().getBoolean("spawn.Connection.Title.EnableTitle") == true){

			String cotitle = main.getConfig().getString("spawn.Connection.Title.title").replaceAll("&", "§");
			String cosubTitle = main.getConfig().getString("spawn.Connection.Title.subTitle").replaceAll("&", "§");

			if(main.getConfig().getInt("spawn.Connection.Title.PlayerNameOn") == 0) {

				String Apn = cotitle.substring((main.getConfig().getInt("spawn.Connection.Title.TitlePlayerNamePosition"))-1);
				String Bpn = cotitle.substring(0, main.getConfig().getInt("spawn.Connection.Title.TitlePlayerNamePosition")-1);

				p.sendTitle(Bpn+pn+Apn, cosubTitle);

			}else if(main.getConfig().getInt("spawn.Connection.Title.PlayerNameOn") == 1) {

				String Apn = cosubTitle.substring((main.getConfig().getInt("spawn.Connection.Title.SubTitlePlayerNamePosition"))-1);
				String Bpn = cosubTitle.substring(0, main.getConfig().getInt("spawn.Connection.Title.SubTitlePlayerNamePosition")-1);

				p.sendTitle(cotitle, Bpn+pn+Apn);

			}else if(main.getConfig().getInt("spawn.Connection.Title.PlayerNameOn") == 2) {

				String Apn1 = cotitle.substring((main.getConfig().getInt("spawn.Connection.Title.TitlePlayerNamePosition"))-1);
				String Bpn1 = cotitle.substring(0, main.getConfig().getInt("spawn.Connection.Title.TitlePlayerNamePosition")-1);

				String Apn2 = cosubTitle.substring((main.getConfig().getInt("spawn.Connection.Title.SubTitlePlayerNamePosition"))-1);
				String Bpn2 = cosubTitle.substring(0, main.getConfig().getInt("spawn.Connection.Title.SubTitlePlayerNamePosition")-1);

				p.sendTitle(Bpn1+pn+Apn1, Bpn2+pn+Apn2);

			}else if(main.getConfig().getInt("spawn.Connection.Title.PlayerNameOn") == -1) {

				p.sendTitle(cotitle, cosubTitle);

			}

		}

		if(main.getConfig().getBoolean("spawn.Connection.Particles") == true) {

			if(!main.getPlayerParticulesConfig().getConfigurationSection("Player.").contains(pn)) {

				ParticlesPlayer.Connect(pn);
				ParticlesPlayer.Spawn(pn);

			}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Connection")) {

				ParticlesPlayer.Connect(pn);

			}else if(!main.getPlayerParticulesConfig().getConfigurationSection("Player."+pn+".").contains("Spawn")) {

				ParticlesPlayer.Spawn(pn);

			}

			Types type = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+".Connection.TYPE"));

			main.setType(type);

			String path = pn+".Connection";

			ParticleInstance.Instance(p, path, 0, pn);

		}

	}

}
