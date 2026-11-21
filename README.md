# Clovix

A modular, client-side Minecraft Java Edition customization mod.

## Status
**Core build: successful** — Clovix Core Alpha 0.1.0-alpha.1 compiles and produces a Fabric JAR in GitHub Actions.

## Target toolchain
- Minecraft Java Edition **26.3**
- Fabric Loader **0.20.1+**
- Fabric API **0.166.0+26.3**
- Java **25**
- Gradle **9.8.0**
- Fabric Loom **1.19.4**
- Non-remapping (Mojang names)

## Implemented Core
- Module registry and lifecycle states, including re-enable support
- Managed Core modules: Settings & Storage, Theme Engine, Clovix Interface, HUD Overlay
- Native in-game Clovix screen with adaptive layout
- Remappable open-screen key (default SDL scancode C)
- Four selectable themes: Graphite, Midnight, Obsidian, Ocean
- Local JSON settings with backup and atomic save
- HUD: player coordinates and health
- HUD visibility toggle persisted in config
- Event bus and centralized edition manager

## Install (Minecraft 26.3)
1. Create an instance for Minecraft 26.3.
2. Install Fabric Loader 0.20.1 or newer compatible with 26.3.
3. Install Fabric API for 26.3.
4. Copy `clovix-0.1.0-alpha.1.jar` into the instance's `mods` folder.
5. Launch the instance.

**Android launcher compatibility:** The archived original PojavLauncher is discontinued and does not provide a reliable environment for Minecraft 26.3/Java 25. Use a maintained 26.3-compatible launcher such as Amethyst Android instead. Clovix has been built in CI but not yet runtime-tested on the tablet.

## Build
CI uses JDK 25 and Gradle 9.8.0:
```bash
gradle build
``
Output: `build/libs/clovix-0.1.0-alpha.1.jar`

## Safety and design principles
- No Fly module in any edition
- Client-side and local-first
- Cheat modules remain isolated from ordinary-tier macros/extensions
- No anticheat bypass or stealth claims
