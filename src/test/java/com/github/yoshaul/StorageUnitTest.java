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

package com.github.yoshaul;

import org.junit.jupiter.api.Test;

import static com.github.yoshaul.StorageUnit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link StorageUnit}.
 *
 * @author Yossi Shaul
 */
class StorageUnitTest {
    // testing for accuracy of four points after the digit
    private final double delta = 0.0001d;

    @Test
    void convertBytes() {
        assertEquals(BYTES.toBytes(0), 0);

        assertEquals(BYTES.toBytes(1), 1);
        assertEquals(BYTES.toBytes(1024), 1024);

        assertEquals(BYTES.toKB(1), 0.00097, delta);
        assertEquals(BYTES.toKB(1587), 1.5498046875, delta);
        assertEquals(BYTES.toKB(8745 * 1024), 8745, delta);

        assertEquals(BYTES.toMB(0), 0);
        assertEquals(BYTES.toMB(KB), 0.00097656, delta);
        assertEquals(BYTES.toMB(MB), 1);
        assertEquals(BYTES.toMB(8974541), 8.55878925, delta);
        assertEquals(BYTES.toMB(GB), 1024);

        assertEquals(BYTES.toGB(1), 0, delta);
        assertEquals(BYTES.toGB(9478), 0, delta);
        assertEquals(BYTES.toGB(45612346), 0.04247980, delta);
        assertEquals(BYTES.toGB(MB), 0.00097656, delta);
        assertEquals(BYTES.toGB(GB), 1, delta);
        assertEquals(BYTES.toGB(TB), 1024, delta);

        assertEquals(BYTES.toTB(1), 0, delta);
        assertEquals(BYTES.toTB(KB), 0, delta);
        assertEquals(BYTES.toTB(MB), 0, delta);
        assertEquals(BYTES.toTB(GB), 0.00097656, delta);
        assertEquals(BYTES.toTB(TB), 1);
        assertEquals(BYTES.toTB(PB), 1024);

        assertEquals(BYTES.toPB(1), 0, delta);
        assertEquals(BYTES.toPB(KB), 0, delta);
        assertEquals(BYTES.toPB(MB), 0, delta);
        assertEquals(BYTES.toPB(GB), 0, delta);
        assertEquals(BYTES.toPB(TB), 0.00097656, delta);
        assertEquals(BYTES.toPB(PB), 1);
        assertEquals(BYTES.toPB(PB * KB), 1024);
    }

    @Test
    void convertKilobytes() {
        assertEquals(KILOBYTES.toBytes(1), KB);
        assertEquals(KILOBYTES.toBytes(KB), MB);

        assertEquals(KILOBYTES.toKB(1), 1);
        assertEquals(KILOBYTES.toKB(1024), 1024);

        assertEquals(KILOBYTES.toMB(1024), 1);
        assertEquals(KILOBYTES.toGB(1024), 0.00097656, delta);
        assertEquals(KILOBYTES.toTB(1024), 0, delta);
        assertEquals(KILOBYTES.toPB(1024), 0, delta);
    }

    @Test
    void convertMegabytes() {
        assertEquals(MEGABYTES.toBytes(1), MB);
        assertEquals(MEGABYTES.toBytes(KB), KB * MB);
        assertEquals(MEGABYTES.toKB(1), 1024);
        assertEquals(MEGABYTES.toKB(1024), 1024 * 1024);

        assertEquals(MEGABYTES.toMB(1), 1);
        assertEquals(MEGABYTES.toMB(456), 456);
        assertEquals(MEGABYTES.toGB(1), 0.00097656, delta);
        assertEquals(MEGABYTES.toTB(1), 0, delta);
        assertEquals(MEGABYTES.toPB(1024), 0, delta);
    }

    @Test
    void convertGigabytes() {
        assertEquals(GIGABYTES.toBytes(1), GB);
        assertEquals(GIGABYTES.toBytes(KB), KB * GB);
        assertEquals(GIGABYTES.toKB(1), 1024 * 1024);
        assertEquals(GIGABYTES.toKB(1024), 1024 * 1024 * 1024);
        assertEquals(GIGABYTES.toMB(1), 1024);

        assertEquals(GIGABYTES.toGB(1), 1);

        assertEquals(GIGABYTES.toTB(1), 0.00097656, delta);
        assertEquals(GIGABYTES.toPB(1024), 0.00097656, delta);
        assertEquals(GIGABYTES.toPB(1), 0, delta);
    }

    @Test
    void convertTerabytes() {
        assertEquals(TERABYTES.toBytes(1), TB);
        assertEquals(TERABYTES.toBytes(KB), KB * TB);
        assertEquals(TERABYTES.toKB(1), 1024 * 1024 * 1024);
        assertEquals(TERABYTES.toMB(1), 1024 * 1024);
        assertEquals(TERABYTES.toGB(1), 1024);

        assertEquals(TERABYTES.toTB(1), 1);

        assertEquals(TERABYTES.toPB(1), 0.00097656, delta);
        assertEquals(TERABYTES.toPB(1024), 1);
    }

    @Test
    void convertPetabytes() {
        assertEquals(PETABYTES.toBytes(1), PB);
        assertEquals(PETABYTES.toBytes(KB), KB * PB);
        assertEquals(PETABYTES.toKB(1), (double) 1024 * 1024 * 1024 * 1024);
        assertEquals(PETABYTES.toMB(1), 1024 * 1024 * 1024);
        assertEquals(PETABYTES.toGB(1), 1024 * 1024);
        assertEquals(PETABYTES.toTB(1), 1024);

        assertEquals(PETABYTES.toPB(1), 1);
        assertEquals(PETABYTES.toPB(1024), 1024);
    }

    @Test
    void symbol() {
        assertEquals("bytes", BYTES.symbol());
        assertEquals("KB", KILOBYTES.symbol());
        assertEquals("MB", MEGABYTES.symbol());
        assertEquals("GB", GIGABYTES.symbol());
        assertEquals("TB", TERABYTES.symbol());
        assertEquals("PB", PETABYTES.symbol());
    }
}
