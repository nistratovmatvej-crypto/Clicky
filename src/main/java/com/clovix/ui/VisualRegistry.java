package com.clovix.ui;
import java.util.*;import java.util.concurrent.ConcurrentHashMap;
/** Extensible registry for visuals, with availability checks and categorized access. */
public final class VisualRegistry {
 private final Map<String,VisualFeature> features=new ConcurrentHashMap<>();
 public void register(VisualFeature f){Objects.requireNonNull(f);if(f.id()==null||f.id().isBlank())throw new IllegalArgumentException("Feature id required");if(features.putIfAbsent(f.id(),f)!=null)throw new IllegalStateException("Duplicate visual feature: "+f.id());}
 public Optional<VisualFeature> get(String id){return Optional.ofNullable(features.get(id));}
 public List<VisualFeature> all(){return features.values().stream().sorted(Comparator.comparing(VisualFeature::category).thenComparing(VisualFeature::name)).toList();}
 public List<VisualFeature> category(String c){return all().stream().filter(f->f.category().equalsIgnoreCase(c)).toList();}
 public boolean setEnabled(String id,boolean enabled){return get(id).filter(VisualFeature::isAvailable).map(f->{f.setEnabled(enabled);return true;}).orElse(false);}public int size(){return features.size();}
}