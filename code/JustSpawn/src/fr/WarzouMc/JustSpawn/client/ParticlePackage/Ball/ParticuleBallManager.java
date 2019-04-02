package fr.WarzouMc.JustSpawn.client.ParticlePackage.Ball;

import fr.WarzouMc.JustSpawn.client.ParticlePackage.ParticleInstance;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;
import fr.WarzouMc.JustSpawn.client.cmd.SpawnCommand.SpawnCommand;
import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.serveur.particles.Head;
import fr.WarzouMc.JustSpawn.serveur.particles.ParticlesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
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

import java.io.IOException;
import java.util.Arrays;

public class ParticuleBallManager implements Listener {

    private static fr.WarzouMc.JustSpawn.main main;

    public ParticuleBallManager(main main) {this.main = main;}

    private static Inventory spawnBallInv = Bukkit.createInventory(null, 18, "§2/spawn ball particles option");
    private Inventory spawnBallDirectionInv = Bukkit.createInventory(null, 18, "§2ball particles direction option");
    private Inventory spawnBallParticleInv = Bukkit.createInventory(null, 36, "§2ball particles particle option");
    private Inventory ColorInv = Bukkit.createInventory(null, 36, "§2Color particle ball");

    private ItemStack redGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
    private ItemMeta redGlassM = redGlass.getItemMeta();

    private ItemStack greenGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)5);
    private ItemMeta greenGlassM = greenGlass.getItemMeta();

    private static ItemStack Arrow = new ItemStack(Material.ARROW);
    private static ItemMeta ArrowM = Arrow.getItemMeta();

    private static ItemStack stick = new ItemStack(Material.STICK);
    private ItemMeta stickM = stick.getItemMeta();

    private static ItemStack redstone = new ItemStack(Material.REDSTONE);
    private ItemMeta restoneM = redstone.getItemMeta();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        String paths = "Spawn";
        Inventory inv = e.getInventory();
        Player p = (Player)e.getWhoClicked();
        String pn = p.getName();

        ItemStack current = e.getCurrentItem();

        redGlassM.setDisplayName("§4Disable");
        redGlass.setItemMeta(redGlassM);

        greenGlassM.setDisplayName("§2Enable");
        greenGlass.setItemMeta(greenGlassM);

        stickM.setDisplayName("§5Direction");
        stick.setItemMeta(stickM);

        restoneM.setDisplayName("§5Particles");
        redstone.setItemMeta(restoneM);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        Types types = null;

        if(inv.getName().equalsIgnoreCase("§2/spawn particles option")) {

            types = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+paths+".TYPE"));

            e.setCancelled(true);

            if(current.getType() == Material.SLIME_BALL && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§2Ball") || current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Ball")) {

                p.closeInventory();

                Config(p, pn, paths);

            }

        }else if(inv.getName().equalsIgnoreCase("§2/spawn ball particles option")) {

            e.setCancelled(true);

            if(current.getType() == Material.STICK && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Direction")){

                p.closeInventory();

                Direction(p, paths);

            }else if(current.getType() == Material.REDSTONE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Particles")){

                p.closeInventory();
                SParticles(p, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                ParticlesManager.SpawnParticles(p, "Spawn", "§2/spawn particles option");

            }else if(current.getType() == Material.TORCH && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§2Particle Test")) {

                p.closeInventory();

                types = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+".Spawn.TYPE"));

                main.setType(types);

                String path = pn+".Spawn";

                ParticleInstance.Instance(p, path, 1, pn);

            }

        }

        if(inv.getName().equalsIgnoreCase("§2ball particles direction option")) {

            e.setCancelled(true);

            if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Left")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_LEFT");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Up")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_UP");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Down")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_DOWN");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Right")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_RIGHT");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Backward")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_BACKWARD");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Forward")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_FORWARD");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();
                Config(p, pn, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }else if(inv.getName().equalsIgnoreCase("§2ball particles particle option")) {

            e.setCancelled(true);

            if(current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§5§o")) {

                String particlename = current.getItemMeta().getDisplayName();

                particlename = particlename.substring(4, particlename.length());

                p.closeInventory();

                if(current.getType() == Material.WOOL) {

                    SColor(p, paths);

                }else {

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", particlename);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", "");

                    int color = 0;

                    int red = 0;
                    int green = 0;
                    int blue = 0;

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    try {
                        main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    YamlConfiguration.loadConfiguration(main.getPlayerParticles());

                    SParticles(p, paths);

                }

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                Config(p, pn, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }else if(inv.getName().equalsIgnoreCase("§2Color particle ball")) {

            e.setCancelled(true);

            if(current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§5§o")) {

                String particlename = current.getItemMeta().getDisplayName();

                particlename = particlename.substring(4, particlename.length());

                p.closeInventory();

                int color;

                double red;
                double green;
                double blue;

                if(current.getType() == Material.STICK) {

                    color = 1;

                    red = 0;
                    green = 0;
                    blue = 0;

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                    if(!main.getPlayerParticulesConfig().getString("Player."+pn+"."+paths+".Particles").equalsIgnoreCase("NOTE")) {

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", "COLOR");

                    }

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                }else if(current.getType() == Material.WOOL) {

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", "COLOR");

                    if(particlename.equalsIgnoreCase("WHITE")) {

                        color = 0;

                        red = 1;
                        green = 1;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("ORANGE")) {

                        color = 0;

                        red = 1;
                        green = 0.3;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("MAGENTA")) {

                        color = 0;

                        red = 1;
                        green = 0;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("LIGHT BLUE")) {

                        color = 0;

                        red = 0.1;
                        green = 0.56;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("YELLOW")) {

                        color = 0;

                        red = 1;
                        green = 1;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("LIME")) {

                        color = 0;

                        red = -1;
                        green = 1;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("PINK")) {

                        color = 0;

                        red = 1;
                        green = 0.4;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("GREY")) {

                        color = 0;

                        red = 0.4;
                        green = 0.4;
                        blue = 0.4;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("LIGHT GREY")) {

                        color = 0;

                        red = 0.75;
                        green = 0.75;
                        blue = 0.75;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("CYAN")) {

                        color = 0;

                        red = -1;
                        green = 0.2;
                        blue = -0.4;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("PURPLE")) {

                        color = 0;

                        red = 0.5;
                        green = 0;
                        blue = 0.5;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("BLUE")) {

                        color = 0;

                        red = -1;
                        green = 0;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("BROWN")) {

                        color = 0;

                        red = 0.5;
                        green = 0;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("GREEN")) {

                        color = 0;

                        red = -1;
                        green = 0.6;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);
                    }else if(particlename.equalsIgnoreCase("DARK")) {

                        color = 0;

                        red = 0.1;
                        green = 0.1;
                        blue = 0.1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("RED")) {

                        color = 0;

                        red = 1;
                        green = 0;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }

                }

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());

                SColor(p, paths);

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                SParticles(p, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }

    }

    public static void Config(Player p, String pn, String path) {

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        ItemStack torch = new ItemStack(Material.TORCH);
        ItemMeta torchM = torch.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        if(path.equalsIgnoreCase("Spawn")){

            spawnBallInv = Bukkit.createInventory(null, 18, "§2/spawn ball particles option");

        }else if(path.equalsIgnoreCase("Connection")){

            spawnBallInv = Bukkit.createInventory(null, 18, "§2Connection ball particles option");

        }

        if(main.getConfig().getBoolean("spawn.ParticleTest") == true) {

            torchM.setDisplayName("§2Particle Test");
            torch.setItemMeta(torchM);

            spawnBallInv.setItem(4, torch);

        }

        spawnBallInv.setItem(1, stick);
        spawnBallInv.setItem(7, redstone);

        spawnBallInv.setItem(9, barrier);
        spawnBallInv.setItem(17, Arrow);

        p.openInventory(spawnBallInv);

        if(!main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".TYPEGEO").equalsIgnoreCase("BALL")) {

            main.getPlayerParticulesConfig().set("Player."+pn+"."+path+".TYPEGEO", "BALL");
            main.getPlayerParticulesConfig().set("Player."+pn+"."+path+".TYPE", "BALL_UP");

            try {
                main.getPlayerParticulesConfig().save(main.getPlayerParticles());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            YamlConfiguration.loadConfiguration(main.getPlayerParticles());

        }

    }

    private void SColor(Player p, String path) {

        String pn = p.getName();

        String Color = main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".ColorN");

        ItemStack whitwool = new ItemStack(Material.WOOL, 1, (byte)0);
        ItemMeta whitwoolM = whitwool.getItemMeta();

        whitwoolM.setDisplayName("§5§oWHITE");

        if(Color.equalsIgnoreCase("WHITE")) {

            whitwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            whitwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        whitwool.setItemMeta(whitwoolM);

        ItemStack orangewool = new ItemStack(Material.WOOL, 1, (byte)1);
        ItemMeta orangewoolM = orangewool.getItemMeta();

        orangewoolM.setDisplayName("§5§oORANGE");

        if(Color.equalsIgnoreCase("ORANGE")) {

            orangewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            orangewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        orangewool.setItemMeta(orangewoolM);

        ItemStack magantawool = new ItemStack(Material.WOOL, 1, (byte)2);
        ItemMeta magantawoolM = magantawool.getItemMeta();

        magantawoolM.setDisplayName("§5§oMAGENTA");

        if(Color.equalsIgnoreCase("MAGENTA")) {

            magantawoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            magantawoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        magantawool.setItemMeta(magantawoolM);

        ItemStack bleuclairwool = new ItemStack(Material.WOOL, 1, (byte)3);
        ItemMeta bleuclairwoolM = bleuclairwool.getItemMeta();

        bleuclairwoolM.setDisplayName("§5§oLIGHT BLUE");

        if(Color.equalsIgnoreCase("LIGHT BLUE")) {

            bleuclairwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            bleuclairwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        bleuclairwool.setItemMeta(bleuclairwoolM);

        ItemStack jaunewool = new ItemStack(Material.WOOL, 1, (byte)4);
        ItemMeta jaunewoolM = jaunewool.getItemMeta();

        jaunewoolM.setDisplayName("§5§oYELLOW");

        if(Color.equalsIgnoreCase("YELLOW")) {

            jaunewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            jaunewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        jaunewool.setItemMeta(jaunewoolM);

        ItemStack limewool = new ItemStack(Material.WOOL, 1, (byte)5);
        ItemMeta limewoolM = limewool.getItemMeta();

        limewoolM.setDisplayName("§5§oLIME");

        if(Color.equalsIgnoreCase("LIME")) {

            limewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            limewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        limewool.setItemMeta(limewoolM);

        ItemStack rosewool = new ItemStack(Material.WOOL, 1, (byte)6);
        ItemMeta rosewoolM = rosewool.getItemMeta();

        rosewoolM.setDisplayName("§5§oPINK");

        if(Color.equalsIgnoreCase("PINK")) {

            rosewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            rosewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        rosewool.setItemMeta(rosewoolM);

        ItemStack grisfonceswool = new ItemStack(Material.WOOL, 1, (byte)7);
        ItemMeta grisfonceswoolM = grisfonceswool.getItemMeta();

        grisfonceswoolM.setDisplayName("§5§oGREY");

        if(Color.equalsIgnoreCase("GREY")) {

            grisfonceswoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            grisfonceswoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        grisfonceswool.setItemMeta(grisfonceswoolM);

        ItemStack grisclairewool = new ItemStack(Material.WOOL, 1, (byte)8);
        ItemMeta grisclairewoolM = grisclairewool.getItemMeta();

        grisclairewoolM.setDisplayName("§5§oLIGHT GREY");

        if(Color.equalsIgnoreCase("LIGHT GREY")) {

            grisclairewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            grisclairewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        grisclairewool.setItemMeta(grisclairewoolM);

        ItemStack cyanwool = new ItemStack(Material.WOOL, 1, (byte)9);
        ItemMeta cyanwoolM = cyanwool.getItemMeta();

        cyanwoolM.setDisplayName("§5§oCYAN");

        if(Color.equalsIgnoreCase("CYAN")) {

            cyanwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            cyanwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        cyanwool.setItemMeta(cyanwoolM);

        ItemStack violetwool = new ItemStack(Material.WOOL, 1, (byte)10);
        ItemMeta violetwoolM = violetwool.getItemMeta();

        violetwoolM.setDisplayName("§5§oPURPLE");

        if(Color.equalsIgnoreCase("PURPLE")) {

            violetwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            violetwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        violetwool.setItemMeta(violetwoolM);

        ItemStack bleuwool = new ItemStack(Material.WOOL, 1, (byte)11);
        ItemMeta bleuwoolM = bleuwool.getItemMeta();

        bleuwoolM.setDisplayName("§5§oBLUE");

        if(Color.equalsIgnoreCase("BLUE")) {

            bleuwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            bleuwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        bleuwool.setItemMeta(bleuwoolM);

        ItemStack marronwool = new ItemStack(Material.WOOL, 1, (byte)12);
        ItemMeta marronwoolM = marronwool.getItemMeta();

        marronwoolM.setDisplayName("§5§oBROWN");

        if(Color.equalsIgnoreCase("BROWN")) {

            marronwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            marronwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        marronwool.setItemMeta(marronwoolM);

        ItemStack vertwool = new ItemStack(Material.WOOL, 1, (byte)13);
        ItemMeta vertwoolM = vertwool.getItemMeta();

        vertwoolM.setDisplayName("§5§oGREEN");

        if(Color.equalsIgnoreCase("GREEN")) {

            vertwoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            vertwoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        vertwool.setItemMeta(vertwoolM);

        ItemStack rougewool = new ItemStack(Material.WOOL, 1, (byte)14);
        ItemMeta rougewoolM = rougewool.getItemMeta();

        rougewoolM.setDisplayName("§5§oRED");

        if(Color.equalsIgnoreCase("RED")) {

            rougewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            rougewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        rougewool.setItemMeta(rougewoolM);

        ItemStack noirewool = new ItemStack(Material.WOOL, 1, (byte)15);
        ItemMeta noirewoolM = noirewool.getItemMeta();

        noirewoolM.setDisplayName("§5§oDARK");

        if(Color.equalsIgnoreCase("DARK")) {

            noirewoolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            noirewoolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        noirewool.setItemMeta(noirewoolM);

        if(path.equalsIgnoreCase("Spawn")){

            ColorInv = Bukkit.createInventory(null, 36, "§2Color particle ball");

        }else if(path.equalsIgnoreCase("Connection")){

            ColorInv = Bukkit.createInventory(null, 36, "§2Color particle ball.");

        }

        ColorInv.setItem(0, whitwool);
        ColorInv.setItem(1, orangewool);
        ColorInv.setItem(2, magantawool);
        ColorInv.setItem(3, bleuclairwool);
        ColorInv.setItem(4, jaunewool);
        ColorInv.setItem(5, limewool);
        ColorInv.setItem(6, rosewool);
        ColorInv.setItem(7, grisfonceswool);
        ColorInv.setItem(8, grisclairewool);
        ColorInv.setItem(21, cyanwool);
        ColorInv.setItem(22, violetwool);
        ColorInv.setItem(11, bleuwool);
        ColorInv.setItem(12, marronwool);
        ColorInv.setItem(13, vertwool);
        ColorInv.setItem(14, noirewool);
        ColorInv.setItem(15, rougewool);

        ItemStack Stick = new ItemStack(Material.STICK);
        ItemMeta StickM = Stick.getItemMeta();

        StickM.setDisplayName("§5§oRAINBOW");

        if(Color.equalsIgnoreCase("RAINBOW")) {

            StickM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            StickM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        Stick.setItemMeta(StickM);

        ColorInv.setItem(23, Stick);

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        ColorInv.setItem(35, Arrow);
        ColorInv.setItem(27, barrier);

        p.openInventory(ColorInv);

    }

    @SuppressWarnings("deprecation")
    private void SParticles(Player p, String path) {

        String pn = p.getName();

        Particle particle;

        int color;

        double red;
        double green;
        double blue;

        String Color;

        if(main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".Particles").equalsIgnoreCase("COLOR")) {

            color = main.getPlayerParticulesConfig().getInt("Player."+pn+"."+path+".Color");

            red = main.getPlayerParticulesConfig().getDouble("Player."+pn+"."+path+".Red");
            green = main.getPlayerParticulesConfig().getDouble("Player."+pn+"."+path+".Green");
            blue = main.getPlayerParticulesConfig().getDouble("Player."+pn+"."+path+".Blue");

            Color = main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".ColorN");

            particle = Particle.valueOf("REDSTONE");

        }else {

            color = 0;

            red = 0;
            green = 0;
            blue = 0;

            Color = "";

            particle = Particle.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".Particles"));

        }

        //Particle.CLOUD  0

        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemMeta featherM = feather.getItemMeta();

        featherM.setDisplayName("§5§oCLOUD");

        if(particle == Particle.CLOUD) {

            featherM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            featherM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        feather.setItemMeta(featherM);

        //Particle.CRIT  1

        ItemStack IronSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta IronSwordM = IronSword.getItemMeta();

        IronSwordM.setDisplayName("§5§oCRIT");

        if(particle == Particle.CRIT) {

            IronSwordM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            IronSwordM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        IronSword.setItemMeta(IronSwordM);

        //Particle.CRIT_MAGIC  2

        ItemStack DiamonSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta DiamonSwordM = DiamonSword.getItemMeta();

        DiamonSwordM.setDisplayName("§5§oCRIT_MAGIC");

        if(particle == Particle.CRIT_MAGIC) {

            DiamonSwordM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            DiamonSwordM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        DiamonSword.setItemMeta(DiamonSwordM);

        //Particle.DAMAGE_INDICATOR  3

        ItemStack Skull = new ItemStack(Material.SKULL_ITEM);
        ItemMeta SkullM = Skull.getItemMeta();

        SkullM.setDisplayName("§5§oDAMAGE_INDICATOR");

        if(particle == Particle.DAMAGE_INDICATOR) {

            SkullM.setLore(Arrays.asList("§2Enable"));

        }

        Skull.setItemMeta(SkullM);

        //Particle.DRAGON_BREATH  4

        ItemStack DragonEgg = new ItemStack(Material.DRAGON_EGG);
        ItemMeta DragonEggM = DragonEgg.getItemMeta();

        DragonEggM.setDisplayName("§5§oDRAGON_BREATH");

        if(particle == Particle.DRAGON_BREATH) {

            DragonEggM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            DragonEggM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        DragonEgg.setItemMeta(DragonEggM);

        //Particle.DRIP_LAVA  5

        ItemStack LavaBukket = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta LavaBukketM = LavaBukket.getItemMeta();

        LavaBukketM.setDisplayName("§5§oDRIP_LAVA");

        if(particle == Particle.DRIP_LAVA) {

            LavaBukketM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            LavaBukketM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        LavaBukket.setItemMeta(LavaBukketM);

        //Particle.DRIP_WATER  6

        ItemStack WaterBukket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta WaterBukketM = WaterBukket.getItemMeta();

        WaterBukketM.setDisplayName("§5§oDRIP_WATER");

        if(particle == Particle.DRIP_WATER) {

            WaterBukketM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            WaterBukketM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        WaterBukket.setItemMeta(WaterBukketM);

        //Particle.ENCHANTMENT_TABLE  7

        ItemStack EnchanteTable = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta EnchanteTableM = EnchanteTable.getItemMeta();

        EnchanteTableM.setDisplayName("§5§oENCHANTMENT_TABLE");

        if(particle == Particle.ENCHANTMENT_TABLE) {

            EnchanteTableM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            EnchanteTableM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        EnchanteTable.setItemMeta(EnchanteTableM);

        //Particle.END_ROD  8

        ItemStack EndeRod = new ItemStack(Material.END_ROD);
        ItemMeta EndeRodM = EndeRod.getItemMeta();

        EndeRodM.setDisplayName("§5§oEND_ROD");

        if(particle == Particle.END_ROD) {

            EndeRodM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            EndeRodM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        EndeRod.setItemMeta(EndeRodM);

        //Particle.FIREWORKS_SPARK  9

        ItemStack Firework = new ItemStack(Material.FIREWORK);
        ItemMeta FireworkM = Firework.getItemMeta();

        FireworkM.setDisplayName("§5§oFIREWORKS_SPARK");

        if(particle == Particle.FIREWORKS_SPARK) {

            FireworkM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            FireworkM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        Firework.setItemMeta(FireworkM);

        //Particle.FLAME  10

        ItemStack FlintAndSteel = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta FlintAndSteelM = FlintAndSteel.getItemMeta();

        FlintAndSteelM.setDisplayName("§5§oFLAME");

        if(particle == Particle.FLAME) {

            FlintAndSteelM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            FlintAndSteelM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        FlintAndSteel.setItemMeta(FlintAndSteelM);

        //Particle.HEART  11

        ItemStack GApple;

        if(particle == Particle.HEART) {

            GApple = new ItemStack(Material.GOLDEN_APPLE, 1, (byte)1);

        }else {
            GApple = new ItemStack(Material.GOLDEN_APPLE);
        }

        ItemMeta GAppleM = GApple.getItemMeta();

        GAppleM.setDisplayName("§5§oHEART");

        GApple.setItemMeta(GAppleM);

        //Particle.NOTE  12

        ItemStack JukeBox = new ItemStack(Material.JUKEBOX);
        ItemMeta JukeBoxM = JukeBox.getItemMeta();

        JukeBoxM.setDisplayName("§5§oNOTE");

        if(particle == Particle.NOTE) {

            JukeBoxM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            JukeBoxM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        JukeBox.setItemMeta(JukeBoxM);

        //Particle.REDSTONE  13

        ItemStack Redstone = new ItemStack(Material.REDSTONE);
        ItemMeta RedstoneM = Redstone.getItemMeta();

        RedstoneM.setDisplayName("§5§oREDSTONE");

        if(particle == Particle.REDSTONE && Color.equalsIgnoreCase("")) {

            RedstoneM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            RedstoneM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        Redstone.setItemMeta(RedstoneM);

        //Particle.SLIME  14

        ItemStack SlimeBall = new ItemStack(Material.SLIME_BALL);
        ItemMeta SlimeBallM = SlimeBall.getItemMeta();

        SlimeBallM.setDisplayName("§5§oSLIME");

        if(particle == Particle.SLIME) {

            SlimeBallM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            SlimeBallM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        SlimeBall.setItemMeta(SlimeBallM);

        //Particle.SMOKE_NORMAL  15

        ItemStack tnt = new ItemStack(Material.TNT);
        ItemMeta tntM = tnt.getItemMeta();

        tntM.setDisplayName("§5§oSMOKE_NORMAL");

        if(particle == Particle.SMOKE_NORMAL) {

            tntM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            tntM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        tnt.setItemMeta(tntM);

        //Particle.SNOW_SHOVEL  16

        ItemStack snowBall = new ItemStack(Material.SNOW_BALL);
        ItemMeta snowBallM = snowBall.getItemMeta();

        snowBallM.setDisplayName("§5§oSNOW_SHOVEL");

        if(particle == Particle.SNOW_SHOVEL) {

            snowBallM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            snowBallM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        snowBall.setItemMeta(snowBallM);

        //Particle.SPELL_WITCH  17

        ItemStack glass = new ItemStack(Material.STAINED_GLASS, 1, (byte)10);
        ItemMeta glassM = glass.getItemMeta();

        glassM.setDisplayName("§5§oSPELL_WITCH");

        if(particle == Particle.SPELL_WITCH) {

            glassM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            glassM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        glass.setItemMeta(glassM);

        //Particle.VILLAGER_HAPPY  18

        ItemStack emeraud = new ItemStack(Material.EMERALD);
        ItemMeta emeraudM = emeraud.getItemMeta();

        emeraudM.setDisplayName("§5§oVILLAGER_HAPPY");

        if(particle == Particle.VILLAGER_HAPPY) {

            emeraudM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            emeraudM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        emeraud.setItemMeta(emeraudM);

        //Particle.WATER_BUBBLE  19

        ItemStack potion = new ItemStack(Material.POTION);
        ItemMeta potionM = potion.getItemMeta();

        potionM.setDisplayName("§5§oWATER_BUBBLE");

        if(particle == Particle.WATER_BUBBLE) {

            potionM.setLore(Arrays.asList("§2Enable"));

        }

        potion.setItemMeta(potionM);

        //Particle.REDSTONE  20

        ItemStack wool = new ItemStack(Material.WOOL, 1, (byte)5);
        ItemMeta woolM = wool.getItemMeta();

        woolM.setDisplayName("§5§oCOLOR");

        if(particle == Particle.REDSTONE && (!Color.equalsIgnoreCase(""))) {

            woolM.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            woolM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        }

        wool.setItemMeta(woolM);

        if(path.equalsIgnoreCase("Spawn")){

            spawnBallParticleInv = Bukkit.createInventory(null, 36, "§2ball particles particle option");

        }else if(path.equalsIgnoreCase("Connection")){

            spawnBallParticleInv = Bukkit.createInventory(null, 36, "§2ball particles particle option.");

        }

        spawnBallParticleInv.setItem(0, feather);
        spawnBallParticleInv.setItem(1, IronSword);
        spawnBallParticleInv.setItem(2, DiamonSword);
        spawnBallParticleInv.setItem(3, Skull);
        spawnBallParticleInv.setItem(4, DragonEgg);
        spawnBallParticleInv.setItem(5, LavaBukket);
        spawnBallParticleInv.setItem(6, WaterBukket);
        spawnBallParticleInv.setItem(7, EnchanteTable);
        spawnBallParticleInv.setItem(8, EndeRod);
        spawnBallParticleInv.setItem(24, Firework);
        spawnBallParticleInv.setItem(10, FlintAndSteel);
        spawnBallParticleInv.setItem(11, GApple);
        spawnBallParticleInv.setItem(12, JukeBox);
        spawnBallParticleInv.setItem(13, Redstone);
        spawnBallParticleInv.setItem(14, SlimeBall);
        spawnBallParticleInv.setItem(15, tnt);
        spawnBallParticleInv.setItem(16, snowBall);
        spawnBallParticleInv.setItem(23, glass);
        spawnBallParticleInv.setItem(20, emeraud);
        spawnBallParticleInv.setItem(21, potion);
        spawnBallParticleInv.setItem(22, wool);

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        spawnBallParticleInv.setItem(35, Arrow);
        spawnBallParticleInv.setItem(27, barrier);

        p.openInventory(spawnBallParticleInv);

    }

    private void Direction(Player p, String path) {

        String pn = p.getName();

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        Types types = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+path+".TYPE"));

        if(path.equalsIgnoreCase("Spawn")){

            spawnBallDirectionInv = Bukkit.createInventory(null, 18, "§2ball particles direction option");

        }else if(path.equalsIgnoreCase("Connection")){

            spawnBallDirectionInv = Bukkit.createInventory(null, 18, "§2ball particles direction option.");

        }

        if(Head.getHeadTypes(Types.BALL_UP) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, greenGlass);
            spawnBallDirectionInv.setItem(10, redGlass);
            spawnBallDirectionInv.setItem(11, redGlass);
            spawnBallDirectionInv.setItem(12, redGlass);
            spawnBallDirectionInv.setItem(13, redGlass);
            spawnBallDirectionInv.setItem(14, redGlass);

        }else if(Head.getHeadTypes(Types.BALL_DOWN) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, redGlass);
            spawnBallDirectionInv.setItem(10, greenGlass);
            spawnBallDirectionInv.setItem(11, redGlass);
            spawnBallDirectionInv.setItem(12, redGlass);
            spawnBallDirectionInv.setItem(13, redGlass);
            spawnBallDirectionInv.setItem(14, redGlass);

        }else if(Head.getHeadTypes(Types.BALL_RIGHT) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, redGlass);
            spawnBallDirectionInv.setItem(10, redGlass);
            spawnBallDirectionInv.setItem(11, greenGlass);
            spawnBallDirectionInv.setItem(12, redGlass);
            spawnBallDirectionInv.setItem(13, redGlass);
            spawnBallDirectionInv.setItem(14, redGlass);

        }else if(Head.getHeadTypes(Types.BALL_LEFT) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, redGlass);
            spawnBallDirectionInv.setItem(10, redGlass);
            spawnBallDirectionInv.setItem(11, redGlass);
            spawnBallDirectionInv.setItem(12, greenGlass);
            spawnBallDirectionInv.setItem(13, redGlass);
            spawnBallDirectionInv.setItem(14, redGlass);

        }else if(Head.getHeadTypes(Types.BALL_BACKWARD) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, redGlass);
            spawnBallDirectionInv.setItem(10, redGlass);
            spawnBallDirectionInv.setItem(11, redGlass);
            spawnBallDirectionInv.setItem(12, redGlass);
            spawnBallDirectionInv.setItem(13, greenGlass);
            spawnBallDirectionInv.setItem(14, redGlass);

        }else if(Head.getHeadTypes(Types.BALL_FORWARD) == types) {

            spawnBallDirectionInv.setItem(0, Head.getHead("arrowup"));
            spawnBallDirectionInv.setItem(1, Head.getHead("arrowdown"));
            spawnBallDirectionInv.setItem(2, Head.getHead("arrowright"));
            spawnBallDirectionInv.setItem(3, Head.getHead("arrowleft"));
            spawnBallDirectionInv.setItem(4, Head.getHead("arrowbackward"));
            spawnBallDirectionInv.setItem(5, Head.getHead("arrowforward"));

            spawnBallDirectionInv.setItem(9, redGlass);
            spawnBallDirectionInv.setItem(10, redGlass);
            spawnBallDirectionInv.setItem(11, redGlass);
            spawnBallDirectionInv.setItem(12, redGlass);
            spawnBallDirectionInv.setItem(13, redGlass);
            spawnBallDirectionInv.setItem(14, greenGlass);

        }

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        spawnBallDirectionInv.setItem(17, Arrow);
        spawnBallDirectionInv.setItem(8, barrier);

        p.openInventory(spawnBallDirectionInv);

    }

    /******************
     ******************
     ****CONNECTION****
     ******************
     ******************/

    @EventHandler
    public void onInventoryClickCo(InventoryClickEvent e) {
        String paths = "Connection";
        Inventory inv = e.getInventory();
        Player p = (Player)e.getWhoClicked();
        String pn = p.getName();

        ItemStack current = e.getCurrentItem();

        redGlassM.setDisplayName("§4Disable");
        redGlass.setItemMeta(redGlassM);

        greenGlassM.setDisplayName("§2Enable");
        greenGlass.setItemMeta(greenGlassM);

        stickM.setDisplayName("§5Direction");
        stick.setItemMeta(stickM);

        restoneM.setDisplayName("§5Particles");
        redstone.setItemMeta(restoneM);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierM = barrier.getItemMeta();

        barrierM.setDisplayName("§4Exit");
        barrier.setItemMeta(barrierM);

        ArrowM.setDisplayName("§ePrevious page");
        Arrow.setItemMeta(ArrowM);

        Types types = null;

        if(inv.getName().equalsIgnoreCase("§2Connection particles option")) {

            types = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+paths+".TYPE"));

            e.setCancelled(true);

            if(current.getType() == Material.SLIME_BALL && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§2Ball") || current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Ball")) {

                p.closeInventory();

                Config(p, pn, paths);

            }

        }else if(inv.getName().equalsIgnoreCase("§2Connection ball particles option")) {

            e.setCancelled(true);

            if(current.getType() == Material.STICK && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Direction")){

                p.closeInventory();

                Direction(p, paths);

            }else if(current.getType() == Material.REDSTONE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Particles")){

                p.closeInventory();
                SParticles(p, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                ParticlesManager.SpawnParticles(p, "Connection", "§2Connection particles option");

            }else if(current.getType() == Material.TORCH && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§2Particle Test")) {

                p.closeInventory();

                types = Types.valueOf(main.getPlayerParticulesConfig().getString("Player."+pn+"."+paths+".TYPE"));

                main.setType(types);

                String path = pn+".Connection";

                ParticleInstance.Instance(p, path, 1, pn);

            }

        }

        if(inv.getName().equalsIgnoreCase("§2ball particles direction option.")) {

            e.setCancelled(true);

            if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Left")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_LEFT");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Up")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_UP");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Down")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_DOWN");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Right")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_RIGHT");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Backward")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_BACKWARD");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.SKULL_ITEM && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§5Forward")) {

                p.closeInventory();

                main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".TYPE", "BALL_FORWARD");

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());
                Direction(p, paths);

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();
                Config(p, pn, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }else if(inv.getName().equalsIgnoreCase("§2ball particles particle option.")) {

            e.setCancelled(true);

            if(current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§5§o")) {

                String particlename = current.getItemMeta().getDisplayName();

                particlename = particlename.substring(4, particlename.length());

                p.closeInventory();

                if(current.getType() == Material.WOOL) {

                    SColor(p, paths);

                }else {

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", particlename);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", "");

                    int color = 0;

                    int red = 0;
                    int green = 0;
                    int blue = 0;

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    try {
                        main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    YamlConfiguration.loadConfiguration(main.getPlayerParticles());

                    SParticles(p, paths);

                }

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                Config(p, pn, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }else if(inv.getName().equalsIgnoreCase("§2Color particle ball.")) {

            e.setCancelled(true);

            if(current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§5§o")) {

                String particlename = current.getItemMeta().getDisplayName();

                particlename = particlename.substring(4, particlename.length());

                p.closeInventory();

                int color;

                double red;
                double green;
                double blue;

                if(current.getType() == Material.STICK) {

                    color = 1;

                    red = 0;
                    green = 0;
                    blue = 0;

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                    if(!main.getPlayerParticulesConfig().getString("Player."+pn+"."+paths+".Particles").equalsIgnoreCase("NOTE")) {

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", "COLOR");

                    }

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                }else if(current.getType() == Material.WOOL) {

                    main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Particles", "COLOR");

                    if(particlename.equalsIgnoreCase("WHITE")) {

                        color = 0;

                        red = 1;
                        green = 1;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("ORANGE")) {

                        color = 0;

                        red = 1;
                        green = 0.3;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("MAGENTA")) {

                        color = 0;

                        red = 1;
                        green = 0;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("LIGHT BLUE")) {

                        color = 0;

                        red = 0.1;
                        green = 0.56;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("YELLOW")) {

                        color = 0;

                        red = 1;
                        green = 1;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("LIME")) {

                        color = 0;

                        red = -1;
                        green = 1;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("PINK")) {

                        color = 0;

                        red = 1;
                        green = 0.4;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("GREY")) {

                        color = 0;

                        red = 0.4;
                        green = 0.4;
                        blue = 0.4;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);
                    }else if(particlename.equalsIgnoreCase("LIGHT GREY")) {

                        color = 0;

                        red = 0.75;
                        green = 0.75;
                        blue = 0.75;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("CYAN")) {

                        color = 0;

                        red = -1;
                        green = 0.2;
                        blue = -0.4;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("PURPLE")) {

                        color = 0;

                        red = 0.5;
                        green = 0;
                        blue = 0.5;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("BLUE")) {

                        color = 0;

                        red = -1;
                        green = 0;
                        blue = 1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("BROWN")) {

                        color = 0;

                        red = 0.5;
                        green = 0;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("GREEN")) {

                        color = 0;

                        red = -1;
                        green = 0.6;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);
                    }else if(particlename.equalsIgnoreCase("DARK")) {

                        color = 0;

                        red = 0.1;
                        green = 0.1;
                        blue = 0.1;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }else if(particlename.equalsIgnoreCase("RED")) {

                        color = 0;

                        red = 1;
                        green = 0;
                        blue = 0;

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".ColorN", particlename);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Color", color);

                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Red", red);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Green", green);
                        main.getPlayerParticulesConfig().set("Player."+pn+"."+paths+".Blue", blue);

                    }

                }

                try {
                    main.getPlayerParticulesConfig().save(main.getPlayerParticles());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                YamlConfiguration.loadConfiguration(main.getPlayerParticles());

                SColor(p, paths);

            }else if(current.getType() == Material.ARROW && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§ePrevious page")) {

                p.closeInventory();

                SParticles(p, paths);

            }else if(current.getType() == Material.BARRIER && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§4Exit")) {

                p.closeInventory();

            }

        }

    }

}