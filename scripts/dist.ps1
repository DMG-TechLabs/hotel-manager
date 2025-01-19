#!/usr/bin/env pwsh

$DistDir = "dist"

# Create the distribution directory if it doesn't already exist
if (!(Test-Path -Path $DistDir)) {
    New-Item -ItemType Directory -Path $DistDir | Out-Null
}

# List of items to include in the distribution
$Include = @(
    "src",
    "pom.xml",
    "LICENSE",
    "README.md",
    "help",
    "assets",
    "docs/sequence-diagrams",
    "docs/uml",
    "docs/code-metrics"
)

# Function to copy items to the distribution directory
function Copy-ItemToDist {
    param (
        [string]$Item
    )

    # Determine the destination directory
    $DestinationDir = Join-Path $DistDir (Split-Path -Path $Item -Parent)

    # Create the destination directory if it doesn't exist
    if (!(Test-Path -Path $DestinationDir)) {
        New-Item -ItemType Directory -Path $DestinationDir | Out-Null
    }

    # Copy the file or directory to the destination
    Copy-Item -Recurse -Path $Item -Destination $DestinationDir
}

# Iterate over the include array and copy each item
foreach ($Item in $Include) {
    Copy-ItemToDist -Item $Item
}


