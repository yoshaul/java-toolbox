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
 * Common operating system details such as name, version, vendor etc.
 *
 * @author Yossi Shaul
 */
public class OsDetails {

    private final OsType osType;
    private final String prettyName;
    private final String name;
    private final String version;
    private final String versionId;
    private final String distribution;

    public OsDetails(OsType osType, String prettyName, String name, String version, String versionId,
            String distribution) {
        this.osType = osType;
        this.prettyName = prettyName;
        this.name = name;
        this.version = version;
        this.versionId = versionId;
        this.distribution = distribution;
    }

    public OsType getType() {
        return osType;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getVersionId() {
        return versionId;
    }

    public String getDistribution() {
        return distribution;
    }

    @Override
    public String toString() {
        return "OsDetails{" +
                "osType=" + osType +
                ", prettyName='" + prettyName + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", versionId='" + versionId + '\'' +
                ", distribution='" + distribution + '\'' +
                '}';
    }
}
