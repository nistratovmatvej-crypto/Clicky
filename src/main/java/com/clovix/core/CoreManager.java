package com.clovix.core;
import com.clovix.Clovix;import com.clovix.config.ConfigStore;import com.clovix.config.CoreSettings;import net.minecraft.client.Minecraft;import java.util.concurrent.atomic.AtomicBoolean;
/** Core service composition root. */
public final class CoreManager {
 public static final String VERSION="0.1.0-alpha.1";private final ModuleRegistry modules=new ModuleRegistry();private final EventBus events=new EventBus();private final EditionManager editions=new EditionManager();private final AtomicBoolean initialized=new AtomicBoolean(false);private ConfigStore config;
 public void initialize(){if(!initialized.compareAndSet(false,true))return;Clovix.LOGGER.info("Starting Clovix Core");config=new ConfigStore(Minecraft.getInstance().gameDirectory.toPath().resolve("config/clovix/core.json"));config.load();var loaded=config.snapshot();if(loaded.isEmpty())CoreSettings.defaults().entrySet().forEach(e->config.put(e.getKey(),e.getValue()));Clovix.LOGGER.info("Core configuration initialized");}
 public void shutdown(){var list=modules.all().stream().toList();for(int i=list.size()-1;i>=0;i--)try{list.get(i).shutdown();}catch(Exception ex){ClovixLog.error("Module shutdown failed: "+list.get(i).id(),ex);}if(config!=null)config.save();initialized.set(false);}
 public ModuleRegistry modules(){return modules;}public EventBus events(){return events;}public EditionManager editions(){return editions;}public ConfigStore config(){return config;}public boolean isInitialized(){return initialized.get();}
}
