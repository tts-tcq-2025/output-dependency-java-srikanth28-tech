import java.util.ArrayList;
import java.util.List;

// Represents a color pair with its number
class ColorPair {
    int pairNumber;
    String majorColor;
    String minorColor;

    ColorPair(int pairNumber, String majorColor, String minorColor) {
        this.pairNumber = pairNumber;
        this.majorColor = majorColor;
        this.minorColor = minorColor;
    }
}

// Functional interface for output abstraction
interface OutputFunction {
    void print(String line);
}

public class ColorMap {

    public static List<ColorPair> generateColorMap() {
        String[] majorColors = {"White", "Red", "Black", "Yellow", "Violet"};
        String[] minorColors = {"Blue", "Orange", "Green", "Brown", "Slate"};
        List<ColorPair> colorMap = new ArrayList<>();

        for (int i = 0; i < majorColors.length; i++) {
            for (int j = 0; j < minorColors.length; j++) {
                int pairNumber = i * minorColors.length + j;
                colorMap.add(new ColorPair(pairNumber, majorColors[i], minorColors[j]));
            }
        }
        return colorMap;
    }

    public static String formatColorMapEntry(ColorPair pair) {
        return String.format("%d | %-6s | %s", pair.pairNumber, pair.majorColor, pair.minorColor);
    }

    public static int printColorMap(OutputFunction outputFunc) {
        List<ColorPair> colorMap = generateColorMap();
        for (ColorPair pair : colorMap) {
            String line = formatColorMapEntry(pair);
            outputFunc.print(line);
        }
        return colorMap.size();
    }

    public static void printOnConsole(String lineItem) {
        System.out.println(lineItem);
    }

    // Test method using mock output
    public static void testPrintColorMap() {
        List<String> calls = new ArrayList<>();

        OutputFunction mockPrint = line -> calls.add(line);

        int count = printColorMap(mockPrint);

        // Assertions
        if (calls.size() != 25) {
            throw new AssertionError("Expected 25 entries, got " + calls.size());
        }
        if (!calls.get(0).equals("0 | White  | Blue")) {
            throw new AssertionError("First entry mismatch: " + calls.get(0));
        }
        if (!calls.get(24).equals("24 | Violet | Slate")) {
            throw new AssertionError("Last entry mismatch: " + calls.get(24));
        }

        System.out.println(" All tests passed!");
    }

    public static void main(String[] args) {
        // Run with console output
        printColorMap(ColorMap::printOnConsole);

        // Run test
        testPrintColorMap();
    }
}
