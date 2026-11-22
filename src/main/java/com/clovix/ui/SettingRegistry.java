package com.clovix.ui;
import java.util.*;import java.util.concurrent.ConcurrentHashMap;
/** Per-module setting schema registry. */
public final class SettingRegistry {
 private final Map<String,List<SettingSpec>> specs=new ConcurrentHashMap<>();
 public void register(String moduleId,List<SettingSpec> settings){if(moduleId==null||moduleId.isBlank())throw new IllegalArgumentException("Module id required");List<SettingSpec> copy=List.copyOf(settings);Set<String> ids=new HashSet<>();for(var s:copy)if(!ids.add(s.id()))throw new IllegalArgumentException("Duplicate setting id: "+s.id());specs.put(moduleId,copy);}
 public List<SettingSpec> forModule(String id){return specs.getOrDefault(id,List.of());}public Set<String> moduleIds(){return Set.copyOf(specs.keySet());}public void remove(String id){specs.remove(id);}public void clear(){specs.clear();}
}