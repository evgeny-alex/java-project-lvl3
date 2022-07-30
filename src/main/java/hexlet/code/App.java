package hexlet.code;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        String[][] arrOfNested = {{"a", "b"},
                {"c", "d"}};

        String[][] result = Arrays.stream(arrOfNested)
                .flatMap(elem -> Arrays.stream(new String[][]{elem, elem}))
                .toArray(String[][]::new);

        System.out.println(Arrays.deepToString(result));

        String[][] resultFull = Arrays.stream(arrOfNested)
                .map(elem ->
                        Arrays.stream(elem)
                                .flatMap(item -> Arrays.stream(new String[]{item, item}))
                                .toArray(String[]::new))
                .flatMap(item -> Arrays.stream(new String[][]{item, item}))
                .toArray(String[][]::new);

        System.out.println(Arrays.deepToString(resultFull));
    }
}
