package fr.WarzouMc.JustSpawn.client.ParticlePackage;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.WarzouMc.JustSpawn.main;

public class ParticlesPlayer {
	
	private static main main;
	
	public ParticlesPlayer(main main) {this.main = main;}

	public static void Connect(String pn) {
		
		main.getPlayerParticulesConfig().createSection("Player."+pn);
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.TYPE", "HELIX_RIGHT_UP");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.TYPEGEO", "HELIX");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.Particles", "FLAME");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.Color", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.Red", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.Green", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.Blue", 0);
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Connection.ColorN", "");
		
		try {
			main.getPlayerParticulesConfig().save(main.getPlayerParticles());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		YamlConfiguration.loadConfiguration(main.getPlayerParticles());
		
	}

	public static void Spawn(String pn) {
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.TYPE", "HELIX_RIGHT_UP");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.TYPEGEO", "HELIX");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.Particles", "FLAME");
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.Color", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.Red", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.Green", 0);
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.Blue", 0);
		
		main.getPlayerParticulesConfig().set("Player."+pn+".Spawn.ColorN", "");
		
		try {
			main.getPlayerParticulesConfig().save(main.getPlayerParticles());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		YamlConfiguration.loadConfiguration(main.getPlayerParticles());
		
	}

}
