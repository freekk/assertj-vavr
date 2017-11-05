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
 * Copyright 2012-2017 the original author or authors.
 */
package org.assertj.vavr.api;

import io.vavr.control.Option;
import org.assertj.vavr.test.BaseTest;
import org.junit.Test;

import static io.vavr.API.Some;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class OptionAssert_map_Test extends BaseTest {

    @Test
    public void should_fail_when_Option_is_null() {
        thrown.expectAssertionError(actualIsNull());

        assertThat((Option<String>) null).map(String::length);
    }

    @Test
    public void should_pass_when_Option_is_empty() {
        assertThat(Option.<String> none()).map(String::length).isEmpty();
    }

    @Test
    public void should_pass_when_Option_contains_a_value() {
        assertThat(Option.of("42"))
          .map(String::length)
          .contains(2);
        assertThat(Option.of("42"))
          .map(s -> null)
          .isEqualTo(Some(null));
    }
}
