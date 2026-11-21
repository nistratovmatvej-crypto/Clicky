package com.clovix.core;
/** Edition checks centralize entitlement policy; modules must never implement tier checks independently. */
public final class EditionManager {
    private volatile Edition edition = Edition.SHALLOW;
    public Edition current() { return edition; }
    public void setEdition(Edition edition) { if (edition == null) throw new IllegalArgumentException("Edition required"); this.edition = edition; }
    public boolean permits(Edition minimum) { return rank(edition) >= rank(minimum); }
    private static int rank(Edition e) { return switch (e) { case SHALLOW -> 0; case DEEP -> 1; case ABYSS -> 2; case CHEAT -> 3; }; }
}
