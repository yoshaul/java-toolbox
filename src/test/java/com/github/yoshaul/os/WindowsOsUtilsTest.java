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

import static com.github.yoshaul.os.WindowsOsUtils.getPrettyVersion;
import static com.google.common.truth.Truth.assertThat;

/**
 * @author Yossi Shaul
 */
class WindowsOsUtilsTest {

    private static final String currentOS = System.getProperty("os.name");
    private static final String currentVer = System.getProperty("os.version");

    @AfterEach
    void resetToCurrentOs() {
        switchOsTo(currentOS, currentVer);
    }

    @Test
    void getOsDetailsWindowsServer() {
        switchOsTo("Windows Server 2019", "10.0");
        OsDetails osDetails = WindowsOsUtils.getOsDetails();
        assertThat(osDetails).isNotNull();
        assertThat(osDetails.getType()).isEqualTo(OsType.WINDOWS);
        assertThat(osDetails.getName()).isEqualTo("Windows Server 2019");
        assertThat(osDetails.getPrettyName()).isEqualTo("Windows Server 2019");
        assertThat(osDetails.getVersion()).isEqualTo("Server 2019");
        assertThat(osDetails.getVersionId()).isEqualTo("10.0");
        assertThat(osDetails.getDistribution()).isEqualTo("windows");
    }

    @Test
    void getOsDetailsWindowsPC() {
        switchOsTo("Windows 10", "10.0");
        OsDetails osDetails = WindowsOsUtils.getOsDetails();
        assertThat(osDetails).isNotNull();
        assertThat(osDetails.getType()).isEqualTo(OsType.WINDOWS);
        assertThat(osDetails.getName()).isEqualTo("Windows 10");
        assertThat(osDetails.getPrettyName()).isEqualTo("Windows 10");
        assertThat(osDetails.getVersion()).isEqualTo("10");
        assertThat(osDetails.getVersionId()).isEqualTo("10.0");
        assertThat(osDetails.getDistribution()).isEqualTo("windows");
    }

    @Test
    void getPrettyVersionDifferentFormat() {
        assertThat(getPrettyVersion("Windows Server 2008 R2", "6.1")).isEqualTo("Server 2008 R2");
        assertThat(getPrettyVersion("Windows 8.1", "6.3")).isEqualTo("8.1");
        assertThat(getPrettyVersion("Windows XP", "5.1")).isEqualTo("XP");
        assertThat(getPrettyVersion("Windows Vista", "6.0")).isEqualTo("Vista");
    }

    @Test
    void getPrettyVersionUnknownFormatReturnsVersionId() {
        assertThat(getPrettyVersion("Server 2008 R2", "6.1")).isEqualTo("6.1");
        assertThat(getPrettyVersion("10", "10.0")).isEqualTo("10.0");
        assertThat(getPrettyVersion("Windows", "0.1")).isEqualTo("0.1");
    }

    private void switchOsTo(String osName, String osVersion) {
        System.setProperty("os.name", osName);
        System.setProperty("os.version", osVersion);
    }
}