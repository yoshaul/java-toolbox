/*
 * Copyright 2013 Yossi Shaul
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

import org.fest.assertions.Delta;
import org.junit.Test;

import static com.github.yoshaul.StorageUnit.*;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Unit tests for {@link StorageUnit}.
 *
 * @author Yossi Shaul
 */
public class StorageUnitTest {
    // four points after the digit
    private final Delta delta = Delta.delta(0.0001d);

    @Test
    public void convertBytes() {
        assertThat(BYTES.toBytes(0)).isEqualTo(0);

        assertThat(BYTES.toBytes(1)).isEqualTo(1);
        assertThat(BYTES.toBytes(1024)).isEqualTo(1024);

        assertThat(BYTES.toKB(1)).isEqualTo(0.00097, delta);
        assertThat(BYTES.toKB(1587)).isEqualTo(1.5498046875, delta);
        assertThat(BYTES.toKB(8745 * 1024)).isEqualTo(8745, delta);

        assertThat(BYTES.toMB(0)).isEqualTo(0);
        assertThat(BYTES.toMB(KB)).isEqualTo(0.00097656, delta);
        assertThat(BYTES.toMB(MB)).isEqualTo(1);
        assertThat(BYTES.toMB(8974541)).isEqualTo(8.55878925, delta);
        assertThat(BYTES.toMB(GB)).isEqualTo(1024);

        assertThat(BYTES.toGB(1)).isEqualTo(0, delta);
        assertThat(BYTES.toGB(9478)).isEqualTo(0, delta);
        assertThat(BYTES.toGB(45612346)).isEqualTo(0.04247980, delta);
        assertThat(BYTES.toGB(MB)).isEqualTo(0.00097656, delta);
        assertThat(BYTES.toGB(GB)).isEqualTo(1, delta);
        assertThat(BYTES.toGB(TB)).isEqualTo(1024, delta);

        assertThat(BYTES.toTB(1)).isEqualTo(0, delta);
        assertThat(BYTES.toTB(KB)).isEqualTo(0, delta);
        assertThat(BYTES.toTB(MB)).isEqualTo(0, delta);
        assertThat(BYTES.toTB(GB)).isEqualTo(0.00097656, delta);
        assertThat(BYTES.toTB(TB)).isEqualTo(1);
        assertThat(BYTES.toTB(PB)).isEqualTo(1024);

        assertThat(BYTES.toPB(1)).isEqualTo(0, delta);
        assertThat(BYTES.toPB(KB)).isEqualTo(0, delta);
        assertThat(BYTES.toPB(MB)).isEqualTo(0, delta);
        assertThat(BYTES.toPB(GB)).isEqualTo(0, delta);
        assertThat(BYTES.toPB(TB)).isEqualTo(0.00097656, delta);
        assertThat(BYTES.toPB(PB)).isEqualTo(1);
        assertThat(BYTES.toPB(PB * KB)).isEqualTo(1024);
    }

    @Test
    public void convertKilobytes() {
        assertThat(KILOBYTES.toBytes(1)).isEqualTo(KB);
        assertThat(KILOBYTES.toBytes(KB)).isEqualTo(MB);

        assertThat(KILOBYTES.toKB(1)).isEqualTo(1);
        assertThat(KILOBYTES.toKB(1024)).isEqualTo(1024);

        assertThat(KILOBYTES.toMB(1024)).isEqualTo(1);
        assertThat(KILOBYTES.toGB(1024)).isEqualTo(0.00097656, delta);
        assertThat(KILOBYTES.toTB(1024)).isEqualTo(0, delta);
        assertThat(KILOBYTES.toPB(1024)).isEqualTo(0, delta);
    }

    @Test
    public void convertMegabytes() {
        assertThat(MEGABYTES.toBytes(1)).isEqualTo(MB);
        assertThat(MEGABYTES.toBytes(KB)).isEqualTo(KB * MB);
        assertThat(MEGABYTES.toKB(1)).isEqualTo(1024);
        assertThat(MEGABYTES.toKB(1024)).isEqualTo(1024 * 1024);

        assertThat(MEGABYTES.toMB(1)).isEqualTo(1);
        assertThat(MEGABYTES.toMB(456)).isEqualTo(456);
        assertThat(MEGABYTES.toGB(1)).isEqualTo(0.00097656, delta);
        assertThat(MEGABYTES.toTB(1)).isEqualTo(0, delta);
        assertThat(MEGABYTES.toPB(1024)).isEqualTo(0, delta);
    }

    @Test
    public void convertGigabytes() {
        assertThat(GIGABYTES.toBytes(1)).isEqualTo(GB);
        assertThat(GIGABYTES.toBytes(KB)).isEqualTo(KB * GB);
        assertThat(GIGABYTES.toKB(1)).isEqualTo(1024 * 1024);
        assertThat(GIGABYTES.toKB(1024)).isEqualTo(1024 * 1024 * 1024);
        assertThat(GIGABYTES.toMB(1)).isEqualTo(1024);

        assertThat(GIGABYTES.toGB(1)).isEqualTo(1);

        assertThat(GIGABYTES.toTB(1)).isEqualTo(0.00097656, delta);
        assertThat(GIGABYTES.toPB(1024)).isEqualTo(0.00097656, delta);
        assertThat(GIGABYTES.toPB(1)).isEqualTo(0, delta);
    }

    @Test
    public void convertTerabytes() {
        assertThat(TERABYTES.toBytes(1)).isEqualTo(TB);
        assertThat(TERABYTES.toBytes(KB)).isEqualTo(KB * TB);
        assertThat(TERABYTES.toKB(1)).isEqualTo(1024 * 1024 * 1024);
        assertThat(TERABYTES.toMB(1)).isEqualTo(1024 * 1024);
        assertThat(TERABYTES.toGB(1)).isEqualTo(1024);

        assertThat(TERABYTES.toTB(1)).isEqualTo(1);

        assertThat(TERABYTES.toPB(1)).isEqualTo(0.00097656, delta);
        assertThat(TERABYTES.toPB(1024)).isEqualTo(1);
    }

    @Test
    public void convertPetabytes() {
        assertThat(PETABYTES.toBytes(1)).isEqualTo(PB);
        assertThat(PETABYTES.toBytes(KB)).isEqualTo(KB * PB);
        assertThat(PETABYTES.toKB(1)).isEqualTo((double) 1024 * 1024 * 1024 * 1024);
        assertThat(PETABYTES.toMB(1)).isEqualTo(1024 * 1024 * 1024);
        assertThat(PETABYTES.toGB(1)).isEqualTo(1024 * 1024);
        assertThat(PETABYTES.toTB(1)).isEqualTo(1024);

        assertThat(PETABYTES.toPB(1)).isEqualTo(1);
        assertThat(PETABYTES.toPB(1024)).isEqualTo(1024);
    }
}
