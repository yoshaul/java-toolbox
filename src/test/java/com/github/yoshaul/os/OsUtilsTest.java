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


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link OsUtils}.
 *
 * @author Yossi Shaul
 */
class OsUtilsTest {

    private static final String currentOS = System.getProperty("os.name");
    private static final String currentVersion = System.getProperty("os.version");

    @AfterEach
    void resetToCurrentOs() {
        switchOsTo(currentOS, currentVersion);
    }

    @Test
    void testGetOsName() {
        assertThat(OsUtils.getOsName()).isNotEmpty();
    }

    @Test
    void testGetOsVersion() {
        assertThat(OsUtils.getOsVersion()).isNotEmpty();
    }

    @Test
    void testGetOsNameUnknownType() {
        switchOsTo("new_os", "2.0");
        assertThat(OsUtils.getOsName()).isEqualTo("new_os");
        OsDetails osDetails = OsUtils.getOsDetails();
        assertThat(osDetails).isNotNull();
        assertThat(osDetails.getType()).isEqualTo(OsType.OTHER);
        assertThat(osDetails.getName()).isEqualTo("new_os");
        assertThat(osDetails.getPrettyName()).isEqualTo("new_os");
        assertThat(osDetails.getVersion()).isEqualTo("2.0");
        assertThat(osDetails.getVersionId()).isEqualTo("2.0");
        assertThat(osDetails.getDistribution()).isEqualTo("unknown");
    }

    @Test
    void testIsLinux() {
        switchOsTo("Linux", "1.0");
        assertTrue(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isMac());
        assertFalse(OsUtils.isWindows());
    }

    @Test
    void testIsMac() {
        switchOsTo("Mac", "1.0");
        assertTrue(OsUtils.isMac());
        assertFalse(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isWindows());
    }

    @Test
    void testIsWindows() {
        switchOsTo("Windows", "1.0");
        assertFalse(OsUtils.isLinux());
        assertFalse(OsUtils.isUnix());
        assertFalse(OsUtils.isMac());
        assertTrue(OsUtils.isWindows());
    }

    @Test
    void testGetOsNameLinux() {
        switchOsTo("Linux", "1.0");
        assertTrue(OsUtils.getOsName().contains("Linux"));
    }

    private void switchOsTo(String osName, String osVersion) {
        System.setProperty("os.name", osName);
        System.setProperty("os.version", osVersion);
    }

}
