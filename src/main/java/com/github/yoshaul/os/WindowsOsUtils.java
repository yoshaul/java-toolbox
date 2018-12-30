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

/**
 * A utility class to collect OS data on Windows OS.
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/List_of_Microsoft_Windows_versions">List of Microsoft Windows versions</a>.
 *
 * @author Yossi Shaul
 */
public class WindowsOsUtils {

    private WindowsOsUtils() {
        // utility class
    }

    public static OsDetails getOsDetails() {
        String osName = OsUtils.getOsName();
        String versionId = OsUtils.getOsVersion();  // the JVM reports the internal version number
        String prettyVersion = getPrettyVersion(osName, versionId);

        return new OsDetails(OsType.WINDOWS, osName, osName, prettyVersion, versionId, "windows");
    }

    static String getPrettyVersion(String osName, String versionId) {
        int windowsIndex = osName.indexOf("Windows ");
        return windowsIndex >= 0 ? osName.substring("Windows ".length()) : versionId;
    }
}
