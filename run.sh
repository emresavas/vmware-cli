#!/usr/bin/env bash
ant -q jar && java -cp "dist/vmware-cli.jar:lib/*" com.emresavas.vmwarecli.Main "$@"
