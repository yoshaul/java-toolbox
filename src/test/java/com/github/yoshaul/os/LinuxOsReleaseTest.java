package com.github.yoshaul.os;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.yoshaul.os.LinuxOsRelease.DEFAULT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Yossi Shaul
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
class LinuxOsReleaseTest {

    @Test
    void testEmptyOsReleaseInfo() {
        LinuxOsRelease osRelease = new LinuxOsRelease(new HashMap<>());
        assertEquals(osRelease.getName(), DEFAULT_NAME);
        assertEquals(osRelease.getPrettyName(), DEFAULT_NAME);
        assertEquals(osRelease.getId(), DEFAULT_NAME);
        assertFalse(osRelease.getVersion().isPresent());
        assertFalse(osRelease.getIdLike().isPresent());
    }

    @Test
    void testOsReleaseInfo() {
        Map<String, String> keyVals = new HashMap<>();
        keyVals.put("NAME", "Fedora");
        keyVals.put("PRETTY_NAME", "Fedora 17 (Beefy Miracle)");
        keyVals.put("ID", "fedora");
        keyVals.put("VERSION", "17");
        LinuxOsRelease osRelease = new LinuxOsRelease(keyVals);
        assertEquals(osRelease.getName(), "Fedora");
        assertEquals(osRelease.getPrettyName(), "Fedora 17 (Beefy Miracle)");
        assertEquals(osRelease.getId(), "fedora");
        assertEquals(osRelease.getVersion().get(), "17");
        assertFalse(osRelease.getIdLike().isPresent());
    }

}