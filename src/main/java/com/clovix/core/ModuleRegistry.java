package com.clovix.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/** Thread-safe module registry. Registration rejects duplicate IDs. */
public final class ModuleRegistry {
    private final Map<String, ClovixModule> modules = new ConcurrentHashMap<>();
    public void register(ClovixModule module) {
        Objects.requireNonNull(module, "module");
        String id = Objects.requireNonNull(module.id(), "module id").trim();
        if (id.isEmpty()) throw new IllegalArgumentException("Module id cannot be blank");
        if (modules.putIfAbsent(id, module) != null) throw new IllegalStateException("Duplicate module id: " + id);
    }
    public Optional<ClovixModule> get(String id) { return Optional.ofNullable(modules.get(id)); }
    public Collection<ClovixModule> all() { return List.copyOf(modules.values()); }
    public boolean contains(String id) { return modules.containsKey(id); }
    public int size() { return modules.size(); }
}
