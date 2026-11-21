package com.clovix.ui;
public enum Theme {
 GRAPHITE(0xFF11151D,0xFF1C2430,0xFF273345,0xFF6CA5F5,0xFFEAF0FA),
 MIDNIGHT(0xFF080D18,0xFF111B2B,0xFF1B2B42,0xFF67B8FF,0xFFE9F4FF),
 OBSIDIAN(0xFF101010,0xFF202020,0xFF303030,0xFFB8B8B8,0xFFF4F4F4),
 OCEAN(0xFF0C1720,0xFF122632,0xFF1B3745,0xFF4EC9C0,0xFFE4F6F4);
 public final int background,panel,raised,accent,text;
 Theme(int background,int panel,int raised,int accent,int text){this.background=background;this.panel=panel;this.raised=raised;this.accent=accent;this.text=text;}
 public Theme next(){Theme[] v=values();return v[(ordinal()+1)%v.length];}
}
