package com.clovix.core;
import com.clovix.Clovix;import com.clovix.config.ConfigStore;import com.clovix.config.CoreSettings;import com.clovix.ui.ClovixHud;import com.clovix.ui.ClovixKeybinds;import com.clovix.ui.ModuleManager;import com.clovix.ui.ThemeManager;import net.minecraft.client.Minecraft;import java.util.concurrent.atomic.AtomicBoolean;
/** Core service composition root and managed feature bootstrap. */
public final class CoreManager {
 public static final String VERSION="0.1.0-alpha.1";private final ModuleRegistry modules=new ModuleRegistry();private final EventBus events=new EventBus();private final EditionManager editions=new EditionManager();private final AtomicBoolean initialized=new AtomicBoolean(false);private ConfigStore config;private ModuleManager moduleManager;
 public void initialize(){if(!initialized.compareAndSet(false,true))return;Clovix.LOGGER.info("Starting Clovix Core");config=new ConfigStore(Minecraft.getInstance().gameDirectory.toPath().resolve("config/clovix/core.json"));config.load();var loaded=config.snapshot();if(loaded.isEmpty())CoreSettings.defaults().entrySet().forEach(e->config.put(e.getKey(),e.getValue()));ThemeManager.load(this);
  moduleManager=new ModuleManager(this);
  moduleManager.register(new CoreFeatureModule("settings","Settings & Storage",c->{},()->{}));
  moduleManager.register(new CoreFeatureModule("themes","Theme Engine",c->ThemeManager.load(c),()->{}));
  moduleManager.register(new CoreFeatureModule("ui","Clovix Interface",c->ClovixKeybinds.register(),()->{}));
  moduleManager.register(new CoreFeatureModule("hud","HUD Overlay",c->ClovixHud.register(),()->{}));
  Clovix.LOGGER.info("Core services initialized: {} managed modules",modules.size());
 }
 public void shutdown(){if(moduleManager!=null)moduleManager.stopAll();if(config!=null)config.save();initialized.set(false);}
 public ModuleRegistry modules(){return modules;}public EventBus events(){return events;}public EditionManager editions(){return editions;}public ConfigStore config(){return config;}public ModuleManager moduleManager(){return moduleManager;}public boolean isInitialized(){return initialized.get();}
}
