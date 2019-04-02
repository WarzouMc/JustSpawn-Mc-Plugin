package fr.WarzouMc.JustSpawn.client.ParticlePackage.Ball;

import fr.WarzouMc.JustSpawn.client.ParticlePackage.Helix.ParticuleHelixManager;
import fr.WarzouMc.JustSpawn.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Ball {

    private main main;

    public Ball(main main) {this.main = main;}


    public void creatBallU(Player p, String path, int test, String pn) {

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
            double phi = 9;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 13) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(i) * Math.sin(phi);
                            double y = r * Math.cos(phi) + 0.75;
                            double z = r * Math.sin(i) * Math.sin(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }

    public void creatBallD(Player p, String path, int test, String pn) {

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
            double phi = 0;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 3.75) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(i) * Math.sin(phi);
                            double y = r * Math.cos(phi) + 0.75;
                            double z = r * Math.sin(i) * Math.sin(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }

    public void creatBallL(Player p, String path, int test, String pn) {

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
            double phi = 9;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 13) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(phi);
                            double y = r * Math.cos(i) * Math.sin(phi) + 0.75;
                            double z = r * Math.sin(i) * Math.sin(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }

    public void creatBallR(Player p, String path, int test, String pn) {

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
            double phi = 0;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 3.75) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(phi);
                            double y = r * Math.cos(i) * Math.sin(phi) + 0.75;
                            double z = r * Math.sin(i) * Math.sin(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }

    public void creatBallB(Player p, String path, int test, String pn) {

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
            double phi = 0;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 3.75) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(i) * Math.sin(phi);
                            double y = r * Math.sin(i) * Math.sin(phi) + 0.75;
                            double z = r * Math.cos(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }

    public void creatBallF(Player p, String path, int test, String pn) {

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
            double phi = 9;
            double time = 0;

            @Override
            public void run() {

                phi = phi+Math.PI/10;
                time = time+Math.PI/10;

                if(phi > 13) {

                    if(test == 1) {

                        if(time > 30) {

                            String paths = "";

                            if(path.endsWith("Connection")){

                                paths = "Connection";

                            }else if(path.endsWith("Spawn")){

                                paths = "Spawn";

                            }

                            ParticuleBallManager.Config(p, pn, paths);
                            cancel();

                        }

                    }else {

                        cancel();

                    }

                }else {

                    for(Player pls : Bukkit.getOnlinePlayers()) {

                        for(double i = 0; i <= 2*Math.PI; i=i+Math.PI/40){

                            double r = 1.5;
                            double x = r * Math.cos(i) * Math.sin(phi);
                            double y = r * Math.sin(i) * Math.sin(phi) + 0.75;
                            double z = r * Math.cos(phi);

                            loc.add(x,y,z);

                            pls.spawnParticle(particle, loc, color, red, green, blue, 1);

                            loc.subtract(x,y,z);

                        }

                    }

                }

            }

        }.runTaskTimer(main, 0, 1L);

    }
}
