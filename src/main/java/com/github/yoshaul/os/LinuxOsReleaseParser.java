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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A parser of linux distributions os-release files.
 *
 * @author Yossi Shaul
 * @see <a href="https://www.freedesktop.org/software/systemd/man/os-release.html">os-release</a>
 */
public class LinuxOsReleaseParser {

    public static LinuxOsRelease parseFile(Path osReleasePath) throws IOException {
        try (Stream<String> lines = Files.lines(osReleasePath, Charset.forName("UTF-8"))) {
            Map<String, String> keyVals = lines
                    .filter(filterCommentLines())
                    .filter(filterNonKeyValueLines())
                    .map(mapLineToKeyValuePair())
                    .collect(Collectors.toMap(p -> p.left, p -> p.right));
            return new LinuxOsRelease(keyVals);
        }
    }

    private static Predicate<String> filterCommentLines() {
        return line -> !line.startsWith("#");
    }

    private static Predicate<String> filterNonKeyValueLines() {
        return line -> line.split("=").length == 2;
    }

    private static Function<String, Pair> mapLineToKeyValuePair() {
        return line -> {
            String[] parts = line.split("=");
            String key = parts[0];
            String value = null;
            if (parts[1] != null) {
                value = removeLeadingAndTrailingQuotes(parts[1]);
            }
            return new Pair(key, value);
        };
    }

    private static String removeLeadingAndTrailingQuotes(String part) {
        return part.replaceAll("^\"|\"$", "");
    }

    private static class Pair {
        private final String left;
        private final String right;

        Pair(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

}
