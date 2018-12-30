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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class to collect OS data on Linux OS.
 *
 * @author Yossi Shaul
 */
public class LinuxOsUtils {
    private static final Logger log = Logger.getLogger(LinuxOsUtils.class.getName());

    private LinuxOsUtils() {
        // utility class
    }

    public static OsDetails getOsDetails() {
        OsDetails osDetails;
        if (LinuxOsReleaseParser.isOsReleaseFileExist()) {
            osDetails = extractOsDetailsFromOsReleaseFile(Paths.get(LinuxOsReleaseParser.OS_RELEASE_FILE_PATH));
        } else {
            return extractOsDetailsFromJvm();
        }

        return osDetails;
    }

    static OsDetails extractOsDetailsFromOsReleaseFile(Path path) {
        try {
            LinuxOsRelease linuxOsRelease = LinuxOsReleaseParser.parseFile(path);
            return buildOsDetailsFromOsReleaseFile(linuxOsRelease);

        } catch (IOException ioe) {
            log.log(Level.FINE, ioe, () -> "Failed to parse os-release file");
        }
        return null;
    }

    private static OsDetails buildOsDetailsFromOsReleaseFile(LinuxOsRelease osRelease) {
        String version = osRelease.getVersion().orElseGet(OsUtils::getOsVersion);
        String versionId = osRelease.getVersionId().orElse(version);
        return new OsDetails(OsType.LINUX, osRelease.getPrettyName(), osRelease.getName(),
                version, versionId, osRelease.getId());
    }

    private static OsDetails extractOsDetailsFromJvm() {
        return new OsDetails(OsType.LINUX, null, null, null, null, null);
    }
}
