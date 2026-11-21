package com.clovix;
import com.clovix.core.CoreManager;import net.fabricmc.api.ClientModInitializer;import org.slf4j.Logger;import org.slf4j.LoggerFactory;
public final class Clovix implements ClientModInitializer {public static final String MOD_ID="clovix";public static final Logger LOGGER=LoggerFactory.getLogger("Clovix");private static CoreManager core;@Override public void onInitializeClient(){core=new CoreManager();core.initialize();LOGGER.info("Clovix Core {} initialized",CoreManager.VERSION);}public static CoreManager core(){return core;}}
