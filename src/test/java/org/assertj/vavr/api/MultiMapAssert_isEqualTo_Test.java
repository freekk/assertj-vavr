package org.assertj.vavr.api;

/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2019 the original author or authors.
 */

import io.vavr.collection.HashMultimap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;
import static org.assertj.core.description.EmptyTextDescription.emptyDescription;

class MultiMapAssert_isEqualTo_Test {

    @Test
    void should_pass_if_Map_is_equal_to() {
        assertThat(HashMultimap.withSeq().of(
                "key1", "value2",
                "key2", "value2"))
                .isEqualTo(HashMultimap.withSeq().of(
                        "key1", "value2",
                        "key2", "value2"));
    }

    @Test
    void should_fail_when_Map_is_null() {
        final HashMultimap<String, String> actual = null;

        final HashMultimap<String, String> expected = HashMultimap.withSeq().of("a", "b");

        assertThatThrownBy(
                () -> assertThat(actual).isEqualTo(expected)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldBeEqual(actual, expected, STANDARD_REPRESENTATION).newAssertionError(emptyDescription(), STANDARD_REPRESENTATION).getMessage());
    }

    @Test
    void should_fail_if_Map_is_not_equal_to() {
        final HashMultimap<Object, Object> actual = HashMultimap.withSeq().of(
                "key1", "value2",
                "key2", "value2");

        final HashMultimap<Object, Object> expected = HashMultimap.withSeq().of("a", "b");

        assertThatThrownBy(
                () -> assertThat(actual).isEqualTo(expected)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldBeEqual(actual, expected, STANDARD_REPRESENTATION).newAssertionError(emptyDescription(), STANDARD_REPRESENTATION).getMessage());
    }
}