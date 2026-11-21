package com.clovix.ui;
import com.clovix.Clovix;
import com.clovix.core.ClovixModule;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.List;
/** First native Clovix interface; responsive centered layout with theme-aware styling. */
public final class ClovixScreen extends Screen {
 private final Screen parent;private String section="Overview";
 public ClovixScreen(Screen parent){super(Component.literal("Clovix"));this.parent=parent;}
 @Override protected void init(){
  int left=Math.max(12,width/12),top=42;int contentW=Math.min(360,width-left-12);
  addRenderableWidget(Button.builder(Component.literal("Overview"),b->section="Overview").bounds(left,top,contentW,20).build());
  addRenderableWidget(Button.builder(Component.literal("Modules"),b->section="Modules").bounds(left,top+24,contentW,20).build());
  addRenderableWidget(Button.builder(Component.literal("Settings"),b->section="Settings").bounds(left,top+48,contentW,20).build());
  addRenderableWidget(Button.builder(Component.literal("Theme: "+ThemeManager.current().name()),b->{ThemeManager.cycle(Clovix.core());init();}).bounds(left,top+72,contentW,20).build());
  addRenderableWidget(Button.builder(Component.literal("Close"),b->onClose()).bounds(left,top+96,contentW,20).build());
 }
 @Override public void extractRenderState(GuiGraphicsExtractor g,int mx,int my,float delta){
  Theme t=ThemeManager.current();g.fill(0,0,width,height,t.background);g.fill(8,8,width-8,height-8,t.panel);g.fill(8,8,width-8,34,t.raised);
  super.extractRenderState(g,mx,my,delta);
  g.text(font,"CLOVIX",18,18,t.text,true);
  g.text(font,"CORE  "+Clovix.core().VERSION,Math.min(width-120,100),18,t.accent,true);
  int x=Math.max(12,width/12),y=152;
  g.text(font,section,x,y,t.accent,true);
  if(section.equals("Overview")){
   g.text(font,"Core status: ACTIVE",x,y+18,t.text,true);g.text(font,"Edition: "+Clovix.core().editions().current(),x,y+34,t.text,true);
   g.text(font,"Modules: "+Clovix.core().modules().size(),x,y+50,t.text,true);g.text(font,"Config: local / JSON",x,y+66,t.text,true);
  }else if(section.equals("Modules")){
   List<ClovixModule> list=Clovix.core().modules().all().stream().toList();
   if(list.isEmpty())g.text(font,"Core services are initializing.",x,y+18,t.text,true);
   else for(int i=0;i<Math.min(8,list.size());i++)g.text(font,list.get(i).displayName()+"  ["+list.get(i).lifecycle()+"]",x,y+18+i*14,t.text,true);
  }else{
   g.text(font,"Theme: "+ThemeManager.current(),x,y+18,t.text,true);g.text(font,"Settings stored in config/clovix/core.json",x,y+34,t.text,true);
   g.text(font,"More controls will appear here as modules are added.",x,y+50,t.text,true);
  }
 }
 @Override public void onClose(){minecraft.gui.setScreen(parent);}
}
