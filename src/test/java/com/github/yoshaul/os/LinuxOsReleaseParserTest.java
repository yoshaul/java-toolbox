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

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.yoshaul.Tests.getResourcePath;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LinuxOsReleaseParser}.
 *
 * @author Yossi Shaul
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
class LinuxOsReleaseParserTest {

    @Test
    void testReadCentOSReleaseFile() throws URISyntaxException, IOException {
        LinuxOsRelease osRelease = LinuxOsReleaseParser.parseFile(getResourcePath("/os/os-release.centos7"));
        assertNotNull(osRelease);
        assertEquals(osRelease.getId(), "centos");
        assertEquals(osRelease.getName(), "CentOS Linux");
        assertEquals(osRelease.getPrettyName(), "CentOS Linux 7 (Core)");
        assertEquals(osRelease.getVersion().get(), "7 (Core)");
        assertEquals(osRelease.getVersionId().get(), "7");
        assertEquals(osRelease.getIdLike().get(), "rhel fedora");
    }

    @Test
    void testReadDebian9ReleaseFile() throws URISyntaxException, IOException {
        LinuxOsRelease osRelease = LinuxOsReleaseParser.parseFile(getResourcePath("/os/os-release.debian9"));
        assertNotNull(osRelease);
        assertEquals(osRelease.getId(), "debian");
        assertEquals(osRelease.getName(), "Debian GNU/Linux");
        assertEquals(osRelease.getPrettyName(), "Debian GNU/Linux 9 (stretch)");
        assertEquals(osRelease.getVersion().get(), "9 (stretch)");
        assertEquals(osRelease.getVersionId().get(), "9");
        assertFalse(osRelease.getIdLike().isPresent());
    }

    @Test
    void testReadRedHat7ReleaseFile() throws URISyntaxException, IOException {
        LinuxOsRelease osRelease = LinuxOsReleaseParser.parseFile(getResourcePath("/os/os-release.rhel7"));
        assertNotNull(osRelease);
        assertEquals(osRelease.getId(), "rhel");
        assertEquals(osRelease.getName(), "Red Hat Enterprise Linux Server");
        assertEquals(osRelease.getPrettyName(), "Red Hat Enterprise Linux Server 7.6 (Maipo)");
        assertEquals(osRelease.getVersion().get(), "7.6 (Maipo)");
        assertEquals(osRelease.getVersionId().get(), "7.6");
        assertEquals(osRelease.getIdLike().get(), "fedora");
    }

    @Test
    void testReadUbuntu14ReleaseFile() throws URISyntaxException, IOException {
        LinuxOsRelease osRelease = LinuxOsReleaseParser.parseFile(getResourcePath("/os/os-release.ubuntu14"));
        assertNotNull(osRelease);
        assertEquals(osRelease.getId(), "ubuntu");
        assertEquals(osRelease.getName(), "Ubuntu");
        assertEquals(osRelease.getPrettyName(), "Ubuntu 14.04.5 LTS");
        assertEquals(osRelease.getVersion().get(), "14.04.5 LTS, Trusty Tahr");
        assertEquals(osRelease.getVersionId().get(), "14.04");
        assertEquals(osRelease.getIdLike().get(), "debian");
    }
}
