package com.clovix.ui;
import com.clovix.Clovix;import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;import net.minecraft.client.KeyMapping;import net.minecraft.resources.Identifier;import net.minecraft.client.input.InputConstants;import java.util.concurrent.atomic.AtomicBoolean;
/** Open Clovix from a remappable keybind; registration is idempotent. */
public final class ClovixKeybinds {
 private static final AtomicBoolean REGISTERED=new AtomicBoolean(false);private ClovixKeybinds(){}
 public static void register(){if(!REGISTERED.compareAndSet(false,true))return;KeyMapping.Category category=KeyMapping.Category.register(Identifier.fromNamespaceAndPath("clovix","main"));KeyMapping open=KeyMappingHelper.registerKeyMapping(new KeyMapping("key.clovix.open",InputConstants.KEY_C,category));ClientTickEvents.END_CLIENT_TICK.register(client->{while(open.consumeClick())if(Clovix.core().modules().get("ui").map(m->m.lifecycle()==com.clovix.core.Lifecycle.ACTIVE).orElse(false))client.gui.setScreen(new ClovixScreen(client.gui.screen()));});}
}
