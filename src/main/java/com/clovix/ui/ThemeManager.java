package com.clovix.ui;
import com.clovix.core.CoreManager;
import com.google.gson.JsonPrimitive;
public final class ThemeManager {
 private static Theme current=Theme.GRAPHITE;
 private ThemeManager(){}
 public static Theme current(){return current;}
 public static void load(CoreManager core){if(core==null||core.config()==null)return;try{current=Theme.valueOf(core.config().snapshot().get("theme").getAsString().toUpperCase());}catch(Exception ignored){current=Theme.GRAPHITE;}}
 public static void cycle(CoreManager core){current=current.next();if(core!=null&&core.config()!=null)core.config().put("theme",new JsonPrimitive(current.name().toLowerCase()));}
 public static void set(Theme theme,CoreManager core){current=theme;if(core!=null&&core.config()!=null)core.config().put("theme",new JsonPrimitive(theme.name().toLowerCase()));}
}
