package com.clovix.config;
import com.google.gson.JsonObject;import com.google.gson.JsonPrimitive;
/** Defaults for core-level preferences. */
public final class CoreSettings {
 public static final String DEFAULT_THEME="graphite",DEFAULT_LANGUAGE="system";public static final double DEFAULT_UI_SCALE=1.0;
 private CoreSettings(){}
 public static JsonObject defaults(){JsonObject o=new JsonObject();o.add("schemaVersion",new JsonPrimitive(1));o.add("theme",new JsonPrimitive(DEFAULT_THEME));o.add("language",new JsonPrimitive(DEFAULT_LANGUAGE));o.add("uiScale",new JsonPrimitive(DEFAULT_UI_SCALE));o.add("animations",new JsonPrimitive(true));o.add("touchMode",new JsonPrimitive("auto"));o.add("edition",new JsonPrimitive("SHALLOW"));o.add("showHud",new JsonPrimitive(true));return o;}
}
