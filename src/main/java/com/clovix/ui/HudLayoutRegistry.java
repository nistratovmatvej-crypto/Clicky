package com.clovix.ui;
import java.util.*;import java.util.concurrent.ConcurrentHashMap;
/** Runtime HUD layout model for editor drag, scale, color and visibility operations. */
public final class HudLayoutRegistry {
 private final Map<String,HudElementSpec> elements=new ConcurrentHashMap<>();
 public void register(HudElementSpec e){elements.putIfAbsent(e.id(),e);}public Optional<HudElementSpec> get(String id){return Optional.ofNullable(elements.get(id));}
 public List<HudElementSpec> all(){return elements.values().stream().sorted(Comparator.comparing(HudElementSpec::id)).toList();}
 public boolean update(HudElementSpec e){return elements.replace(e.id(),e)!=null;}public boolean remove(String id){return elements.remove(id)!=null;}public void clear(){elements.clear();}
}