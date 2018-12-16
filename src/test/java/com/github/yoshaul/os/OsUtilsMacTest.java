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

import static com.github.yoshaul.os.MacOsUtils.MAC_OS_X;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link OsUtils} when running on Mac OS.
 *
 * @author Yossi Shaul
 */
@EnabledOnOs(OS.MAC)
class OsUtilsMacTest {

    @Test
    void testIsMac() {
        assertTrue(OsUtils.isMac());
        assertFalse(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isWindows());
    }

    @Test
    void testGetOsVersion() {
        assertTrue(OsUtils.getOsName().contains("Mac"));
    }

    @Test
    void testMacOsDetails() {
        OsDetails details = OsUtils.getOsDetails();
        assertNotNull(details);
        assertThat(details.getType()).isEqualTo(OsType.mac);
        assertThat(details.getName()).contains("Mac");
        assertThat(details.getPrettyName()).contains("Mac");
        assertThat(details.getPrettyName()).contains(details.getVersion());
        assertThat(details.getVersion()).isNotEmpty();
        assertThat(details.getVersionId()).isNotEmpty();
        assertThat(details.getDistribution()).isEqualTo("Mac");
        if (details.getName().startsWith(MAC_OS_X)) {
            assertWithMessage("Expected os codename in pretty name")
                    .that(details.getPrettyName()).contains("(");
        }
    }

}
