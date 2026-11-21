package com.clovix.ui;
import com.clovix.core.*;
import java.util.List;
/** Core module bootstrap and status access. */
public final class ModuleManager {
 private final CoreManager core;
 public ModuleManager(CoreManager core){this.core=core;}
 public List<ClovixModule> modules(){return core.modules().all().stream().toList();}
 public boolean register(ClovixModule module){try{core.modules().register(module);if(module instanceof AbstractModule a)return a.start(core);module.initialize(core);return true;}catch(Exception e){ClovixLog.error("Could not register module "+module.id(),e);return false;}}
 public void stopAll(){var list=modules();for(int i=list.size()-1;i>=0;i--)if(list.get(i) instanceof AbstractModule a)a.stop();}
}
