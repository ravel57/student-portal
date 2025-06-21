#!/bin/zsh

orig_path="$PWD"

cd ../../WebstormProjects/student-portal-front || exit 1

npm install
vite build

if [[ -d "./dist" ]]; then
    cp -r ./dist/* "$orig_path/src/main/resources/static"
    cd "$orig_path"
else
    echo "Coping error" >&2
    cd "$orig_path"
    exit 1
fi