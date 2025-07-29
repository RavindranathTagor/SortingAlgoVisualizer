@echo off
echo Creating Windows installer (MSI)...
echo This requires Java 14+ with jpackage tool

jpackage --input . --name "SortingAlgoVisualizer" --main-jar SortingVisualizer.jar --main-class visualizer.SortingVisualizer --type msi --win-shortcut --win-menu

echo Installer created! Check the current directory for SortingAlgoVisualizer.msi
pause
