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
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link LinuxOsReleaseParser}.
 *
 * @author Yossi Shaul
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
class LinuxOsReleaseParserTest {

    @Test
    void testReadCentOSReleaseFile() throws URISyntaxException, IOException {
        URL resource = getClass().getResource("/os/os-release.centos");
        LinuxOsRelease osRelease = LinuxOsReleaseParser.parseFile(Paths.get(resource.toURI()));
        assertNotNull(osRelease);
        assertEquals(osRelease.getName(), "CentOS Linux");
        assertEquals(osRelease.getPrettyName(), "CentOS Linux 7 (Core)");
        assertEquals(osRelease.getVersion().get(), "7 (Core)");
        assertEquals(osRelease.getId(), "centos");
        assertEquals(osRelease.getIdLike().get(), "rhel fedora");
    }

}
