/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2017-2022 the original author or authors.
 */
package org.assertj.vavr.api;

import io.vavr.collection.HashMultimap;
import io.vavr.collection.Multimap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

@SuppressWarnings("unchecked")
class MultimapAssert_doesNotContainEntry_Test {

    @Test
    void should_pass_if_Multimap_does_not_contain_the_given_entry() {
        Multimap<String, String> actual = HashMultimap.withSeq().of(
                "key1", "value1", "key3", "value1", "key3", "value2"
        );

        assertThat(actual).doesNotContainEntry("key3", "value3");
    }

    @Test
    void should_pass_if_Multimap_is_empty() {
        Multimap<String, String> actual = HashMultimap.withSeq().empty();
        assertThat(actual).doesNotContainEntry("key3", "value3");
    }

    @Test
    void should_fail_when_Multimap_is_null() {
        assertThatThrownBy(
                () -> assertThat((Multimap<String, String>) null).doesNotContainEntry("key1", "value1")
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldNotBeNull().create());
    }

    @Test
    void should_fail_if_Multimap_contains_the_given_entry() {
        Multimap<String, String> actual = HashMultimap.withSeq().of("key1", "value1", "key3", "value3");

        assertThatThrownBy(
                () -> assertThat(actual).doesNotContainEntry("key1", "value1")
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage("\nExpecting\n  HashMultimap[List]((key1, value1), (key3, value3))\nnot to contain\n  [(key1, value1)]\nbut found\n  HashSet((key1, value1))\n");
    }
}
