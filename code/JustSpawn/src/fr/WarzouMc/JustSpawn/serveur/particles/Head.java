package fr.WarzouMc.JustSpawn.serveur.particles;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;

public class Head {

	private main main;
	
	public Head(main main) {this.main = main;}
	
	 public static ItemStack getHead(String name)
	    {
	        for (Heads head : Heads.values())
	        {
	            if (head.getName().equalsIgnoreCase(name))
	            {
	                return head.getItemStack();
	            }
	        }
	        return null;
	    }
	 
	 public static Types getHeadTypes(Types types) {
		 
		 for (Heads head : Heads.values())
	        {
	           if(head.getTypes() == types) {
	        	   
	        	   return head.getTypes();
	        	   
	           }
	        }
		 
		 return null;
		 
	 }
	 
	    public  static ItemStack createSkull(String url, String name, String displayname, Types types)
	    {
	        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
	        if (url.isEmpty()) return head;
	       
	        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
	        headMeta.setDisplayName(displayname);
	        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	       
	        profile.getProperties().put("textures", new Property("textures", url));
	       
	        try
	        {
	            Field profileField = headMeta.getClass().getDeclaredField("profile");
	            profileField.setAccessible(true);
	            profileField.set(headMeta, profile);
	           
	        }
	        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
	        {
	            error.printStackTrace();
	        }
	        head.setItemMeta(headMeta);
	        return head;
	    }
	
}
