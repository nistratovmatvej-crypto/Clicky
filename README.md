<div align="center">  <h1>Clovix</h1>
  <p>A modular Minecraft Java Edition client customization mod.</p>
</div>

> Work in progress. Clovix is designed as a local-first client-side mod with one shared Core and edition-based feature access.

## Current target
- Minecraft Java Edition 26.3
- Fabric Loader / Fabric API
- Java 25
- Gradle + Fabric Loom

## Core principles
- Modular services and lifecycle management
- Local configuration, validation and backups
- Edition permissions centralized in Core
- Responsive native in-game UI (planned)
- Compatibility and diagnostics as first-class systems
- No Fly module in any edition
- Cheat-only modules are isolated and unavailable to ordinary-tier macro/extensions

## Build
CI uses Java 25 and Gradle 9.6.0. The Gradle wrapper is generated in CI at this bootstrap stage. A reproducible wrapper binary will be committed once a binary source is available.
