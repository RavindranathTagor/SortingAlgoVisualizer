# 🎯 Sorting Algorithm Visualizer

A Java Swing application that provides an interactive visualization of various sorting algorithms. Watch algorithms like Bubble Sort, Quick Sort, and Merge Sort in action with real-time visual feedback!

## ✨ Features

- **5 Sorting Algorithms**: Bubble Sort, Selection Sort, Insertion Sort, Merge Sort, Quick Sort
- **Interactive Controls**: Generate random arrays, input custom data, control speed
- **Step-by-Step Mode**: Execute algorithms one step at a time
- **Visual Feedback**: Real-time highlighting of comparisons and swaps
- **Responsive UI**: Clean, modern interface with dark theme

## 🚀 Quick Start

### Option 1: Run JAR File (Recommended)
1. Download `SortingVisualizer.jar`
2. Ensure Java 8+ is installed
3. Run: `java -jar SortingVisualizer.jar`

### Option 2: Build from Source
```bash
# Clone and navigate to project
cd SortingVisualizer

# Build (Windows)
.\build.bat

# Build (Unix/Linux/macOS)
javac -d out -cp src src/visualizer/*.java src/algorithms/*.java
cd out && jar cfm ../SortingVisualizer.jar ../MANIFEST.MF . && cd ..
```

## 🎮 How to Use

1. **Generate Data**: Click "Generate Random" or enter custom numbers
2. **Select Algorithm**: Choose from the dropdown menu
3. **Adjust Speed**: Use the slider to control visualization speed
4. **Start Sorting**: Click "Sort" to begin visualization
5. **Step Mode**: Toggle for manual step-by-step execution

## 🛠️ Technical Details

- **Language**: Java
- **GUI Framework**: Swing
- **Architecture**: Clean separation of algorithms and visualization
- **Requirements**: Java 8+

## 📦 Project Structure

```
src/
├── algorithms/           # Sorting algorithm implementations
│   ├── SortAlgorithm.java    # Interface
│   ├── BubbleSort.java
│   ├── SelectionSort.java
│   ├── InsertionSort.java
│   ├── MergeSort.java
│   └── QuickSort.java
└── visualizer/          # GUI components
    ├── SortingVisualizer.java # Main class
    └── SortingPanel.java      # UI panel
```

## 🌟 Algorithms Included

| Algorithm | Time Complexity | Space Complexity | Stable |
|-----------|----------------|------------------|--------|
| Bubble Sort | O(n²) | O(1) | ✅ |
| Selection Sort | O(n²) | O(1) | ❌ |
| Insertion Sort | O(n²) | O(1) | ✅ |
| Merge Sort | O(n log n) | O(n) | ✅ |
| Quick Sort | O(n log n) avg | O(log n) | ❌ |

## 🚀 Deployment Options

See [DEPLOYMENT.md](DEPLOYMENT.md) for detailed deployment instructions including:
- JAR distribution
- Windows executable creation
- Web deployment options
- Package manager distribution

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Add new algorithms or improve UI
4. Submit a pull request

## 📄 License

This project is open source and available under the MIT License.
