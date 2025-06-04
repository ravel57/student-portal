$path = Get-Location
Set-Location ../../WebstormProjects/student-portal-front

yarn install
yarn build

if (Test-Path "./dist/") {
    Copy-Item -Path "./dist/*" -Destination "$path/src/main/resources/static" -Recurse -Force
    Set-Location $path
} else {
    Write-Error "Coping error"
    Set-Location $path
    exit 1
}