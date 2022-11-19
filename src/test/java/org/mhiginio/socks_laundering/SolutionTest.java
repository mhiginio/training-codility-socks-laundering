package org.mhiginio.socks_laundering;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private final Solution solution = new Solution();

    private static Stream<Arguments> testData() {
        return Stream.of(
                new TestCaseBuilder().capacity(2).clean(new int[]{1, 2, 1, 1}).dirty(new int[]{1, 4, 3, 2, 4})
                        .solution(3).build(),
                new TestCaseBuilder().capacity(1000)
                        .clean(new int[]{1, 10, 20, 30, 33, 33, 40, 50})
                        .dirty(new int[]{1, 10, 20, 30, 40, 50})
                        .solution(7).build(),
                new TestCaseBuilder().capacity(0)
                        .clean(new int[]{1})
                        .dirty(new int[]{1})
                        .solution(0).build(),
                new TestCaseBuilder().capacity(10)
                        .clean(new int[]{1})
                        .dirty(new int[]{19})
                        .solution(0).build(),
                new TestCaseBuilder().capacity(1)
                        .clean(new int[]{1, 2})
                        .dirty(new int[]{1, 2, 9})
                        .solution(1).build(),
                new TestCaseBuilder().capacity(5)
                        .clean(new int[]{1})
                        .dirty(new int[]{2, 2, 3, 3, 4, 4})
                        .solution(2).build(),
                new TestCaseBuilder().capacity(2)
                        .clean(new int[]{1})
                        .dirty(new int[]{3, 2, 5, 5})
                        .solution(1).build(),
                new TestCaseBuilder().capacity(3)
                        .clean(new int[]{1, 1, 2, 3})
                        .dirty(new int[]{3, 3, 3})
                        .solution(3).build()


        );
    }

    @ParameterizedTest(name = "Solution for capacity={0}, clean={1}, dirty={2} must be {3}")
    @MethodSource("testData")
    void test_should_return(int capacity, int[] clean, int[] dirty, int expectedSolution) {
        assertEquals(expectedSolution, solution.solution(capacity, clean, dirty));
    }

    private static class TestCaseBuilder {
        private int capacity;
        private int[] clean;
        private int[] dirty;
        private int solution;

        public TestCaseBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public TestCaseBuilder clean(int[] clean) {
            this.clean = clean;
            return this;
        }

        public TestCaseBuilder dirty(int[] dirty) {
            this.dirty = dirty;
            return this;
        }

        public TestCaseBuilder solution(int solution) {
            this.solution = solution;
            return this;
        }

        public Arguments build() {
            return Arguments.of(capacity, clean, dirty, solution);
        }
    }
}