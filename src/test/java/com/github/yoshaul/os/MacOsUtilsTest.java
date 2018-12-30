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
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static com.github.yoshaul.os.MacOsUtils.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Yossi Shaul
 */
class MacOsUtilsTest {

    @Test
    void testPrettyVersionMacOsX() {
        assertThat(getPrettyVersion(MAC_OS_X, "10.9")).isEqualTo("10.9 (Mavericks)");
        assertThat(getPrettyVersion(MAC_OS_X, "10.14")).isEqualTo("10.14 (Mojave)");
    }

    @Test
    void testPrettyVersionMacOsXMinorVersion() {
        assertThat(getPrettyVersion(MAC_OS_X, "10.8")).isEqualTo("10.8 (Mountain Lion)");
        assertThat(getPrettyVersion(MAC_OS_X, "10.8.2")).isEqualTo("10.8.2 (Mountain Lion)");
    }

    @Test
    void testPrettyVersionMacOsXUnknownVersion() {
        String prettyVersion = getPrettyVersion(MAC_OS_X, "10.16");
        assertThat(prettyVersion).isEqualTo("10.16");
    }

    @Test
    void testPrettyVersionNonMacOsX() {
        String prettyVersion = getPrettyVersion("Mac OS", "10.9");
        assertThat(prettyVersion).isEqualTo("10.9");
    }

    @Test
    void testPrettyNameMacOsX() {
        assertThat(getPrettyName(MAC_OS_X, "10.0")).isEqualTo("Mac OS X 10.0 (Cheetah)");
        assertThat(getPrettyName(MAC_OS_X, "10.13")).isEqualTo("Mac OS X 10.13 (High Sierra)");
    }

    @Test
    void testPrettyNameMacOsXMinorVersion() {
        assertThat(getPrettyName(MAC_OS_X, "10.5")).isEqualTo("Mac OS X 10.5 (Leopard)");
        assertThat(getPrettyName(MAC_OS_X, "10.5.1")).isEqualTo("Mac OS X 10.5.1 (Leopard)");
    }

    @Test
    void testPrettyNameMacOsXUnknownVersion() {
        String prettyVersion = getPrettyName(MAC_OS_X, "10.16");
        assertThat(prettyVersion).isEqualTo("Mac OS X 10.16");
    }

    @Test
    void testPrettyNameNonMacOsX() {
        String prettyVersion = getPrettyName("Mac OS", "10.9");
        assertThat(prettyVersion).isEqualTo("Mac OS 10.9");
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testMacOsDetails() {
        OsDetails details = MacOsUtils.getOsDetails();
        assertNotNull(details);
        assertThat(details.getType()).isEqualTo(OsType.MAC);
        assertThat(details.getName()).contains("Mac");
        assertThat(details.getPrettyName()).contains("Mac");
        assertThat(details.getPrettyName()).contains(details.getVersion());
        assertThat(details.getVersion()).isNotEmpty();
        assertThat(details.getVersionId()).isNotEmpty();
        assertThat(details.getDistribution()).isEqualTo("mac");
        if (details.getName().startsWith(MAC_OS_X)) {
            assertWithMessage("Expected os codename in pretty name")
                    .that(details.getPrettyName()).contains("(");
        }
    }
}