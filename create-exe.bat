@echo off
echo Creating Windows executable...
echo This requires Java 14+ with jpackage tool

jpackage --input . --name "SortingAlgoVisualizer" --main-jar SortingVisualizer.jar --main-class visualizer.SortingVisualizer --type exe --win-shortcut --win-menu

echo Executable created! Check the current directory for SortingAlgoVisualizer.exe
pause
