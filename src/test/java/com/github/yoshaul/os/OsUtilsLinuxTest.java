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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link OsUtils} when running on Linux.
 *
 * @author Yossi Shaul
 */
@EnabledOnOs(OS.LINUX)
class OsUtilsLinuxTest {

    @Test
    void testIsWindows() {
        assertTrue(OsUtils.isLinux());
        assertTrue(OsUtils.isUnix());
        assertFalse(OsUtils.isMac());
        assertFalse(OsUtils.isWindows());
    }

    @Test
    void testGetOsName() {
        assertTrue(OsUtils.getOsName().contains("Linux"));
    }

}