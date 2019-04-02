package fr.WarzouMc.JustSpawn.serveur.particles;

import org.bukkit.inventory.ItemStack;

import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;

public enum Heads {


	ARROWLEFTUP("ODY1NDI2YTMzZGY1OGI0NjVmMDYwMWRkOGI5YmVjMzY5MGIyMTkzZDFmOTUwM2MyY2FhYjc4ZjZjMjQzOCJ9fX0=","arrowleftup", "§5Left and Up", Types.HELIX_LEFT_UP),
	ARROWLEFTDOWN("MzU0Y2U4MTU3ZTcxZGNkNWI2YjE2NzRhYzViZDU1NDkwNzAyMDI3YzY3NWU1Y2RjZWFjNTVkMmZiYmQ1YSJ9fX0=","arrowleftdown", "§5Left and Down", Types.HELIX_LEFT_DOWN),
	ARROWRIGHTUP("OTBlMGE0ZDQ4Y2Q4MjlhNmQ1ODY4OTA5ZDY0M2ZhNGFmZmQzOWU4YWU2Y2FhZjZlYzc5NjA5Y2Y3NjQ5YjFjIn19fQ==","arrowrightup","§5Right and Up", Types.HELIX_RIGHT_UP),
	ARROWRIGHTDOWN("MzVjYmRiMjg5OTFhMTZlYjJjNzkzNDc0ZWY3ZDBmNDU4YTVkMTNmZmZjMjgzYzRkNzRkOTI5OTQxYmIxOTg5In19fQ==","arrowrightdown","§5Right and Down", Types.HELIX_RIGHT_DOWN),
	ARROWUP("MzA0MGZlODM2YTZjMmZiZDJjN2E5YzhlYzZiZTUxNzRmZGRmMWFjMjBmNTVlMzY2MTU2ZmE1ZjcxMmUxMCJ9fX0=","arrowup","§5Up", Types.BALL_UP),
	ARROWDOWN("NzQzNzM0NmQ4YmRhNzhkNTI1ZDE5ZjU0MGE5NWU0ZTc5ZGFlZGE3OTVjYmM1YTEzMjU2MjM2MzEyY2YifX19","arrowdown","§5Down", Types.BALL_DOWN),
	ARROWRIGHT("MTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19","arrowright","§5Right", Types.BALL_RIGHT),
	ARROWLEFT("YmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==","arrowleft","§5Left", Types.BALL_LEFT),
	ARROWBACKWARD("ODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0=","arrowbackward","§5Backward", Types.BALL_BACKWARD),
	ARROWFORWARD("MmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0=","arrowforward","§5Forward", Types.BALL_FORWARD);

	private ItemStack item;
	private String idTag;
	private Types type;
	private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
	private Heads(String texture, String id, String displayname, Types types)
	{
		item = Head.createSkull(prefix + texture, id, displayname, types);
		idTag = id;
		type = types;
	}


	public ItemStack getItemStack()
	{
		return item;
	}

	public String getName()
	{
		return idTag;
	}


	public Types getTypes() {
		return type;
	}

}
