package fr.WarzouMc.JustSpawn.client.ParticlePackage.Helix;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import fr.WarzouMc.JustSpawn.main;

public class Helix implements Listener{

	private main main;
	
	public Helix(main main) {this.main = main;}
	
	public void creatHelixRU(Player p, String path, int test, String pn) {
		
		Particle particle;
		
		int color;
		
		double red;
		double green;
		double blue;

		if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("COLOR")) {
			
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf("REDSTONE");
			
		}else if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("NOTE")) {
	    
			color = 1;
			
			red = 0;
			green = 0;
			blue = 0;
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}else {
		
			color = 0;
			
			red = 0;
			green = 0;
			blue = 0;
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}
		
		new BukkitRunnable() {
			
			Location loc = p.getLocation();
			int radius = 1;
			double y = 0;
			double time = 0;
			
			@Override
			public void run() {
				
				y = y+Math.PI/8;
				time = time+Math.PI/8;
	            double x = radius * Math.cos(y);
	            double z = radius * Math.sin(y);
				
	            if(y > 15) {
	            	
	            	if(test == 1) {
	            		
	            		if(time > 30) {

							String paths = "";

							if(path.endsWith("Connection")){

								paths = "Connection";

							}else if(path.endsWith("Spawn")){

								paths = "Spawn";

							}

							ParticuleHelixManager.SHelixConfig(p, pn, paths);
	            			cancel();
	            			
	            		}
	            		
	            	}else {
	            		
	            		cancel();
	            		
	            	}
	            	
	            }else {
	            	
	            	for(Player pls : Bukkit.getOnlinePlayers()) {
	            		
	            		pls.spawnParticle(particle, (float) (loc.getX() + x), (float) (loc.getY() + y/10), (float) (loc.getZ() + z), color, red, green, blue, 1);	
	            		
	            	}
	            	
	            }
	            
			}
			
		}.runTaskTimer(main, 0, 1L);
		
	}
	
	public void creatHelixRD(Player p, String path, int test, String pn) {
		
		Particle particle;
		
		int color;
		
		double red;
		double green;
		double blue;
		
		if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("COLOR")) {
			
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf("REDSTONE");
			
		}else if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("NOTE")) {
	    
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}else {
		
			color = 0;
			
			red = 0;
			green = 0;
			blue = 0;
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}
		
		new BukkitRunnable() {
			
			Location loc = p.getLocation();
			int radius = 1;
			double y = 15;
			double time = 15;
			
			@Override
			public void run() {
				
				y = y-Math.PI/8;
				time = time-Math.PI/8;
	            double x = radius * Math.cos(-y);
	            double z = radius * Math.sin(-y);
				
	            if(y < 0) {
	            	
	            	if(test == 1) {
	            		
	            		if(time < -15) {

							String paths = "";

							if(path.endsWith("Connection")){

								paths = "Connection";

							}else if(path.endsWith("Spawn")){

								paths = "Spawn";

							}

	            			ParticuleHelixManager.SHelixConfig(p, pn, paths);
	            			
	            			cancel();
	            			
	            		}
	            		
	            	}else {
	            		
	            		cancel();	
	            		
	            	}
	            	
	            }else {
	            	
	            	for(Player pls : Bukkit.getOnlinePlayers()) {
	            		
	            		pls.spawnParticle(particle, (float) (loc.getX() + x), (float) (loc.getY() + y/10), (float) (loc.getZ() + z), color, red, green, blue, 1);	
	            		
	            	}
	            	
	            }
	            
			}
			
		}.runTaskTimer(main, 0, 1L);
		
	}
	
	public void creatHelixLU(Player p, String path, int test, String pn) {
		
		Particle particle;
		
		int color;
		
		double red;
		double green;
		double blue;

		if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("COLOR")) {
			
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf("REDSTONE");
			
		}else if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("NOTE")) {
	    
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}else {
		
			color = 0;
			
			red = 0;
			green = 0;
			blue = 0;
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}
		
		new BukkitRunnable() {
			
			Location loc = p.getLocation();
			int radius = 1;
			double y = 0;
			double time = 0;
			
			@Override
			public void run() {
				
				y = y+Math.PI/8;
				time = time+Math.PI/8;
	            double x = radius * Math.cos(-y);
	            double z = radius * Math.sin(-y);
				
	            if(y > 15) {
	            	
	            	if(test == 1) {
	            		
	            		if(time > 30) {

							String paths = "";

							if(path.endsWith("Connection")){

								paths = "Connection";

							}else if(path.endsWith("Spawn")){

								paths = "Spawn";

							}

							ParticuleHelixManager.SHelixConfig(p, pn, paths);
	            			cancel();
	            			
	            		}
	            		
	            	}else {
	            		
	            		cancel();
	            		
	            	}
	            	
	            }else {
	            	
	            	for(Player pls : Bukkit.getOnlinePlayers()) {
	            		
	            		pls.spawnParticle(particle, (float) (loc.getX() + x), (float) (loc.getY() + y/10), (float) (loc.getZ() + z), color, red, green, blue, 1);	
	            		
	            	}
	            	
	            }
	            
			}
			
		}.runTaskTimer(main, 0, 1L);
		
	}
	
	public void creatHelixLD(Player p, String path, int test, String pn) {
		
		Particle particle;
		
		int color;
		
		double red;
		double green;
		double blue;

		if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("COLOR")) {
			
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf("REDSTONE");
			
		}else if(main.getPlayerParticulesConfig().getString("Player."+path+".Particles").equalsIgnoreCase("NOTE")) {
	    
			color = main.getPlayerParticulesConfig().getInt("Player."+path+".Color");
			
			red = main.getPlayerParticulesConfig().getDouble("Player."+path+".Red");
			green = main.getPlayerParticulesConfig().getDouble("Player."+path+".Green");
			blue = main.getPlayerParticulesConfig().getDouble("Player."+path+".Blue");
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}else{
		
			color = 0;
			
			red = 0;
			green = 0;
			blue = 0;
			
			particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+path+".Particles"));
		
		}
		
		new BukkitRunnable() {
			
			Location loc = p.getLocation();
			int radius = 1;
			double y = 15;
			double time = 15;
			
			@Override
			public void run() {
				
				y = y-Math.PI/8;
				time = time-Math.PI/8;
	            double x = radius * Math.cos(y);
	            double z = radius * Math.sin(y);
				
	            if(y < 0) {
	            	
	            	if(test == 1) {
	            		
	            		if(time < -15) {

							String paths = "";

							if(path.endsWith("Connection")){

								paths = "Connection";

							}else if(path.endsWith("Spawn")){

								paths = "Spawn";

							}

							ParticuleHelixManager.SHelixConfig(p, pn, paths);
	            			
	            			cancel();
	            			
	            		}
	            		
	            	}else {
	            		
	            		cancel();	
	            		
	            	}
	            	
	            }else {
	            	
	            	for(Player pls : Bukkit.getOnlinePlayers()) {
	            		
	            		pls.spawnParticle(particle, (float) (loc.getX() + x), (float) (loc.getY() + y/10), (float) (loc.getZ() + z), color, red, green, blue, 1);	
	            		
	            	}
	            	
	            }
	            
			}
			
		}.runTaskTimer(main, 0, 1L);
		
	}
	
}
