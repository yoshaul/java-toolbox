/*
 * Copyright 2018 Yossi Shaul
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        assertFalse(osRelease.getVersionId().isPresent());
        assertFalse(osRelease.getIdLike().isPresent());
    }

    @Test
    void testOsReleaseInfo() {
        Map<String, String> keyVals = new HashMap<>();
        keyVals.put("NAME", "Fedora");
        keyVals.put("PRETTY_NAME", "Fedora 17 (Beefy Miracle)");
        keyVals.put("ID", "fedora");
        keyVals.put("VERSION", "17 (Beefy Miracle)");
        keyVals.put("VERSION_ID", "17");
        LinuxOsRelease osRelease = new LinuxOsRelease(keyVals);
        assertEquals(osRelease.getName(), "Fedora");
        assertEquals(osRelease.getPrettyName(), "Fedora 17 (Beefy Miracle)");
        assertEquals(osRelease.getId(), "fedora");
        assertEquals(osRelease.getVersion().get(), "17 (Beefy Miracle)");
        assertEquals(osRelease.getVersionId().get(), "17");
        assertFalse(osRelease.getIdLike().isPresent());
    }

}