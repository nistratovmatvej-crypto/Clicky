package com.clovix.core;

/** Base contract for independently managed Clovix modules. */
public interface ClovixModule {
    String id();
    String displayName();
    default void initialize(CoreManager core) throws Exception {}
    default void shutdown() throws Exception {}
    default Lifecycle lifecycle() { return Lifecycle.NEW; }
}
