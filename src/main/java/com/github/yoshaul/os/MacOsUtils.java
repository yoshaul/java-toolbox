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
 * A utility class to collect OS data on Mac OS.
 *
 * @author Yossi Shaul
 */
public class MacOsUtils {

    static final String MAC_OS_X = "Mac OS X";

    /**
     * @return Current Mac operating system details.
     */
    public static OsDetails getOsDetails() {
        String name = OsUtils.getOsName();    // e.g., Mac OS X
        String versionId = OsUtils.getOsVersion();  // e.g., 10.14.2
        String version = getPrettyVersion(name, versionId);
        String prettyName = getPrettyName(name, versionId);
        return new OsDetails(OsType.mac, prettyName, name, version, versionId, "Mac");
    }

    static String getPrettyVersion(String osName, String versionId) {
        if (!osName.equalsIgnoreCase(MAC_OS_X)) {
            return versionId;
        }

        String macOsXCodeName = getMacOsXCodeName(versionId);
        if (macOsXCodeName == null) {
            return versionId;
        }

        return buildPrettyVersion(versionId, macOsXCodeName);
    }

    static String getPrettyName(String osName, String versionId) {
        String prettyVersion = getPrettyVersion(osName, versionId);
        return osName + " " + prettyVersion;
    }

    private static String getMacOsXCodeName(String versionId) {
        if (isVersionOrMinorOf(versionId, "10.14")) {
            return "Mojave";
        } else if (isVersionOrMinorOf(versionId, "10.13")) {
            return "High Sierra";
        } else if (isVersionOrMinorOf(versionId, "10.12")) {
            return "Sierra";
        } else if (isVersionOrMinorOf(versionId, "10.11")) {
            return "El Capitan";
        } else if (isVersionOrMinorOf(versionId, "10.10")) {
            return "Yosemite";
        } else if (isVersionOrMinorOf(versionId, "10.9")) {
            return "Mavericks";
        } else if (isVersionOrMinorOf(versionId, "10.8")) {
            return "Mountain Lion";
        } else if (isVersionOrMinorOf(versionId, "10.7")) {
            return "Lion";
        } else if (isVersionOrMinorOf(versionId, "10.6")) {
            return "Snow Leopard";
        } else if (isVersionOrMinorOf(versionId, "10.5")) {
            return "Leopard";
        } else if (isVersionOrMinorOf(versionId, "10.4")) {
            return "Tiger";
        } else if (isVersionOrMinorOf(versionId, "10.3")) {
            return "Panther";
        } else if (isVersionOrMinorOf(versionId, "10.2")) {
            return "Jaguar";
        } else if (isVersionOrMinorOf(versionId, "10.1")) {
            return "Puma";
        } else if (isVersionOrMinorOf(versionId, "10.0")) {
            return "Cheetah";
        }
        return null;
    }

    private static boolean isVersionOrMinorOf(String versionId, String majorVersion) {
        return versionId.equals(majorVersion) || versionId.startsWith(majorVersion + ".");
    }

    private static String buildPrettyVersion(String versionId, String macOsXCodeName) {
        return versionId + " (" + macOsXCodeName + ")";
    }

}
