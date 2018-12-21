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
 * Util class to extract operating system data.
 *
 * @author Yossi Shaul
 */
@SuppressWarnings("WeakerAccess")
public final class OsUtils {
    private OsUtils() {
        // utility class
    }

    /**
     * @return String representing the current operating system name
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     * @return String representing the current operating system version
     */
    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    /**
     * @return True if this operating system is Mac OS
     */
    public static boolean isMac() {
        return getOsName().contains("Mac");
    }

    /**
     * @return True if this operating system is Linux based
     */
    public static boolean isLinux() {
        return getOsName().toLowerCase().contains("linux");
    }

    /**
     * @return True if this operating system is Windows
     */
    public static boolean isWindows() {
        return getOsName().toLowerCase().contains("Windows");
    }

    /**
     * @return True if this operating system is Unix flavor (Linux, Mac, etc.)
     */
    public static boolean isUnix() {
        return isLinux() || isMac();
    }

    /**
     * @return An object with details about the current runtime operating system.
     * @see OsDetails
     */
    public static OsDetails getOsDetails() {
        if (isMac()) {
            return MacOsUtils.getOsDetails();
        } else if (isLinux()) {
            return LinuxOsUtils.getOsDetails();
        }
        return null;
    }
}
