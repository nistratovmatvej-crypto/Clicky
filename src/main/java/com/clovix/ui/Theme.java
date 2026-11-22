package com.clovix.ui;
public enum Theme {
 LIGHT(UiPalette.LIGHT_PRIMARY,UiPalette.LIGHT_PANEL,0xFFE9ECF4,UiPalette.ACCENT,UiPalette.LIGHT_TEXT),
 GRAPHITE(UiPalette.DARK_PRIMARY,UiPalette.DARK_PANEL,0xFF303030,UiPalette.ACCENT,UiPalette.DARK_TEXT),
 MIDNIGHT(0xFF0D1422,0xFF172235,0xFF22334B,UiPalette.ACCENT,0xFFE9F4FF),
 OBSIDIAN(0xFF101010,0xFF202020,0xFF303030,UiPalette.ACCENT,0xFFF4F4F4),
 OCEAN(0xFF0C1720,0xFF122632,0xFF1B3745,0xFF4EC9C0,0xFFE4F6F4);
 public final int background,panel,raised,accent,text;
 Theme(int background,int panel,int raised,int accent,int text){this.background=background;this.panel=panel;this.raised=raised;this.accent=accent;this.text=text;}
 public Theme next(){Theme[] v=values();return v[(ordinal()+1)%v.length];}
}