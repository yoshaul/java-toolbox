package com.github.yoshaul.os;

import java.util.Map;
import java.util.Optional;

/**
 * Represents the information captured in Linux os-release file.
 *
 * @author Yossi Shaul
 * @see <a href="https://www.freedesktop.org/software/systemd/man/os-release.html">os-release</a>
 */
public class LinuxOsRelease {
    static final String DEFAULT_NAME = "Linux";

    private final Map<String, String> keyVals;

    public LinuxOsRelease(Map<String, String> keyVals) {
        this.keyVals = keyVals;
    }

    /**
     * @return A string identifying the operating system, without a version component,and suitable for presentation to
     * the user. If not set, defaults to "NAME=Linux". Example: "NAME=Fedora" or "NAME="Debian GNU/Linux"".
     */
    public String getName() {
        return keyVals.getOrDefault("NAME", DEFAULT_NAME);
    }

    /**
     * @return A pretty operating system name in a format suitable for presentation to the user. May or may not contain
     * a release code name or OS version of some kind, as suitable. If not set, defaults to "PRETTY_NAME="Linux"".
     * Example: "PRETTY_NAME="Fedora 17 (Beefy Miracle)"".
     */
    public String getPrettyName() {
        return keyVals.getOrDefault("PRETTY_NAME", DEFAULT_NAME);
    }

    /**
     * @return A string identifying the operating system version, excluding any OS name information, possibly including
     * a release code name, and suitable for presentation to the user. This field is optional.
     * Example: "VERSION=17" or "VERSION="17 (Beefy Miracle)"".
     */
    public Optional<String> getVersion() {
        return Optional.ofNullable(keyVals.get("VERSION"));
    }

    /**
     * @return A lower-case string (no spaces or other characters outside of 0–9, a–z, ".", "_" and "-") identifying
     * the operating system, excluding any version information and suitable for processing by scripts or usage in
     * generated filenames. If not set, defaults to "ID=linux". Example: "ID=fedora" or "ID=debian".
     */
    public String getId() {
        return keyVals.getOrDefault("ID", DEFAULT_NAME);
    }

    /**
     * @return A space-separated list of operating system identifiers in the same syntax as the ID= setting. It should
     * list identifiers of operating systems that are closely related to the local operating system in regards to
     * packaging and programming interfaces, Example: for an operating system with "ID=centos", an assignment of
     * "ID_LIKE="rhel fedora"" would be appropriate. For an operating system with "ID=ubuntu", an assignment of
     * "ID_LIKE=debian" is appropriate.
     */
    public Optional<String> getIdLike() {
        return Optional.ofNullable(keyVals.get("ID_LIKE"));
    }
}
