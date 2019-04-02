package fr.WarzouMc.JustSpawn;

import fr.WarzouMc.JustSpawn.client.ParticlePackage.Helix.Helix;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Helix.ParticuleHelixManager;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticleInstance;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticlesPlayer;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.TypeGeo;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;
import fr.WarzouMc.JustSpawn.client.cmd.SpawnCommand.SpawnCommand;
import fr.WarzouMc.JustSpawn.op.cmd.opSpawn.OpSpawnCommand;
import fr.WarzouMc.JustSpawn.serveur.particles.Head;
import fr.WarzouMc.JustSpawn.serveur.particles.Heads;
import fr.WarzouMc.JustSpawn.serveur.particles.ParticlesManager;
import fr.WarzouMc.JustSpawn.serveur.plugin.PluginManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import fr.WarzouMc.JustSpawn.op.plugin.modifManager.ModifManager;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Ball.*;

import java.io.File;
import java.io.IOException;

public class main extends JavaPlugin {
	
	private File PlayerParticles;
	private FileConfiguration PlayerParticulesConfig;

	private File Chat;
	private FileConfiguration ChatConfig;
	
	private Helix helixP = new Helix(this);
    private Ball ballP = new Ball(this);
	private Head head = new Head(this);
	private ParticlesPlayer pp = new ParticlesPlayer(this);
	
	private Types type;
	private TypeGeo typeGeo;
	private Heads heads;
	
	private ParticleInstance particleInstance = new ParticleInstance(this);
	
	@Override
	public void onEnable(){
		
		if(!getDataFolder().exists()) {
			
			getDataFolder().mkdirs();
			saveDefaultConfig();
			
			getConfig().options().header("This plugin has been creat by WarzouMc\nThank you for use this\nYou can comment this plugin on the spigot official web site\nIf you discover bug or dysfunction thank you for the report on the site : https://www.spigotmc.org/threads/justspawn.354158/");
			
			saveConfig();
			
		}

		this.PlayerParticles = new File(getDataFolder() + File.separator + "particlePlayer.yml");

		if(!PlayerParticles.exists()) {

			try {
				PlayerParticles.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.PlayerParticulesConfig = YamlConfiguration.loadConfiguration(PlayerParticles);

			this.PlayerParticulesConfig.options().copyDefaults(true);
			this.PlayerParticulesConfig.options().header("This plugin has been creat by WarzouMc\nThank you for use this\nYou can comment this plugin on the spigot official web site\nIf you discover bug or dysfunction thank you for the report on the site : https://www.spigotmc.org/threads/justspawn.354158/");
			this.PlayerParticulesConfig.createSection("Player");

			try {
				PlayerParticulesConfig.save(PlayerParticles);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {

			this.PlayerParticulesConfig = YamlConfiguration.loadConfiguration(PlayerParticles);

		}

		this.Chat = new File(getDataFolder() + File.separator + "chat.yml");

		if(!Chat.exists()) {

			try {
				Chat.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.ChatConfig = YamlConfiguration.loadConfiguration(Chat);

			this.ChatConfig.options().copyDefaults(true);
			this.ChatConfig.options().header("This plugin has been creat by WarzouMc\nThank you for use this\nYou can comment this plugin on the spigot official web site\nIf you discover bug or dysfunction thank you for the report on the site : https://www.spigotmc.org/threads/justspawn.354158/");

			try {
				ChatConfig.save(Chat);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {

			this.ChatConfig = YamlConfiguration.loadConfiguration(Chat);

		}
		
		System.out.println("JustSpawn is enable now");
		
		getCommand("spawn").setExecutor(new SpawnCommand(this));

		getCommand("opspawn").setExecutor(new OpSpawnCommand(this));

		getServer().getPluginManager().registerEvents(new PluginManager(this), this);
		getServer().getPluginManager().registerEvents(new ParticlesManager(this), this);
		getServer().getPluginManager().registerEvents(new ParticuleHelixManager(this), this);
		getServer().getPluginManager().registerEvents(new ParticuleBallManager(this), this);

		getServer().getPluginManager().registerEvents(new ModifManager(this), this);

	}
	
	@Override
	public void onDisable() {
		
		System.out.println("JustSpawn is disable now");
		
	}
	
	public File getPlayerParticles() {
		return PlayerParticles;
	}

	public void setPlayerParticles(File playerParticles) {
		PlayerParticles = playerParticles;
	}

	public FileConfiguration getPlayerParticulesConfig() {
		return PlayerParticulesConfig;
	}

	public void setPlayerParticulesConfig(FileConfiguration playerParticulesConfig) {
		PlayerParticulesConfig = playerParticulesConfig;
	}

	public Helix getHelixP() {
		return helixP;
	}

	public void setHelixP(Helix helixP) {
		this.helixP = helixP;
	}

    public Ball getBallP() {
        return ballP;
    }

    public void setBallP(Ball ballP) {
        this.ballP = ballP;
    }

	public void setType(Types type) {
		this.type = type;
	}

	public boolean isType(Types type) {
		return this.type == type;
	}

	public TypeGeo getTypeGeo() {
		return typeGeo;
	}

	public void setTypeGeo(TypeGeo typeGeo) {
		this.typeGeo = typeGeo;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Heads getHeads() {
		return heads;
	}

	public void setHeads(Heads heads) {
		this.heads = heads;
	}

	public ParticleInstance getParticleInstance() {
		return particleInstance;
	}

	public void setParticleInstance(ParticleInstance particleInstance) {
		this.particleInstance = particleInstance;
	}

	public File getChat() {return Chat;}

	public void setChat(File chat) {Chat = chat;}

	public FileConfiguration getChatConfig() {return ChatConfig;}

	public void setChatConfig(FileConfiguration chatConfig) {ChatConfig = chatConfig;}

}
