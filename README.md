# Clovix

A modular, client-side Minecraft Java Edition customization mod.

## Current development
**Alpha 0.2.0-alpha.1 — UI & Customization Core foundations**. This is an active development build; compile/runtime verification for this commit is pending CI.

## Toolchain
- Minecraft Java Edition 26.3
- Fabric Loader 0.20.1+
- Fabric API 0.166.0+26.3
- Java 25
- Gradle 9.8.0
- Fabric Loom 1.19.4
- Non-remapping (Mojang names)

## Core systems
- Module registry and lifecycle
- Event bus, edition manager, configuration store
- UI component catalog: toggles, sliders, dropdowns, multi-select, color picker, keybind, text/number inputs, checkbox, button, section, search and preset
- Module setting schema and per-module settings registry
- Visual feature contract and categorized registry
- HUD layout model with normalized position, scale, color/alpha and enabled state
- Light/dark design palette: light primary #FFFFFF, accent #5772FF, dark primary #1A1A1A
- Theme options including Light and Graphite

## Install (Minecraft 26.3)
1. Create an instance for Minecraft 26.3.
2. Install a Fabric Loader version compatible with 26.3.
3. Install Fabric API for 26.3.
4. Copy the built Clovix JAR into the instance's mods folder.
5. Launch the instance.

## Verification
CI status for Alpha 0.2.0-alpha.1 has not yet been confirmed. The component/settings/visual/HUD registries are architectural foundations; a fully rendered Module Manager, live settings widgets, drag-and-drop HUD Studio, and concrete Visuals render features remain development work.

## Safety
- No Fly module in any edition.
- Client-side and local-first.
- Cheat modules remain isolated from ordinary-tier macros/extensions.
- No anticheat bypass or stealth claims.
