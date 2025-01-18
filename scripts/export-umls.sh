#!/usr/bin/env bash

directories=(
    "docs/uml"
    "docs/sequence-diagrams"
)

for dir in "${directories[@]}"; do
    echo "Processing directory: $dir"
    if [[ -d "$dir" ]]; then
        # Find all .plantuml files in the directory
        find "$dir" -type f -name "*.plantuml" | while read -r file; do
            echo "Processing file: $file"
            plantuml "$file"
        done
    else
        echo "Directory not found: $dir"
    fi
done
