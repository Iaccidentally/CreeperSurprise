package me.iaccidentally.creepersurprise;

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
import org.bukkit.util.Vector;


public class CreeperSurprise extends JavaPlugin implements Listener
{
	public static final Logger log = Logger.getLogger("Minecraft");
	private FireworkEffect.Builder builder = FireworkEffect.builder();

	@Override
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
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
				int r = 0 + (int)(Math.random() * ((4 - 0) + 1));
				Firework firework = (Firework)event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.FIREWORK);
				FireworkMeta meta = (FireworkMeta)firework.getFireworkMeta();
				builder = FireworkEffect.builder();
				builder.with(FireworkEffect.Type.CREEPER);
				builder.withColor(org.bukkit.Color.LIME);
				builder.withFade(org.bukkit.Color.GREEN);
				builder.withTrail();
				builder.withFlicker();
				FireworkEffect effect = builder.build();
				meta.addEffect(effect);
				meta.setPower(r);
				//firework.setVelocity(vector);
				firework.setFireworkMeta(meta);
			}
		}
	}
}
