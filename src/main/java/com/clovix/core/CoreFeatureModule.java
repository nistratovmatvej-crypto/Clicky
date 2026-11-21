package com.clovix.core;
import java.util.Objects;
import java.util.function.Consumer;
/** Small lifecycle-managed feature module with explicit enable/disable hooks. */
public final class CoreFeatureModule extends AbstractModule {
 private final String id,name;private final Consumer<CoreManager> onStart;private final Runnable onStop;
 public CoreFeatureModule(String id,String name,Consumer<CoreManager> onStart,Runnable onStop){this.id=Objects.requireNonNull(id);this.name=Objects.requireNonNull(name);this.onStart=onStart==null?c->{}:onStart;this.onStop=onStop==null?()->{}:onStop;}
 @Override public String id(){return id;}@Override public String displayName(){return name;}
 @Override public void initialize(CoreManager core){onStart.accept(core);}
 @Override public void shutdown(){onStop.run();}
}
