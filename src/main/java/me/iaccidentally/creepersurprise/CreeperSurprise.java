package me.iaccidentally.creepersurprise;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.util.Vector;


public class CreeperSurprise extends JavaPlugin implements Listener
{
	public static final Logger log = Logger.getLogger("Minecraft");
	private FireworkEffect.Builder builder = FireworkEffect.builder();

	@Override
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		if (!new File(getDataFolder(), "config.yml").exists())
		{
			log.info("[CreeperSurprise] Config file generated!");
			saveDefaultConfig();
		}
		log.info("[CreeperSurprise] enabled!");
	}

	@Override
	public void onDisable()
	{
		log.info("[CreeperSurpise] disabled!");
	}

	public FireworkEffect.Builder getFireworkBuilder()
	{
		return builder;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event)
	{
		if (event.getEntity() instanceof Creeper)
		{
			for (int i = 0; i < 10; i++)
			{
				//final Vector vector = event.getEntity().getLocation().getDirection().setY(i * 0.50);

				Firework firework = (Firework)event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.FIREWORK);
				FireworkMeta meta = (FireworkMeta)firework.getFireworkMeta();
				builder = FireworkEffect.builder();
				builder.with(FireworkEffect.Type.CREEPER);
				builder.withColor(org.bukkit.Color.LIME);
				builder.withFade(org.bukkit.Color.GREEN);
				builder.trail(trail());
				builder.flicker(flicker());
				meta.addEffect(effectball());
				meta.addEffect(effectballlarge());
				meta.addEffect(effectburst());
				meta.addEffect(effectcreeper());
				meta.addEffect(effectstar());
				meta.setPower(power());
				//firework.setVelocity(vector);
				firework.setFireworkMeta(meta);
			}
		}
	}

	public int power()
	{
		int power = 0 + (int)(Math.random() * ((getConfig().getInt("power") - 0) + 1));
		return power;
	}

	public boolean trail()
	{
		boolean trail = getConfig().getBoolean("trail");
		return trail;
	}

	public boolean flicker()
	{
		boolean flicker = getConfig().getBoolean("flicker");
		return flicker;
	}

	public FireworkEffect effectball()
	{
		builder = FireworkEffect.builder();
		boolean effecttype = getConfig().getBoolean("effect ball");
		if (effecttype == true)
		{
			FireworkEffect effect = builder.build();
			builder.with(FireworkEffect.Type.BALL);
			return effect;
		}
		else
		{
			return null;
		}
	}

	public FireworkEffect effectballlarge()
	{
		builder = FireworkEffect.builder();
		boolean effecttype = getConfig().getBoolean("effect large_ball");
		if (effecttype == true)
		{
			FireworkEffect effect = builder.build();
			builder.with(FireworkEffect.Type.BALL_LARGE);
			return effect;
		}
		else
		{
			return null;
		}
	}

	public FireworkEffect effectburst()
	{
		builder = FireworkEffect.builder();
		boolean effecttype = getConfig().getBoolean("effect burst");
		if (effecttype == true)
		{
			FireworkEffect effect = builder.build();
			builder.with(FireworkEffect.Type.BURST);
			return effect;
		}
		else
		{
			return null;
		}
	}

	public FireworkEffect effectcreeper()
	{
		builder = FireworkEffect.builder();
		boolean effecttype = getConfig().getBoolean("effect creeper");
		if (effecttype == true)
		{
			FireworkEffect effect = builder.build();
			builder.with(FireworkEffect.Type.CREEPER);
			return effect;
		}
		else
		{
			return null;
		}
	}

	public FireworkEffect effectstar()
	{
		builder = FireworkEffect.builder();
		boolean effecttype = getConfig().getBoolean("effect star");
		if (effecttype == true)
		{
			FireworkEffect effect = builder.build();
			builder.with(FireworkEffect.Type.STAR);
			return effect;
		}
		else
		{
			return null;
		}
	}
}
