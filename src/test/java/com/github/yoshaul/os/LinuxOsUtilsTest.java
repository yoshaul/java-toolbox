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

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.github.yoshaul.Tests.getResourcePath;
import static com.github.yoshaul.os.LinuxOsUtils.extractOsDetailsFromOsReleaseFile;
import static com.google.common.truth.Truth.assertThat;

/**
 * @author Yossi Shaul
 */
class LinuxOsUtilsTest {

    @Test
    public void getOsDetails() {
        OsDetails osDetails = LinuxOsUtils.getOsDetails();
        Truth.assertThat(osDetails).isNotNull();
    }

    @Test
    void testExtractFromOsReleaseFileUbuntu() {
        OsDetails details = extractOsDetailsFromOsReleaseFile(getResourcePath("/os/os-release.ubuntu18"));
        assertThat(details).isNotNull();
        assertThat(details.getType()).isEqualTo(OsType.LINUX);
        assertThat(details.getName()).isEqualTo("Ubuntu");
        assertThat(details.getPrettyName()).isEqualTo("Ubuntu 18.04.1 LTS");
        assertThat(details.getVersion()).isEqualTo("18.04.1 LTS (Bionic Beaver)");
        assertThat(details.getVersionId()).isEqualTo("18.04");
        assertThat(details.getDistribution()).isEqualTo("ubuntu");
    }

    @Test
    void testExtractFromOsReleaseFileDebian() {
        OsDetails details = extractOsDetailsFromOsReleaseFile(getResourcePath("/os/os-release.debian9"));
        assertThat(details).isNotNull();
        assertThat(details.getType()).isEqualTo(OsType.LINUX);
        assertThat(details.getName()).isEqualTo("Debian GNU/Linux");
        assertThat(details.getPrettyName()).isEqualTo("Debian GNU/Linux 9 (stretch)");
        assertThat(details.getVersion()).isEqualTo("9 (stretch)");
        assertThat(details.getVersionId()).isEqualTo("9");
        assertThat(details.getDistribution()).isEqualTo("debian");
    }

    @Test
    void testExtractFromOsReleaseFilePathNotFound() {
        assertThat(extractOsDetailsFromOsReleaseFile(Paths.get("no_such_file"))).isNull();
    }

}