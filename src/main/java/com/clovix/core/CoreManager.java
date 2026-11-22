package com.clovix.core;
import com.clovix.Clovix;
import com.clovix.config.ConfigStore;
import com.clovix.config.CoreSettings;
import com.clovix.ui.*;
import net.minecraft.client.Minecraft;
import java.util.concurrent.atomic.AtomicBoolean;
/** Core service composition root and managed feature bootstrap. */
public final class CoreManager {
 public static final String VERSION="0.2.0-alpha.1";
 private final ModuleRegistry modules=new ModuleRegistry();
 private final EventBus events=new EventBus();
 private final EditionManager editions=new EditionManager();
 private final UiComponentRegistry components=new UiComponentRegistry();
 private final SettingRegistry settings=new SettingRegistry();
 private final VisualRegistry visuals=new VisualRegistry();
 private final HudLayoutRegistry hudLayout=new HudLayoutRegistry();
 private final AtomicBoolean initialized=new AtomicBoolean(false);
 private ConfigStore config;private ModuleManager moduleManager;
 public void initialize(){
  if(!initialized.compareAndSet(false,true))return;
  Clovix.LOGGER.info("Starting Clovix Core {}",VERSION);
  config=new ConfigStore(Minecraft.getInstance().gameDirectory.toPath().resolve("config/clovix/core.json"));
  config.load();var loaded=config.snapshot();if(loaded.isEmpty())CoreSettings.defaults().entrySet().forEach(e->config.put(e.getKey(),e.getValue()));
  ThemeManager.load(this);moduleManager=new ModuleManager(this);
  moduleManager.register(new CoreFeatureModule("settings","Settings & Storage",c->{},()->{}));
  moduleManager.register(new CoreFeatureModule("themes","Theme Engine",c->ThemeManager.load(c),()->{}));
  moduleManager.register(new CoreFeatureModule("ui","Clovix Interface",c->ClovixKeybinds.register(),()->{}));
  moduleManager.register(new CoreFeatureModule("hud","HUD Overlay",c->ClovixHud.register(),()->{}));
  Clovix.LOGGER.info("Core services initialized: {} modules, {} UI components",modules.size(),components.all().size());
 }
 public void shutdown(){if(moduleManager!=null)moduleManager.stopAll();if(config!=null)config.save();initialized.set(false);}
 public ModuleRegistry modules(){return modules;}public EventBus events(){return events;}public EditionManager editions(){return editions;}
 public ConfigStore config(){return config;}public ModuleManager moduleManager(){return moduleManager;}public boolean isInitialized(){return initialized.get();}
 public UiComponentRegistry components(){return components;}public SettingRegistry settings(){return settings;}public VisualRegistry visuals(){return visuals;}public HudLayoutRegistry hudLayout(){return hudLayout;}
}