#!/bin/bash
# Restore may exit with non-blocking errors so we shouldn't stop the script
# since in many cases the dump will be restored correctly
pg_restore -d fooddb /tmp/db.dump || true
