package com.clovix.ui;
import com.clovix.Clovix;import com.clovix.core.ClovixModule;import com.clovix.core.Lifecycle;import com.google.gson.JsonPrimitive;
import net.minecraft.client.gui.GuiGraphicsExtractor;import net.minecraft.client.gui.components.Button;import net.minecraft.client.gui.screens.Screen;import net.minecraft.network.chat.Component;import java.util.List;
/** Native, touch-friendly Clovix Core control screen. */
public final class ClovixScreen extends Screen {
 private final Screen parent;private String section="Overview";
 public ClovixScreen(Screen parent){super(Component.literal("Clovix"));this.parent=parent;}
 private boolean wide(){return width>=540;}
 private int panelX(){return wide()?168:18;}
 private int panelY(){return wide()?52:178;}
 private int panelW(){return Math.max(140,width-panelX()-18);}
 private void nav(String target){section=target;init();}
 @Override protected void init(){
  int x=18,y=44;int navW=wide()?132:Math.max(72,(width-42)/4);int h=wide()?26:24;
  String[] items={"Overview","Modules","Settings","Theme"};
  for(int i=0;i<items.length;i++){final String item=items[i];int bx=wide?x:x+i*(navW+6);int by=wide?y+i*(h+6):y;addRenderableWidget(Button.builder(Component.literal(item),b->{if(item.equals("Theme")){ThemeManager.cycle(Clovix.core());init();}else nav(item);}).bounds(bx,by,navW,h).build());}
  if(wide)addRenderableWidget(Button.builder(Component.literal("Close"),b->onClose()).bounds(x,y+4*(h+6),navW,h).build());
  if(section.equals("Modules")){int py=panelY()+30;for(ClovixModule m:Clovix.core().modules().all()){int bx=panelX(),bw=Math.min(panelW(),300);boolean active=m.lifecycle()==Lifecycle.ACTIVE;addRenderableWidget(Button.builder(Component.literal((active?"[ON]  ":"[OFF]  ")+m.displayName()),b->{if(active)Clovix.core().moduleManager().disable(m.id());else Clovix.core().moduleManager().enable(m.id());init();}).bounds(bx,py,bw,26).build());py+=32;}}
  if(section.equals("Settings")){var settings=Clovix.core().config().snapshot();boolean show=!settings.has("showHud")||settings.get("showHud").getAsBoolean();addRenderableWidget(Button.builder(Component.literal("Custom HUD: "+(show?"ON":"OFF")),b->{Clovix.core().config().put("showHud",new JsonPrimitive(!show));init();}).bounds(panelX(),panelY()+28,Math.min(panelW(),300),28).build());}
  if(section.equals("Theme")){addRenderableWidget(Button.builder(Component.literal("Change theme  ·  "+ThemeManager.current().name()),b->{ThemeManager.cycle(Clovix.core());init();}).bounds(panelX(),panelY()+28,Math.min(panelW(),300),28).build());}
  if(!wide)addRenderableWidget(Button.builder(Component.literal("Close"),b->onClose()).bounds(18,height-30,Math.min(width-36,200),24).build());
 }
 @Override public void extractRenderState(GuiGraphicsExtractor g,int mx,int my,float delta){
  Theme t=ThemeManager.current();g.fill(0,0,width,height,t.background);g.fill(8,8,width-8,height-8,t.panel);g.fill(8,8,width-8,34,t.raised);super.extractRenderState(g,mx,my,delta);
  g.text(font,"CLOVIX",18,18,t.text,true);g.text(font,"CORE  "+Clovix.core().VERSION,Math.min(width-120,100),18,t.accent,true);
  int px=panelX(),py=panelY();g.fill(px-8,py-14,Math.min(width-8,px+panelW()+8),Math.min(height-8,py+230),t.raised);
  g.text(font,section,px,py-2,t.accent,true);
  if(section.equals("Overview")){g.text(font,"Runtime: ACTIVE",px,py+20,t.text,true);g.text(font,"Edition: "+Clovix.core().editions().current(),px,py+36,t.text,true);g.text(font,"Managed modules: "+Clovix.core().modules().size(),px,py+52,t.text,true);g.text(font,"Storage: local JSON + backup",px,py+68,t.text,true);g.text(font,"Themes: "+Theme.values().length,px,py+84,t.text,true);}
  else if(section.equals("Modules")){List<ClovixModule> list=Clovix.core().modules().all().stream().toList();g.text(font,"Enable or disable Core modules.",px,py+18,t.text,true);if(list.isEmpty())g.text(font,"No registered modules.",px,py+36,t.text,true);}
  else if(section.equals("Settings")){g.text(font,"Settings are saved immediately.",px,py+18,t.text,true);g.text(font,"HUD visibility is persistent.",px,py+34,t.text,true);g.text(font,"Configuration: config/clovix/core.json",px,py+50,t.text,true);}
  else if(section.equals("Theme")){g.text(font,"Current: "+ThemeManager.current(),px,py+18,t.text,true);g.text(font,"Theme selection is saved to disk.",px,py+34,t.text,true);}
 }
 @Override public void onClose(){minecraft.gui.setScreen(parent);}
}
