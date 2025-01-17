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

import org.junit.AssumptionViolatedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

abstract class BaseAssumptionsTest {

    @ParameterizedTest
    @MethodSource("provideAssumptionsRunners")
    public void should_ignore_test_when_assumption_fails(AssumptionRunner<?> assumptionRunner) {
        assertThatExceptionOfType(AssumptionViolatedException.class).isThrownBy(assumptionRunner::runFailingAssumption);
    }

    @ParameterizedTest
    @MethodSource("provideAssumptionsRunners")
    public void should_run_test_when_assumption_passes(AssumptionRunner<?> assumptionRunner) {
        assertThatCode(assumptionRunner::runPassingAssumption).doesNotThrowAnyException();
    }

}
