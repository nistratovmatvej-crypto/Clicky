package com.clovix.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
/** Minimal typed event bus. Subscribers are isolated from one another. */
public final class EventBus {
    private final Map<Class<?>, CopyOnWriteArrayList<Consumer<?>>> subscribers = new ConcurrentHashMap<>();
    public <T> AutoCloseable subscribe(Class<T> type, Consumer<T> handler) {
        Objects.requireNonNull(type); Objects.requireNonNull(handler);
        var list = subscribers.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>());
        list.add(handler);
        return () -> list.remove(handler);
    }
    public <T> void post(T event) {
        if (event == null) return;
        var list = subscribers.get(event.getClass());
        if (list == null) return;
        for (Consumer<?> raw : list) {
            @SuppressWarnings("unchecked") Consumer<T> handler = (Consumer<T>) raw;
            try { handler.accept(event); } catch (RuntimeException ex) { ClovixLog.error("Event handler failed for " + event.getClass().getSimpleName(), ex); }
        }
    }
    public int subscriberCount(Class<?> type) { return subscribers.getOrDefault(type, new CopyOnWriteArrayList<>()).size(); }
}
