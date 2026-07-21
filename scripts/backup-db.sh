#!/usr/bin/env sh
set -eu

: "${DATABASE_URL:?DATABASE_URL is required}"
: "${DATABASE_USERNAME:?DATABASE_USERNAME is required}"
: "${DATABASE_PASSWORD:?DATABASE_PASSWORD is required}"

BACKUP_DIR="${BACKUP_DIR:-./backups}"
TIMESTAMP="$(date -u +%Y%m%dT%H%M%SZ)"
mkdir -p "$BACKUP_DIR"

PGPASSWORD="$DATABASE_PASSWORD" pg_dump \
  --format=custom \
  --no-owner \
  --username="$DATABASE_USERNAME" \
  --dbname="$DATABASE_URL" \
  --file="$BACKUP_DIR/lphie-$TIMESTAMP.dump"

echo "Backup created: $BACKUP_DIR/lphie-$TIMESTAMP.dump"
