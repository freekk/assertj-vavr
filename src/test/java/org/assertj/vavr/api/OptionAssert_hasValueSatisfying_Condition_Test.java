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

import io.vavr.control.Option;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldBe.shouldBe;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.vavr.api.OptionShouldBePresent.shouldBePresent;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class OptionAssert_hasValueSatisfying_Condition_Test {

    private Condition<String> passingCondition = new TestCondition<>(true);
    private Condition<String> notPassingCondition = new TestCondition<>();

    @Test
    void should_fail_when_option_is_null() {
        assertThatThrownBy(
                () -> assertThat((Option<String>) null).hasValueSatisfying(passingCondition)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(actualIsNull());
    }

    @Test
    void should_fail_when_option_is_empty() {
        assertThatThrownBy(
                () -> assertThat(Option.<String>none()).hasValueSatisfying(passingCondition)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldBePresent().create());
    }

    @Test
    void should_fail_when_condition_is_null() {
        assertThatThrownBy(
                () -> assertThat(Option.of("something")).hasValueSatisfying((Condition<String>) null)
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("The condition to evaluate should not be null");
    }

    @Test
    void should_pass_when_condition_is_met() {
        assertThat(Option.of("something")).hasValueSatisfying(passingCondition);
    }

    @Test
    void should_fail_when_condition_is_not_met() {
        assertThatThrownBy(
                () -> assertThat(Option.of("something")).hasValueSatisfying(notPassingCondition)
        )
                .isInstanceOf(AssertionError.class)
                .hasMessage(shouldBe("something", notPassingCondition).create());
    }
}
