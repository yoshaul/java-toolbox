/*
 * Copyright 2016 Yossi Shaul
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
// @formatter:off
package com.github.yoshaul;

import java.util.concurrent.TimeUnit;

/**
 * {@link StorageUnit} designed after Java's {@link TimeUnit}. It represents different binary storage units with
 * converters from one unit to another.
 * Binary storage unit is a power of 1024. This unit is used to represent RAM and file sizes.
 *
 * @author Yossi Shaul
 */
public enum StorageUnit {
    /**
     * Storage unit representing byte (8 bits)
     */
    BYTES {
        public double toBytes(long s) { return s; }
        public double toKB(long s) { return (double) s / KB; }
        public double toMB(long s) { return (double) s / MB; }
        public double toGB(long s) { return (double) s / GB; }
        public double toTB(long s) { return (double) s / TB; }
        public double toPB(long s) { return (double) s / PB; }
        public String symbol() { return "bytes"; }
    },
    /**
     * Storage unit representing kilobyte (1024 bytes)
     */
    KILOBYTES {
        public double toBytes(long s) { return s * KB; }
        public double toKB(long s) { return s; }
        public double toMB(long s) { return (double) s / KB; }
        public double toGB(long s) { return (double) s / MB; }
        public double toTB(long s) { return (double) s / GB; }
        public double toPB(long s) { return (double) s / TB; }
        public String symbol() { return "KB"; }
    },
    /**
     * Storage unit representing megabyte (1024 kilobytes)
     */
    MEGABYTES {
        public double toBytes(long s) { return s * MB; }
        public double toKB(long s) { return s * KB; }
        public double toMB(long s) { return s; }
        public double toGB(long s) { return (double) s / KB; }
        public double toTB(long s) { return (double) s / MB; }
        public double toPB(long s) { return (double) s / GB; }
        public String symbol() { return "MB"; }
    },
    /**
     * Storage unit representing gigabyte (1024 megabytes)
     */
    GIGABYTES {
        public double toBytes(long s) { return s * GB; }
        public double toKB(long s) { return s * MB; }
        public double toMB(long s) { return s * KB; }
        public double toGB(long s) { return s; }
        public double toTB(long s) { return (double) s / KB; }
        public double toPB(long s) { return (double) s / MB; }
        public String symbol() { return "GB"; }
    },
    /**
     * Storage unit representing terabyte (1024 gigabytes)
     */
    TERABYTES {
        public double toBytes(long s) { return s * TB; }
        public double toKB(long s) { return s * GB; }
        public double toMB(long s) { return s * MB; }
        public double toGB(long s) { return s * KB; }
        public double toTB(long s) { return s; }
        public double toPB(long s) { return (double) s / KB; }
        public String symbol() { return "TB"; }
    },
    /**
     * Storage unit representing terabyte (1024 gigabytes)
     */
    PETABYTES {
        public double toBytes(long s) { return s * PB; }
        public double toKB(long s) { return s * TB; }
        public double toMB(long s) { return s * GB; }
        public double toGB(long s) { return s * MB; }
        public double toTB(long s) { return s * KB; }
        public double toPB(long s) { return s; }
        public String symbol() { return "PB"; }
    };

    public static final long BT = 1L;
    public static final long KB = BT * 1024L;
    public static final long MB = KB * 1024L;
    public static final long GB = MB * 1024L;
    public static final long TB = GB * 1024L;
    public static final long PB = TB * 1024L;

    /**
     * Converts the given unit to bytes.
     * @param s The size
     * @return  The size converted to bytes
     */
    public abstract double toBytes(long s);
    /**
     * Converts the given unit to kilobytes.
     * @param s The size
     * @return  The size converted to kilobytes
     */
    public abstract double toKB(long s);
    /**
     * Converts the given unit to megabytes.
     * @param s The size
     * @return  The size converted to megabytes
     */
    public abstract double toMB(long s);
    /**
     * Converts the given unit to gigabytes.
     * @param s The size
     * @return  The size converted to gigabytes
     */
    public abstract double toGB(long s);
    /**
     * Converts the given unit to terabytes.
     * @param s The size
     * @return  The size converted to terabytes
     */
    public abstract double toTB(long s);
    /**
     * Converts the given unit to petabytes.
     * @param s The size
     * @return  The size converted to petabytes
     */
    public abstract double toPB(long s);

    /**
     * @return Symbol representing this unit (e.g., bytes, KB, GB)
     */
    public abstract String symbol();
}
