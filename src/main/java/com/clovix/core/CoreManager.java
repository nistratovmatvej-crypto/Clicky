package com.clovix.core;
import java.util.concurrent.atomic.AtomicBoolean;
/** Core service composition root. */
public final class CoreManager {
    public static final String VERSION = "0.1.0-alpha.1";
    private final ModuleRegistry modules = new ModuleRegistry();
    private final EventBus events = new EventBus();
    private final EditionManager editions = new EditionManager();
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    public void initialize() {
        if (!initialized.compareAndSet(false, true)) return;
        ClovixLog.info("Starting core services");
        // Module bootstrapping is deliberately centralized; module ordering is defined as dependencies arrive.
    }
    public void shutdown() {
        var list = modules.all().stream().toList();
        for (int i = list.size()-1; i >= 0; i--) try { list.get(i).shutdown(); } catch (Exception ex) { ClovixLog.error("Module shutdown failed: " + list.get(i).id(), ex); }
        initialized.set(false);
    }
    public ModuleRegistry modules() { return modules; }
    public EventBus events() { return events; }
    public EditionManager editions() { return editions; }
    public boolean isInitialized() { return initialized.get(); }
}
