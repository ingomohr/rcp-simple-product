# Simple Product

A minimal Eclipse RCP application built with Maven Tycho against the
Eclipse 2026-09 simultaneous release, producing a native macOS launcher
(`SimpleProduct.app`) with a Java 25 (LTS) JRE bundled in.

## Modules

| Module                                | Type                 | Purpose                                                            |
|----------------------------------------|----------------------|---------------------------------------------------------------------|
| `org.example.simpleproduct.app`        | plug-in (bundle)     | The RCP application code (workbench, view, icons).                  |
| `org.example.simpleproduct.feature`    | feature              | Wraps the plug-in for inclusion in the update site / product.       |
| `org.example.simpleproduct.updatesite` | p2 repository        | A p2 update site publishing the feature.                            |
| `org.example.simpleproduct.product`    | product              | Builds the native launcher(s), including a bundled JRE 25.          |

## Prerequisites

- Maven 3.9+
- A JDK to *run* the build (JDK 17+ is fine; Tycho itself doesn't need JDK 25 -
  JDK 25 is fetched automatically as the *target* runtime for the product).
- Internet access to `download.eclipse.org` (Eclipse release + JustJ JRE p2
  repositories) and Maven Central.

## Building

```bash
mvn clean verify
```

This builds all four modules. Tycho resolves the target platform against the
Eclipse 2026-09 release repository and fetches a Java 25 (LTS) JRE from the
JustJ project (`https://download.eclipse.org/justj/jres/25/...`) to embed in
the product.

## Where to find the results

- **Update site (p2 repository):**
  `org.example.simpleproduct.updatesite/target/repository`
- **Product launchers (materialized, unpacked):**
  `org.example.simpleproduct.product/target/products/org.example.simpleproduct.product/macosx/cocoa/{aarch64,x86_64}/SimpleProduct.app`
- **Product launchers (archived, ready to distribute):**
  `org.example.simpleproduct.product/target/products/org.example.simpleproduct.product-macosx.cocoa.aarch64.tar.gz`
  `org.example.simpleproduct.product/target/products/org.example.simpleproduct.product-macosx.cocoa.x86_64.tar.gz`

## Running the app

```bash
open org.example.simpleproduct.product/target/products/org.example.simpleproduct.product/macosx/cocoa/aarch64/SimpleProduct.app
```

(use the `x86_64` variant on Intel Macs)

The app ships with its own Java 25 runtime under
`SimpleProduct.app/Contents/Eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.stripped.macosx.<arch>_*/jre`
so users do **not** need a JDK/JRE installed separately.

## Notes / customization points

- **Icon**: a red "R" on a light grey rounded-square background
  (`org.example.simpleproduct.app/icons/*.png` for in-app window icons,
  `org.example.simpleproduct.product/icons/SimpleProduct.icns` for the
  macOS app icon). Regenerate with any icon tool if you want a different
  design — just keep the same file names or update `simpleproduct.product`
  and `plugin.xml` accordingly.
- **Bundled JRE variant**: the product currently bundles
  `org.eclipse.justj.openjdk.hotspot.jre.full.stripped` (full JDK module set,
  debug info stripped). Swap for `...jre.minimal.stripped` in
  `simpleproduct.product` for a smaller footprint if the app doesn't need the
  complete JDK module set.
- **Target platform**: the Eclipse release train and JustJ JRE repository
  URLs/versions are centralized as properties in the root `pom.xml`.
- **Architectures**: both `aarch64` (Apple Silicon) and `x86_64` (Intel) macOS
  product variants are built by default; adjust the `<environments>` in the
  root `pom.xml` if you only need one.
