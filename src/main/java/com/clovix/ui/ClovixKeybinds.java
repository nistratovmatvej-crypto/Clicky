package com.clovix.ui;
import com.clovix.Clovix;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.client.input.InputConstants;
/** Open Clovix from a remappable keybind. */
public final class ClovixKeybinds {
 private ClovixKeybinds(){}
 public static void register(){
  KeyMapping.Category category=KeyMapping.Category.register(Identifier.fromNamespaceAndPath("clovix","main"));
  KeyMapping open=KeyMappingHelper.registerKeyMapping(new KeyMapping("key.clovix.open",InputConstants.KEY_C,category));
  ClientTickEvents.END_CLIENT_TICK.register(client->{while(open.consumeClick())client.gui.setScreen(new ClovixScreen(client.gui.screen()));});
 }
}
