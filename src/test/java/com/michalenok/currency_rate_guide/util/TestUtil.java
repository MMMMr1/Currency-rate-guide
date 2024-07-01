package com.michalenok.currency_rate_guide.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@UtilityClass
public class TestUtil {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);

    @SneakyThrows
    public static String load(String fileName) {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
        return IOUtils.toString(Objects.requireNonNull(systemResourceAsStream), StandardCharsets.UTF_8);
    }
}
