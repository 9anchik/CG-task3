package objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class ObjWriterTests {

    private final ObjWriter objWriter = new ObjWriter();

    // Тест вершин
    @ParameterizedTest
    @CsvSource({ "0.0f, 2.7f, 1", "0.3, 0, 0", "77.341f, 0.00052f, -17.3551f", "-1.2f, -15.0, 5.3" })
    public void vertexToStringTest(float x, float y, float z) {
        String result = objWriter.vertexToString(new Vector3f(x, y, z));
        String[] array = result.split(" ");
        Assertions.assertEquals("v", array[0]);
        Assertions.assertEquals(x, Float.parseFloat(array[1]));
        Assertions.assertEquals(y, Float.parseFloat(array[2]));
        Assertions.assertEquals(z, Float.parseFloat(array[3]));
    }

    // Тест текстурных координат
    @ParameterizedTest
    @CsvSource({ "0.0f, 2.7f", "0.3, 0", "77.341f, 0.00052f", "-1.2f, -15.0" })
    public void textureVertexToStringTest(float x, float y) {
        String result = objWriter.textureVertexToString(new Vector2f(x, y));
        String[] array = result.split(" ");
        Assertions.assertEquals("vt", array[0]);
        Assertions.assertEquals(x, Float.parseFloat(array[1]));
        Assertions.assertEquals(y, Float.parseFloat(array[2]));
    }

    // Тест нормалей
    @ParameterizedTest
    @CsvSource({ "0.0f, 2.7f, 1", "0.3, 0, 0", "77.341f, 0.00052f, -17.3551f", "-1.2f, -15.0, 5.3" })
    public void normalToStringTest(float x, float y, float z) {
        String result = objWriter.normalToString(new Vector3f(x, y, z));
        String[] array = result.split(" ");
        Assertions.assertEquals("vn", array[0]);
        Assertions.assertEquals(x, Float.parseFloat(array[1]));
        Assertions.assertEquals(y, Float.parseFloat(array[2]));
        Assertions.assertEquals(z, Float.parseFloat(array[3]));
    }

    // Тест полигона с вершинами (без текстурных координат и нормалей)
    @Test
    public void polygonToStringTestWithOnlyVertexIndices() {
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(List.of(0, 1, 2)));
        String result = objWriter.polygonToString(polygon);
        Assertions.assertEquals("f 1 2 3", result);
    }

    // Тест полигона с вершинами и текстурными координатами (без нормалей)
    @Test
    public void polygonToStringTestWithTextureVertexIndices() {
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(List.of(0, 1, 2, 5)));
        polygon.setTextureVertexIndices(new ArrayList<>(List.of(3, 5, 4, 2)));
        String result = objWriter.polygonToString(polygon);
        Assertions.assertEquals("f 1/4 2/6 3/5 6/3", result);
    }

    // Тест полигона с вершинами и нормалями (без текстурных координат)
    @Test
    public void polygonToStringTestWithNormalIndicesAndWithoutTextureVertexIndices() {
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(List.of(0, 1, 2, 5)));
        polygon.setNormalIndices(new ArrayList<>(List.of(3, 5, 4, 2)));
        String result = objWriter.polygonToString(polygon);
        Assertions.assertEquals("f 1//4 2//6 3//5 6//3", result);
    }

    // Тест полигона с вершиной, текстурными координатами и нормалями
    @Test
    public void polygonToStringTestWithNormalIndicesAndWithTextureVertexIndices() {
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(List.of(0, 1, 2, 5)));
        polygon.setTextureVertexIndices(new ArrayList<>(List.of(7, 4, 3, 6)));
        polygon.setNormalIndices(new ArrayList<>(List.of(3, 5, 4, 2)));
        String result = objWriter.polygonToString(polygon);
        Assertions.assertEquals("f 1/8/4 2/5/6 3/4/5 6/7/3", result);
    }
}
