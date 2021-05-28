#!/usr/bin/env bash

# @@set=source.url url=https://stackoverflow.com/questions/59895/how-can-i-get-the-source-directory-of-a-bash-script-from-within-the-script-itsel
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

_RBIN_P="${SCRIPT_DIR}"

export PATH="${PATH}:${_RBIN_P}"
