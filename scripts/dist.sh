#!/usr/bin/env bash


DIST_DIR="dist"

rm -rf $DIST_DIR && echo "[INFO] Removed $DIST_DIR/ directory"
mkdir -p $DIST_DIR && echo "[INFO] Created $DIST_DIR/"

include=(
    "src"
    "pom.xml"
    "LICENSE"
    "README.md"
    "help"
    "assets"
    "docs/sequence-diagrams"
    "docs/uml"
    "docs/code-metrics"
)

copy() {
    dest_dir="$DIST_DIR/$(dirname "$1")"
    mkdir -p "$dest_dir"
    cp -r "$1" "$dest_dir"
}

for inc in "${include[@]}"; do
    copy "$inc"
    echo "[INFO] Copied $inc"
done



