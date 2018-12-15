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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link OsUtils}.
 *
 * @author Yossi Shaul
 */
class OsUtilsTest {

    @Test
    void testIsOs() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac")) {
            testOnMac();
        } else if (osName.startsWith("Windows")) {
            testOnWindows();
        } else if (osName.toLowerCase().startsWith("Linux")) {
            testOnLinux();
        } else {
            fail("Unknown OS: " + osName);
        }
    }

    private void testOnLinux() {
        assertTrue(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isMac());
        assertFalse(OsUtils.isWindows());
    }

    private void testOnMac() {
        assertTrue(OsUtils.isMac());
        assertFalse(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isWindows());
    }

    private void testOnWindows() {
        assertTrue(OsUtils.isWindows());
        assertFalse(OsUtils.isMac());
        assertFalse(OsUtils.isLinux());
        assertFalse(OsUtils.isUnix());
    }

    public void testGetOsVersion() {
        assertNotNull(OsUtils.getOsVersion());
    }

}
