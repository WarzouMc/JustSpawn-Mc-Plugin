package fr.WarzouMc.JustSpawn.op.cmd.opSpawn;

import fr.WarzouMc.JustSpawn.main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class OpSpawnCommand implements CommandExecutor {

    private static main main;

    public OpSpawnCommand(main main){this.main = main;}

    private static Inventory config = Bukkit.createInventory(null, 18, "§2JustSpawn Operator Config");

    private static ItemStack torch = new ItemStack(Material.TORCH);
    private static ItemMeta torchm = torch.getItemMeta();

    private static ItemStack bed = new ItemStack(Material.BED);
    private static ItemMeta bedm = torch.getItemMeta();

    private static ItemStack redstone = new ItemStack(Material.REDSTONE);
    private static ItemMeta redstonem = redstone.getItemMeta();

    private static ItemStack firework = new ItemStack(Material.FIREWORK);
    private static ItemMeta fireworkm = firework.getItemMeta();

    private static ItemStack slime = new ItemStack(Material.SLIME_BALL, 1);
    private static ItemMeta slimem = slime.getItemMeta();

    private static ItemStack slime1 = new ItemStack(Material.SLIME_BALL, 2);
    private static ItemMeta slimem1 = slime1.getItemMeta();

    private static ItemStack feather = new ItemStack(Material.FEATHER, 3);
    private static ItemMeta featherm = feather.getItemMeta();

    private static ItemStack creeper = new ItemStack(Material.SKULL_ITEM, 4, (byte)4);
    private static ItemMeta creeperm = creeper.getItemMeta();

    private static ItemStack star = new ItemStack(Material.NETHER_STAR, 5);
    private static ItemMeta starm = star.getItemMeta();

    private static ItemStack greenglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)5);
    private static ItemMeta greenglassm = greenglass.getItemMeta();

    private static ItemStack dye = new ItemStack(Material.INK_SACK);
    private static ItemMeta dyem = dye.getItemMeta();

    private static ItemStack sugar = new ItemStack(Material.SUGAR);
    private static ItemMeta sugarm = sugar.getItemMeta();

    private static ItemStack diamond = new ItemStack(Material.DIAMOND);
    private static ItemMeta diamondm = diamond.getItemMeta();

    private static ItemStack gunpowder = new ItemStack(Material.SULPHUR);
    private static ItemMeta gunpowderm = gunpowder.getItemMeta();

    private static ItemStack glowstonedust = new ItemStack(Material.GLOWSTONE_DUST);
    private static ItemMeta glowstonedustm = glowstonedust.getItemMeta();

    private static ArrayList<ItemStack> typeS = new ArrayList<>();

    private static ArrayList<ItemStack> colorS = new ArrayList<>();
    private static ArrayList<ItemStack> fadecolorS = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command msg, String cmd, String[] args){

        Player p = (Player) sender;
        String pn = p.getName();

        if(sender instanceof  Player){

            if(cmd.equalsIgnoreCase("opspawn")){

                if(args.length == 1){

                    if(args[0].equalsIgnoreCase("cmd")){
                        Config1(p);
                    }else if (args[0].equalsIgnoreCase("connection")) {

                    }else if (args[0].equalsIgnoreCase("general")) {
                        Config0(p);
                    }

                }

            }

        }

        return false;
    }

    public static void Config1(Player p) {

        config.clear();

        String enableparticles = null;

        if(main.getConfig().getBoolean("spawn.cmd.Particles") == true){
            enableparticles = "§2true";
        }else if(main.getConfig().getBoolean("spawn.cmd.Particles") == false){
            enableparticles = "§4false";
        }

        redstonem.setDisplayName("§6Player can use particles : "+enableparticles);
        redstonem.setLore(Arrays.asList("§5§oSpawn"));
        redstone.setItemMeta(redstonem);

        fireworkm.setDisplayName("§6Firework config");
        fireworkm.setLore(Arrays.asList("§5§oSpawn"));
        firework.setItemMeta(fireworkm);

        config.setItem(0, redstone);
        config.setItem(1, firework);

        p.openInventory(config);

    }

    public static void Config0(Player p) {

        config.clear();

        String enable = null;

        if(main.getConfig().getBoolean("spawn.ParticleTest") == true){
            enable = "§2true";
        }else if(main.getConfig().getBoolean("spawn.ParticleTest") == false){
            enable = "§4false";
        }

        torchm.setDisplayName("§6Player can test his particles : "+enable);
        torch.setItemMeta(torchm);

        double x = main.getConfig().getDouble("spawn.Location.x");
        double y = main.getConfig().getDouble("spawn.Location.y");
        double z = main.getConfig().getDouble("spawn.Location.z");

        bedm.setDisplayName("§6Spawn Location :");
        bedm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
        bed.setItemMeta(bedm);

        config.setItem(2, torch);
        config.setItem(6, bed);

        p.openInventory(config);

    }

    public static void ConfigFireworkSpawn(Player p) {

        config.clear();

        String enablefirework = null;

        if(main.getConfig().getBoolean("spawn.cmd.Fireworks") == true){
            enablefirework = "§2true";
        }else if(main.getConfig().getBoolean("spawn.cmd.Fireworks") == false){
            enablefirework = "§4false";
        }

        fireworkm.setDisplayName("§6Spawn firework : "+enablefirework);
        fireworkm.setLore(Arrays.asList("§5§oSpawn"));
        firework.setItemMeta(fireworkm);

        CreateTypeFirework("spawn");
        CreateColorFirework("spawn");

        if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FADE") == true){
            enablefirework = "§2true";
            CreateWhihtFade("spawn", true);
        }else if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FADE") == false){
            enablefirework = "§4false";
            CreateWhihtFade("spawn", false);
        }

        sugarm.setDisplayName("§6Enable fade color : "+enablefirework);
        sugarm.setLore(Arrays.asList("§5§oSpawn"));
        sugar.setItemMeta(sugarm);

        config.setItem(0, firework);
        config.setItem(1, typeS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")));
        config.setItem(2, colorS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")));
        config.setItem(3, sugar);

    }

    public static void CreateWhihtFade(String path, boolean b) {

        if(path.equalsIgnoreCase("spawn")){

            if(b == true){

                String enablefirework = null;

                if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL") == true){
                    enablefirework = "§2true";
                }else if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL") == false){
                    enablefirework = "§4false";
                }

                diamondm.setDisplayName("§6Trail : "+enablefirework);
                diamondm.setLore(Arrays.asList("§5§oSpawn"));
                diamond.setItemMeta(diamondm);

                if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER") == true){
                    enablefirework = "§2true";
                }else if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER") == false){
                    enablefirework = "§4false";
                }

                glowstonedustm.setDisplayName("§6Flickers : "+enablefirework);
                glowstonedustm.setLore(Arrays.asList("§5§oSpawn"));
                glowstonedust.setItemMeta(glowstonedustm);

                gunpowder = new ItemStack(Material.SULPHUR, main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                gunpowderm = gunpowder.getItemMeta();

                gunpowderm.setDisplayName("§6Power : "+main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                gunpowderm.setLore(Arrays.asList("§5§oSpawn"));
                gunpowder.setItemMeta(gunpowderm);

                double x = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.x");
                double y = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.y");
                double z = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.z");

                bedm.setDisplayName("§6Spawn Firework Location :");
                bedm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                bed.setItemMeta(bedm);

                config.setItem(4, fadecolorS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")));
                config.setItem(5, diamond);
                config.setItem(6, glowstonedust);
                config.setItem(7, gunpowder);
                config.setItem(8, bed);

            }else {

                String enablefirework = null;

                if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL") == true){
                    enablefirework = "§2true";
                }else if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.TRAIL") == false){
                    enablefirework = "§4false";
                }

                diamondm.setDisplayName("§6Trail : "+enablefirework);
                diamondm.setLore(Arrays.asList("§5§oSpawn"));
                diamond.setItemMeta(diamondm);

                if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER") == true){
                    enablefirework = "§2true";
                }else if(main.getConfig().getBoolean("spawn.cmd.FireworksConfig.FLICKER") == false){
                    enablefirework = "§4false";
                }

                glowstonedustm.setDisplayName("§6Flickers : "+enablefirework);
                glowstonedustm.setLore(Arrays.asList("§5§oSpawn"));
                glowstonedust.setItemMeta(glowstonedustm);

                gunpowder = new ItemStack(Material.SULPHUR, main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                gunpowderm = gunpowder.getItemMeta();

                gunpowderm.setDisplayName("§6Power : "+main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
                gunpowderm.setLore(Arrays.asList("§5§oSpawn"));
                gunpowder.setItemMeta(gunpowderm);

                double x = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.x");
                double y = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.y");
                double z = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.z");

                bedm.setDisplayName("§6Spawn Firework Location :");
                bedm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                bed.setItemMeta(bedm);

                config.setItem(4, diamond);
                config.setItem(5, glowstonedust);
                config.setItem(6, gunpowder);
                config.setItem(7, bed);

            }

        }

    }

    public static void CreateColorFirework(String path) {

        if(path.equalsIgnoreCase("spawn")){

            colorS.clear();
            fadecolorS.clear();

            for(int i = 0; i <= 15; i++){

                dye = new ItemStack(Material.INK_SACK, i+1, (byte)i);
                dyem = dye.getItemMeta();

                dyem.setDisplayName("§6"+i+" Color");
                dyem.setLore(Arrays.asList("§5§oSpawn"));
                dye.setItemMeta(dyem);

                colorS.add(dye);

                dye = new ItemStack(Material.INK_SACK, i+1, (byte)i);
                dyem = dye.getItemMeta();

                dyem.setDisplayName("§6"+i+" Fade Color");
                dyem.setLore(Arrays.asList("§5§oSpawn"));
                dye.setItemMeta(dyem);

                fadecolorS.add(dye);

            }

        }

    }

    public static void ConfigFireworkColorSpawn(Player p){

        config.clear();

        greenglassm.setDisplayName("§6+");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oColor"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(5, greenglass);
        config.setItem(4, colorS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")));

        greenglassm.setDisplayName("§6-");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oColor"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(3, greenglass);

    }

    public static void ConfigFireworkFadeColorSpawn(Player p){

        config.clear();

        greenglassm.setDisplayName("§6+");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oFade Color"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(5, greenglass);
        config.setItem(4, colorS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")));

        greenglassm.setDisplayName("§6-");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oFade Color"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(3, greenglass);

    }

    public static void CreateTypeFirework(String path) {

        if(path.equalsIgnoreCase("spawn")){

            typeS.clear();

            slimem.setDisplayName("§1Type : §1Ball");
            slimem.setLore(Arrays.asList("§5§oSpawn"));
            slime.setItemMeta(slimem);

            slimem1.setDisplayName("§1Type : §1Large Ball");
            slimem1.addEnchant(Enchantment.FIRE_ASPECT, 100, true);
            slimem1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            slimem1.setLore(Arrays.asList("§5§oSpawn"));
            slime1.setItemMeta(slimem1);

            featherm.setDisplayName("§1Type : §1Burst");
            featherm.setLore(Arrays.asList("§5§oSpawn"));
            feather.setItemMeta(featherm);

            creeperm.setDisplayName("§1Type : §1Creeper");
            creeperm.setLore(Arrays.asList("§5§oSpawn"));
            creeper.setItemMeta(creeperm);

            starm.setDisplayName("§1Type : §1Star");
            starm.setLore(Arrays.asList("§5§oSpawn"));
            star.setItemMeta(starm);

            typeS.add(slime);
            typeS.add(slime1);
            typeS.add(feather);
            typeS.add(creeper);
            typeS.add(star);

        }

    }

    public static void ConfigFireworkTypeSpawn(Player p){

        config.clear();

        greenglassm.setDisplayName("§6+");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oType"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(5, greenglass);
        config.setItem(4, typeS.get(main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")));

        greenglassm.setDisplayName("§6-");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oType"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(3, greenglass);

    }

    public static void ConfigFireworkPowerSpawn(Player p) {

        config.clear();

        greenglassm.setDisplayName("§6+");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oPower"));

        greenglass.setItemMeta(greenglassm);

        gunpowder = new ItemStack(Material.SULPHUR, main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
        gunpowderm = gunpowder.getItemMeta();

        gunpowderm.setDisplayName("§6"+main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER"));
        gunpowderm.setLore(Arrays.asList("§5§oSpawn"));
        gunpowder.setItemMeta(gunpowderm);

        config.setItem(5, greenglass);
        config.setItem(4, gunpowder);

        greenglassm.setDisplayName("§6-");
        greenglassm.setLore(Arrays.asList("§5§oSpawn", "§5§oFireworks", "§5§oPower"));

        greenglass.setItemMeta(greenglassm);

        config.setItem(3, greenglass);

    }

    public static ArrayList<ItemStack> getTypeS() {
        return typeS;
    }

    public static void setTypeS(ArrayList<ItemStack> typeS) {
        OpSpawnCommand.typeS = typeS;
    }

    public static ArrayList<ItemStack> getColorS() {
        return colorS;
    }

    public static void setColorS(ArrayList<ItemStack> colorS) {
        OpSpawnCommand.colorS = colorS;
    }

    public static ArrayList<ItemStack> getFadeColorS() {
        return fadecolorS;
    }

    public static void setFadeColorS(ArrayList<ItemStack> fadecolorS) {
        OpSpawnCommand.fadecolorS = fadecolorS;
    }

}
