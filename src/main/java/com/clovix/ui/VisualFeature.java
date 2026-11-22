package com.clovix.ui;
/** Contract for registered visual features; rendering remains owned by each feature. */
public interface VisualFeature {String id();String name();String category();default String description(){return "";}default boolean enabled(){return false;}default void setEnabled(boolean enabled){}default boolean requiresWorld(){return false;}default boolean isAvailable(){return true;}}