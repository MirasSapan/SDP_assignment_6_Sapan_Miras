#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build
javac --release 17 -encoding UTF-8 -d build src/station/*.java tests/station/*.java
java -cp build station.VisitorTests
