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

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class MapAssert_hasSize_Test {

    @Test
    void should_pass_if_Map_is_of_expected_size() {
        assertThat(HashMap.of("key", "value")).hasSize(1);
    }

    @Test
    void should_fail_when_Map_is_null() {
        assertThatThrownBy(
                () -> assertThat((Map<String, String>) null).hasSize(1)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldNotBeNull().create());
    }

    @Test
    void should_fail_if_Map_is_not_of_expected_size() {
        final Map<Object, Object> actual = HashMap.empty();

        assertThatThrownBy(
                () -> assertThat(actual).hasSize(1)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldHaveSize(actual, 0, 1).create());
    }
}
