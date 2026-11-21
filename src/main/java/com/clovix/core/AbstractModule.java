package com.clovix.core;
import java.util.concurrent.atomic.AtomicReference;
/** Lifecycle-safe base class for Core modules. */
public abstract class AbstractModule implements ClovixModule {
 private final AtomicReference<Lifecycle> state=new AtomicReference<>(Lifecycle.NEW);
 @Override public final Lifecycle lifecycle(){return state.get();}
 public final boolean start(CoreManager core){if(!state.compareAndSet(Lifecycle.NEW,Lifecycle.INITIALIZING))return false;try{initialize(core);state.set(Lifecycle.ACTIVE);return true;}catch(Exception ex){state.set(Lifecycle.FAILED);ClovixLog.error("Module initialization failed: "+id(),ex);return false;}}
 public final void stop(){Lifecycle old=state.get();if(old!=Lifecycle.ACTIVE&&old!=Lifecycle.FAILED)return;try{shutdown();state.set(Lifecycle.DISABLED);}catch(Exception ex){state.set(Lifecycle.FAILED);ClovixLog.error("Module shutdown failed: "+id(),ex);}}
}
