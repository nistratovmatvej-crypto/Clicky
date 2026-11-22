package com.clovix.ui;
/** Normalized HUD element metadata; position is relative to screen dimensions. */
public record HudElementSpec(String id,String label,boolean enabled,float x,float y,float scale,int color,float alpha){
 public HudElementSpec{if(id==null||id.isBlank())throw new IllegalArgumentException("HUD id required");if(!Float.isFinite(x)||!Float.isFinite(y)||!Float.isFinite(scale)||!Float.isFinite(alpha))throw new IllegalArgumentException("HUD values must be finite");x=clamp(x,0,1);y=clamp(y,0,1);scale=clamp(scale,.25f,4f);alpha=clamp(alpha,0,1);}
 private static float clamp(float v,float a,float b){return Math.max(a,Math.min(b,v));}
 public HudElementSpec move(float nx,float ny){return new HudElementSpec(id,label,enabled,nx,ny,scale,color,alpha);}public HudElementSpec resize(float s){return new HudElementSpec(id,label,enabled,x,y,s,color,alpha);}public HudElementSpec withEnabled(boolean v){return new HudElementSpec(id,label,v,x,y,scale,color,alpha);}public HudElementSpec withColor(int c,float a){return new HudElementSpec(id,label,enabled,x,y,scale,c,a);}
}