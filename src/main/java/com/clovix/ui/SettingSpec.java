package com.clovix.ui;
import java.util.List; import java.util.Objects;
/** Immutable schema for a module setting, independent from widget implementation. */
public record SettingSpec(String id,String label,String description,ComponentType type,Object defaultValue,double min,double max,double step,List<String> options,String dependencyId,Object dependencyValue) {
 public SettingSpec { if(id==null||id.isBlank())throw new IllegalArgumentException("Setting id required");label=Objects.requireNonNullElse(label,id);description=Objects.requireNonNullElse(description,"");type=Objects.requireNonNull(type);options=options==null?List.of():List.copyOf(options);if(type==ComponentType.SLIDER||type==ComponentType.NUMBER_INPUT){if(!Double.isFinite(min)||!Double.isFinite(max)||min>max||!Double.isFinite(step)||step<=0)throw new IllegalArgumentException("Invalid numeric bounds: "+id);}if((type==ComponentType.DROPDOWN||type==ComponentType.MULTI_SELECT)&&options.isEmpty())throw new IllegalArgumentException("Options required: "+id);}
 public static SettingSpec toggle(String id,String label,boolean value){return new SettingSpec(id,label,"",ComponentType.TOGGLE,value,0,1,1,List.of(),null,null);}
 public static SettingSpec slider(String id,String label,double value,double min,double max,double step){return new SettingSpec(id,label,"",ComponentType.SLIDER,value,min,max,step,List.of(),null,null);}
 public static SettingSpec dropdown(String id,String label,String value,List<String> options){return new SettingSpec(id,label,"",ComponentType.DROPDOWN,value,0,0,0,options,null,null);}
}