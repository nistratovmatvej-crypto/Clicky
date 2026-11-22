package com.clovix.ui;
import java.util.*;
/** Component catalog, allowing module settings to be rendered from metadata. */
public final class UiComponentRegistry {
 public record Descriptor(ComponentType type,String label,boolean interactive,String description){}
 private final EnumMap<ComponentType,Descriptor> components=new EnumMap<>(ComponentType.class);
 public UiComponentRegistry(){for(var t:ComponentType.values())register(t,pretty(t),t!=ComponentType.SECTION&&t!=ComponentType.SUBCATEGORY&&t!=ComponentType.TOOLTIP,"Reusable "+t.name().toLowerCase()+" component");}
 private static String pretty(ComponentType t){String s=t.name().toLowerCase().replace('_',' ');return Character.toUpperCase(s.charAt(0))+s.substring(1);}
 public void register(ComponentType type,String label,boolean interactive,String description){components.put(type,new Descriptor(type,label,interactive,description));}
 public Optional<Descriptor> get(ComponentType t){return Optional.ofNullable(components.get(t));}public Collection<Descriptor> all(){return List.copyOf(components.values());}
}