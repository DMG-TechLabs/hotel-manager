#!/usr/bin/env bash

UML_PATH="./docs/uml/"

files=(
    "models"
    "gui"
)

export_uml() {
    plantuml "$UML_PATH/$1.plantuml"
}

for file in "${files[@]}"; do
    export_uml "$file"
done
