package fr.WarzouMc.JustSpawn.op.plugin.modifManager;

import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.op.cmd.opSpawn.OpSpawnCommand;
import net.minecraft.server.v1_10_R1.SoundEffects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ModifManager implements Listener {

    private main main;

    public ModifManager(main main){this.main = main;}

    private Inventory SpawnLoc = Bukkit.createInventory(null, 18, "§6Spawn Location");
    private Inventory FireworkSpawnLoc = Bukkit.createInventory(null, 9, "§6Firework Spawn Location");

    private ItemStack goldPlate = new ItemStack(Material.GOLD_PLATE);
    private ItemMeta goldPlatem = goldPlate.getItemMeta();

    private ItemStack beacon = new ItemStack(Material.BEACON);
    private ItemMeta beaconm = beacon.getItemMeta();

    private ItemStack ironPlate = new ItemStack(Material.IRON_PLATE);
    private ItemMeta ironPlatem = ironPlate.getItemMeta();

    private int onchat = 0;
    private Player playerR = null;

    private ArrayList<String> msg = new ArrayList<>();
    private ArrayList<Float> coo = new ArrayList<>();

    private boolean check = false;

    private String error = null;

    @EventHandler
    public void onInventory(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();
        String pn = p.getName();

        Inventory inv = e.getInventory();
        String invn = inv.getName();

        ItemStack current = e.getCurrentItem();

        double x = main.getConfig().getDouble("spawn.Location.x");
        double y = main.getConfig().getDouble("spawn.Location.y");
        double z = main.getConfig().getDouble("spawn.Location.z");

        double px = p.getLocation().getX();
        double py = p.getLocation().getY();
        double pz = p.getLocation().getZ();

        if(invn.equalsIgnoreCase("§2JustSpawn Operator Config")){

            e.setCancelled(true);

            if(current.getType() == Material.TORCH && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Player can test his particles : ")){

                if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.ParticleTest", false);

                    main.saveConfig();

                    ItemMeta itm = current.getItemMeta();
                    itm.setDisplayName("§6Player can test his particles : §4false");
                    inv.getItem(2).setItemMeta(itm);

                }else if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.ParticleTest", true);

                    main.saveConfig();

                    ItemMeta itm = current.getItemMeta();
                    itm.setDisplayName("§6Player can test his particles : §2true");
                    inv.getItem(2).setItemMeta(itm);
                }

            }else if(current.getType() == Material.BED && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn Location :")){

                p.closeInventory();

                beaconm.setDisplayName("§6Set spawn at your location:");
                beaconm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                beacon.setItemMeta(beaconm);

                goldPlatem.setDisplayName("§6Spawn location");
                goldPlatem.setLore(Arrays.asList("§5§ox: §r§6"+px, "§5§oy: §r§6"+py, "§5§oz: §r§6"+pz));
                goldPlate.setItemMeta(goldPlatem);

                ironPlatem.setDisplayName("§6Set a position at your spawn");
                ironPlatem.setLore(Arrays.asList("§5§oUSE NUMBER WITH VIRGULES"));
                ironPlate.setItemMeta(ironPlatem);

                SpawnLoc.setItem(2, goldPlate);
                SpawnLoc.setItem(4, beacon);
                SpawnLoc.setItem(6, ironPlate);

                p.openInventory(SpawnLoc);

            }else if(current.getType() == Material.REDSTONE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Player can use particles : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                ItemMeta itm = current.getItemMeta();

                if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.cmd.Particles", false);

                    main.saveConfig();

                    itm.setDisplayName("§6Player can use particles : §4false");
                    inv.getItem(0).setItemMeta(itm);

                }else if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.cmd.Particles", true);

                    main.saveConfig();

                    itm.setDisplayName("§6Player can use particles : §2true");
                    inv.getItem(0).setItemMeta(itm);

                }

            }else if(current.getType() == Material.SUGAR && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Enable fade color : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                ItemMeta itm = current.getItemMeta();

                if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADE", false);

                    main.saveConfig();

                    OpSpawnCommand.ConfigFireworkSpawn(p);

                }else if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADE", true);

                    main.saveConfig();

                    OpSpawnCommand.ConfigFireworkSpawn(p);

                }

            }else if(current.getType() == Material.FIREWORK && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Firework config") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                OpSpawnCommand.ConfigFireworkSpawn(p);

            }else if(current.getType() == Material.FIREWORK && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Spawn firework : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                ItemMeta itm = current.getItemMeta();

                if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.cmd.Fireworks", true);

                    main.saveConfig();

                    itm.setDisplayName("§6Spawn firework : §2true");
                    inv.getItem(0).setItemMeta(itm);


                }else if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.cmd.Fireworks", false);

                    main.saveConfig();

                    itm.setDisplayName("§6Spawn firework : §4false");
                    inv.getItem(0).setItemMeta(itm);


                }

            }else if(current.getType() == Material.DIAMOND && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Trail : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                ItemMeta itm = current.getItemMeta();

                if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.TRAIL", true);

                    main.saveConfig();

                }else if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.TRAIL", false);

                    main.saveConfig();

                }

                OpSpawnCommand.ConfigFireworkSpawn(p);

            }else if(current.getType() == Material.GLOWSTONE_DUST && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Flickers : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                ItemMeta itm = current.getItemMeta();

                if(current.getItemMeta().getDisplayName().endsWith("false")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FLICKER", true);

                    main.saveConfig();

                }else if(current.getItemMeta().getDisplayName().endsWith("true")){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FLICKER", false);

                    main.saveConfig();

                }

                OpSpawnCommand.ConfigFireworkSpawn(p);

            }else if(current.getType() == Material.SULPHUR && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6Power : ") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn")){

                OpSpawnCommand.ConfigFireworkPowerSpawn(p);

            }else if(current.getType() == Material.BED && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn Firework Location :")){

                p.closeInventory();

                beaconm.setDisplayName("§6Firework Spawn location");
                beaconm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                beacon.setItemMeta(beaconm);

                goldPlatem.setDisplayName("§6Set firework spawn at your location");
                goldPlatem.setLore(Arrays.asList("§5§ox: §r§6"+px, "§5§oy: §r§6"+py, "§5§oz: §r§6"+pz));
                goldPlate.setItemMeta(goldPlatem);

                ironPlatem.setDisplayName("§6Set a position at your firework");
                ironPlatem.setLore(Arrays.asList("§5§oUSE NUMBER WITH VIRGULES"));
                ironPlate.setItemMeta(ironPlatem);

                FireworkSpawnLoc.setItem(2, goldPlate);
                FireworkSpawnLoc.setItem(4, beacon);
                FireworkSpawnLoc.setItem(6, ironPlate);

                p.openInventory(FireworkSpawnLoc);

            }else if(current.equals(OpSpawnCommand.getTypeS().get(main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")))){

                OpSpawnCommand.ConfigFireworkTypeSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6+") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oType") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() < 5){

                    main.getConfig().set("spawn.cmd.FireworksConfig.TYPE", main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")+1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.TYPE", 0);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkTypeSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6-") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oType") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() > 1){

                    main.getConfig().set("spawn.cmd.FireworksConfig.TYPE", main.getConfig().getInt("spawn.cmd.FireworksConfig.TYPE")-1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.TYPE", 4);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkTypeSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6+") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oPower") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() < 10){

                    main.getConfig().set("spawn.cmd.FireworksConfig.POWER", main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER")+1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.POWER", 1);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkPowerSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6-") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oPower") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() > 1){

                    main.getConfig().set("spawn.cmd.FireworksConfig.POWER", main.getConfig().getInt("spawn.cmd.FireworksConfig.POWER")-1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.POWER", 10);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkPowerSpawn(p);

            }else if(current.equals(OpSpawnCommand.getColorS().get(main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")))){

                OpSpawnCommand.ConfigFireworkColorSpawn(p);

            }else if(current.equals(OpSpawnCommand.getFadeColorS().get(main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")))){

                OpSpawnCommand.ConfigFireworkFadeColorSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6-") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oColor") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() > 1){

                    main.getConfig().set("spawn.cmd.FireworksConfig.COLOR", main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")-1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.COLOR", 15);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkColorSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6+") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oColor") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() < 16){

                    main.getConfig().set("spawn.cmd.FireworksConfig.COLOR", main.getConfig().getInt("spawn.cmd.FireworksConfig.COLOR")+1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.COLOR", 0);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkColorSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6-") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oFade Color") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() > 1){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADECOLOR", main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")-1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADECOLOR", 15);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkFadeColorSpawn(p);

            }else if(current.getType() == Material.STAINED_GLASS_PANE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().startsWith("§6+") && current.getItemMeta().getLore().get(0).equalsIgnoreCase("§5§oSpawn") && current.getItemMeta().getLore().get(1).equalsIgnoreCase("§5§oFireworks") && current.getItemMeta().getLore().get(2).equalsIgnoreCase("§5§oFade Color") && current.getData().getData() == 5){

                if(inv.getItem(4).getAmount() < 16){

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADECOLOR", main.getConfig().getInt("spawn.cmd.FireworksConfig.FADECOLOR")+1);

                }else {

                    main.getConfig().set("spawn.cmd.FireworksConfig.FADECOLOR", 0);

                }

                main.saveConfig();

                OpSpawnCommand.ConfigFireworkFadeColorSpawn(p);

            }

        }else if(invn.equalsIgnoreCase(SpawnLoc.getName())){

            e.setCancelled(true);

            if(current.getType() == Material.GOLD_PLATE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Set firework spawn at your location")){

                main.getConfig().set("spawn.Location.x", px);
                main.getConfig().set("spawn.Location.y", py);
                main.getConfig().set("spawn.Location.z", pz);

                main.saveConfig();

                x = main.getConfig().getDouble("spawn.Location.x");
                y = main.getConfig().getDouble("spawn.Location.y");
                z = main.getConfig().getDouble("spawn.Location.z");

                beaconm.setDisplayName("§6Spawn location");
                beaconm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                inv.getItem(4).setItemMeta(beaconm);

            }else if(current.getType() == Material.IRON_PLATE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Set a position at your spawn")){

                p.closeInventory();
                playerR = p;

                Chat(pn, 1);

                p.sendMessage("§2>> §1[§9Just§cSpawn§4] §6: Send x coordinate !");

            }

        }else if(invn.equalsIgnoreCase(FireworkSpawnLoc.getName())){

            e.setCancelled(true);

            if(current.getType() == Material.GOLD_PLATE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Set firework spawn at your location")){

                main.getConfig().set("spawn.cmd.FireworksConfig.Location.x", px);
                main.getConfig().set("spawn.cmd.FireworksConfig.Location.y", py);
                main.getConfig().set("spawn.cmd.FireworksConfig.Location.z", pz);

                main.saveConfig();

                x = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.x");
                y = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.y");
                z = main.getConfig().getDouble("spawn.cmd.FireworksConfig.Location.z");

                beaconm.setDisplayName("§6Firework Spawn location");
                beaconm.setLore(Arrays.asList("§5§ox: §r§6"+x, "§5§oy: §r§6"+y, "§5§oz: §r§6"+z));
                inv.getItem(4).setItemMeta(beaconm);

            }else if(current.getType() == Material.IRON_PLATE && current.hasItemMeta() && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Set a position at your firework")){

                p.closeInventory();
                playerR = p;

                Chat(pn, 4);

                p.sendMessage("§2>> §1[§9Just§cSpawn§4] §6: Send x coordinate !");

            }

        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onChat(PlayerChatEvent e){

        msg();

        Player player = e.getPlayer();
        String playername = player.getName();
        String message = e.getMessage();
        String messageCheck = message;

        onchat = main.getChatConfig().getInt(playername);

        if(player == playerR){

            if(onchat > 0){

                if(onchat < 4 && onchat >= 1){

                    e.setCancelled(true);

                    Check(message, 1);

                    if(check == true){

                        if(onchat == 3) {
                            coo.add(Float.valueOf(message));
                            Chat(playername, 0);

                            main.getConfig().set("spawn.Location.x", coo.get(0));
                            main.getConfig().set("spawn.Location.y", coo.get(1));
                            main.getConfig().set("spawn.Location.z", coo.get(2));

                            main.saveConfig();

                            OpSpawnCommand.Config0(player);
                            coo.clear();
                        }else{
                            Chat(playername, onchat+1);
                            player.sendMessage(msg.get(onchat));
                            coo.add(Float.valueOf(message));
                        }

                        check = false;

                    }else {

                        player.sendMessage(error);

                    }

                }else if(onchat < 7 && onchat >= 4){

                    e.setCancelled(true);

                    Check(message, 2);

                    if(check == true){

                        if(onchat == 6) {
                            coo.add(Float.valueOf(message));
                            Chat(playername, 0);

                            main.getConfig().set("spawn.cmd.FireworksConfig.Location.x", coo.get(0));
                            main.getConfig().set("spawn.cmd.FireworksConfig.Location.y", coo.get(1));
                            main.getConfig().set("spawn.cmd.FireworksConfig.Location.z", coo.get(2));

                            main.saveConfig();

                            OpSpawnCommand.Config0(player);
                            coo.clear();
                        }else{
                            Chat(playername, onchat+1);
                            player.sendMessage(msg.get(onchat));
                            coo.add(Float.valueOf(message));
                        }

                        check = false;

                    }else {

                        player.sendMessage(error);

                    }

                }

            }

        }

    }

    public void Check(String message, int type) {

        if(type == 1){

            ErrorFloat(message);

        }else if(type == 2){

            ErrorFloat(message);

        }

    }

    public void ErrorFloat(String message) {

        String message1 = message;

        if(message.startsWith("-")){

            message = message1.substring(1);


        }

        if(message.replace("-", "").length() < message.length()){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: §6"+message1.replace("-", "§4-§6")+"\n" +
                    "§2>> §6You can't set §4\"-\"§6 at this position";

        }else if(message.startsWith(".")){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: §4.§6"+message.replace(".", "")+"\n" +
                    "§2>> §6You can't start with §4\".\"";

        }else if(message.endsWith(".")){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: "+message.replace(".", "")+"§4."+"\n" +
                    "§2>> §6You can't end with §4\".\"";

        }else if(message.replace(".", "").length() < message.length()-1){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: "+message.replace(".", "§4.§6")+"\n" +
                    "§2>> §6You can't set more of one §4\".\"";

        }else if(message.replace(".", "").length() == message.length()){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: "+message+"\n" +
                    "§2>> §6This is not a §4VIRGULES§6 number";

        }else if(message.replace(".", "").replace(" ", "").length() == 0){

            error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: "+message+"\n" +
                    "§2>> §6This is not a number";

        }else {

            String messagecheck = "§6"+message.replace(".", " ");

            ArrayList<String> chars = new ArrayList<>();

            boolean continu = false;

            for(int messages = 0; messages < message1.length(); messages++){

                for(int i = 0; i < 10; i++){

                    char c = message1.charAt(messages);
                    char ic = Character.forDigit(i, 10);

                    if(c == ic || c == '.' || c == '-'){

                        continu = true;

                        break;

                    }else{

                        continu = false;

                    }

                    if(i == 9 && continu == false){

                        chars.add(c+"");

                    }

                }

            }

            if(chars.size() != 0){

                for(int i = 0; i < chars.size(); i++){

                    messagecheck = message1.replace(chars.get(i), "§4"+chars.get(i)+"§6");

                }

                error = "§2>> §1[§9Just§cSpawn§4] §4[§cerror§4] §6: "+messagecheck+"\n" +
                        "§2>> §6This is not a number";

            }else {

                check = true;

            }

        }

    }

    public void Chat(String pn, int i) {

        main.getChatConfig().set(pn, i);

        try {
            main.getChatConfig().save(main.getChat());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        YamlConfiguration.loadConfiguration(main.getChat());

    }

    public void msg() {

        msg.clear();

        msg.add("");
        msg.add("§2>> §1[§9Just§cSpawn§4] §6: Send y coordinate !");
        msg.add("§2>> §1[§9Just§cSpawn§4] §6: Send z coordinate !");
        msg.add("");
        msg.add("§2>> §1[§9Just§cSpawn§4] §6: Send y coordinate !");
        msg.add("§2>> §1[§9Just§cSpawn§4] §6: Send z coordinate !");

    }

}
