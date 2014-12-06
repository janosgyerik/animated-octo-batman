package com.janosgyerik.codereview.jadogg;

import org.junit.Test;

import javax.servlet.http.HttpServlet;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

enum JavaDataType {

    STRING(String.class),
    INTEGER(Integer.class),
    BOOLEAN(Boolean.class),
    DOUBLE(Double.class),
    FLOAT(Float.class),
    LONG(Long.class);

    private final String value;

    JavaDataType(Class<?> klass) {
        this.value = klass.getSimpleName();
    }

    /**
     * @return string value
     */
    @Override
    public String toString() {
        return value;
    }

    private static final Map<String, JavaDataType> STRING_TO_ENUM = new HashMap<>();

    static {
        for (JavaDataType javaDataType : values()) {
            STRING_TO_ENUM.put(javaDataType.toString(), javaDataType);
        }
    }

    /**
     * convert String to JavaDataType
     *
     * @param type String type
     * @return JavaDataType or null if not found
     */
    public static JavaDataType fromString(String type) {
        return STRING_TO_ENUM.get(type);
    }
}

public class GenPropertyTest {
    @Test
    public void testGetLabels() {
        List<String> labels = new ArrayList<>();
//        for (JavaDataType javaDataType : JavaDataType.values()) {
//            labels.add(javaDataType.toString());
//        }
        labels.addAll(Arrays.asList(JavaDataType.values()).stream()
                .map(JavaDataType::toString)
                .collect(Collectors.toList()));
        assertEquals(Arrays.asList("String", "Integer", "Boolean", "Double", "Float", "Long"), labels);
    }

    @Test
    public void testFromString() {
        assertEquals(JavaDataType.BOOLEAN, JavaDataType.fromString("Boolean"));
        assertEquals(JavaDataType.DOUBLE, JavaDataType.fromString("Double"));
    }

    @Test
    public void testJavaClassNames() {
        assertEquals("String", String.class.getSimpleName());
        assertEquals("Double", Double.class.getSimpleName());
    }

    @Test
    public void testFormatStringBuilder() {
        String code = "import javafx.beans.property.*;\n\n"
                + "/**\n"
                + " * Class Information\n"
                + " * @author Your Name\n"
                + " */\n"
                + "public class %s {\n\n"
                + "%s\n\n"
                + "    public %s() {\n"
                + "%s\n"
                + "    }\n\n"
                + "%s\n\n"
                + "}";
        String placeHolder = ":";
        StringBuilder builder = new StringBuilder("hello");
        assertEquals("import javafx.beans.property.*;\n" +
                "\n" +
                "/**\n" +
                " * Class Information\n" +
                " * @author Your Name\n" +
                " */\n" +
                "public class : {\n" +
                "\n" +
                ":\n" +
                "\n" +
                "    public :() {\n" +
                ":\n" +
                "    }\n" +
                "\n" +
                ":\n" +
                "\n" +
                "}", String.format(code,
                placeHolder,
                placeHolder, placeHolder, placeHolder, placeHolder));
        String hello = null;
        switch (hello) {
            case "jack":
                System.out.println("test1");
                break;
            case "something":
            default:
                break;
        }
    }
}

class Blah extends HttpServlet {
    private final Map<Integer, String> products;

    public Blah() {
        products = new HashMap<>();
        products.put(1, "Sandpaper");
        products.put(2, "Nails");
        products.put(3, "Glue");
        products.put(4, "Paint");
        products.put(5, "Tape");
    }
}
