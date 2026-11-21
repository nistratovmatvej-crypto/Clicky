package com.clovix.ui;
import com.clovix.Clovix;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
/** HUD overlay displaying real local player/world data only. */
public final class ClovixHud {
 private ClovixHud(){}
 public static void register(){
  HudElementRegistry.addLast(Identifier.fromNamespaceAndPath("clovix","core_info"),(graphics,delta)->{
   Minecraft mc=Minecraft.getInstance();if(mc.player==null||mc.level==null||mc.gui.hud.isHidden())return;
   Theme t=ThemeManager.current();int x=8,y=8;
   graphics.fill(x-4,y-4,x+150,y+42,t.panel);
   var p=mc.player;var pos=p.blockPosition();
   graphics.text(mc.font,"CLOVIX",x,y,t.accent,true);
   graphics.text(mc.font,String.format("XYZ  %d  %d  %d",pos.getX(),pos.getY(),pos.getZ()),x,y+12,t.text,true);
   graphics.text(mc.font,"Biome  "+mc.level.getBiome(pos).getRegisteredName(),x,y+24,t.text,true);
  });
 }
}
