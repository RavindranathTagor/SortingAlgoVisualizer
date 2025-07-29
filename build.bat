@echo off
echo Compiling Java files...
if not exist "out" mkdir out
javac -d out -cp src src/visualizer/*.java src/algorithms/*.java

echo Creating JAR file...
cd out
jar cfm ../SortingVisualizer.jar ../MANIFEST.MF .
cd ..

echo Build complete! Run with: java -jar SortingVisualizer.jar
pause
