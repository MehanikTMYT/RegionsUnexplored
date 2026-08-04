#!/usr/bin/env bash
set -euo pipefail

cd /root/home/forks/RegionsUnexplored

SOURCE="build/libs/regions-unexplored-0.6.1-neoforge-26.1.2.jar"
DESTINATION="/root/home/exports/regions-unexplored-0.6.1-26.1.2-neoforge-only-mdg.jar"

test -f "$SOURCE" || {
    echo "Не найден JAR: $SOURCE"
    exit 1
}

printf '\n[VALIDATING SOURCE]\n'

jar tf "$SOURCE" | grep -Fx 'META-INF/neoforge.mods.toml'
jar tf "$SOURCE" | grep -Fx 'META-INF/accesstransformer.cfg'
jar tf "$SOURCE" | grep -Fx 'net/regions_unexplored/RegionsUnexploredNeo.class'
jar tf "$SOURCE" | grep -Fx 'META-INF/jarjar/json5-java-3.0.0.jar'
jar tf "$SOURCE" | grep -Fx 'META-INF/jarjar/metadata.json'

printf '\n[REMOVED COMPONENTS]\n'

if jar tf "$SOURCE" | grep -Ei 'fabric\.mod\.json|RegionsUnexploredFabric|wikiful|cloche|net/msrandom|Actual\.class$|PrismaglassBlockExtension|mezz/jei|jemi'
then
    echo "Обнаружены удалённые компоненты"
    exit 1
else
    echo "Fabric, Wikiful, Cloche, msrandom и JEI/JEMI отсутствуют"
fi

mkdir -p "$(dirname "$DESTINATION")"
install -m 0644 "$SOURCE" "$DESTINATION"

cmp --silent "$SOURCE" "$DESTINATION" || {
    echo "Экспортированный JAR отличается от исходного"
    exit 1
}

printf '\n[EXPORTED]\n'
ls -lh "$DESTINATION"
stat --printf='size=%s bytes\n' "$DESTINATION"

printf '\n[SHA256]\n'
sha256sum "$DESTINATION"

printf '\n[EXPORT PATH]\n%s\n' "$DESTINATION"
