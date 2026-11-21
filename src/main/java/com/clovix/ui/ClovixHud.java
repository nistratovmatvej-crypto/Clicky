package com.clovix.ui;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
/** HUD overlay displaying real local player/world data only. */
public final class ClovixHud {
 private ClovixHud(){}
 public static void register(){
  HudElementRegistry.addLast(Identifier.fromNamespaceAndPath("clovix","core_info"),(graphics,delta)->{
   Minecraft mc=Minecraft.getInstance();if(mc.player==null||mc.level==null||mc.gui.hud.isHidden())return;
   Theme t=ThemeManager.current();int x=8,y=8;var player=mc.player;var pos=player.blockPosition();
   graphics.fill(x-4,y-4,x+152,y+42,t.panel);
   graphics.text(mc.font,"CLOVIX",x,y,t.accent,true);
   graphics.text(mc.font,String.format("XYZ  %d  %d  %d",pos.getX(),pos.getY(),pos.getZ()),x,y+12,t.text,true);
   graphics.text(mc.font,"Health  "+String.format("%.1f",player.getHealth()),x,y+24,t.text,true);
  });
 }
}
