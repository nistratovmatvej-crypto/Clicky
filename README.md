# Clovix

A modular Minecraft Java Edition client customization mod.

> Work in progress. Clovix is local-first, client-side, and uses a shared Core with edition-based feature access.

## Target toolchain
- Minecraft Java Edition **26.3**
- Fabric Loader **0.20.1**
- Fabric API **0.166.2+26.3**
- Java **25**
- Gradle **9.6.0**
- Fabric Loom **1.17**, non-remapping plugin

Minecraft 26.1+ is unobfuscated; Yarn mappings are no longer used. Clovix uses the non-remapping Loom plugin and Mojang's native class names.

## Principles
- Modular lifecycle-managed services
- Central edition permission checks
- Local configuration, validation and backups
- Compatibility and diagnostics as core systems
- No Fly module in any edition
- Cheat modules isolated from regular-tier macros and extensions

## Build
GitHub Actions builds using Java 25 and Gradle 9.6. The wrapper is generated in CI during bootstrap; a wrapper binary will be committed when a binary can be safely sourced.
