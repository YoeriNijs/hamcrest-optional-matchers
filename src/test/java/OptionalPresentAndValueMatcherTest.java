import org.hamcrest.StringDescription;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;
import static org.hamcrest.Matchers.startsWith;

class OptionalPresentAndValueMatcherTest {

    @Test
    void whenOptionalExists_thenItShouldBePossibleToChain() {
        final var optional = Optional.of("some optional");
        assertThat(optional, is(OptionalPresentAndValueMatcher.presentOptionalAnd(startsWith("some"))));
    }

    @Test
    void whenOptionalEmpty_thenExpectMatchesSafelyReturnsFalse() {
        final var emptyOpt = Optional.empty();
        final var isPresent = new OptionalPresentAndValueMatcher<Object>(is(oneOf(Optional.empty())))
                .matchesSafely(emptyOpt);
        assertThat(isPresent, is(false));
    }

    @Test
    void whenOptionalNull_thenExpectMatchesSafelyReturnsFalse() {
        final var isPresent = new OptionalPresentAndValueMatcher<Object>(is(oneOf(Optional.empty())))
                .matchesSafely(null); // Deliberately null
        assertThat(isPresent, is(false));
    }

    @Test
    void whenOptionalPresent_thenExpectMatchesSafelyReturnsTrue() {
        final var optional = Optional.of("some optional");
        final var isPresent = new OptionalPresentAndValueMatcher<>(is(oneOf("some optional")))
                .matchesSafely(optional);
        assertThat(isPresent, is(true));
    }

    @Test
    void whenOptionalEmpty_thenExpectMatchesReturnsFalse() {
        final var emptyOpt = Optional.empty();
        final var isPresent = new OptionalPresentAndValueMatcher<Object>(is(oneOf(Optional.empty()))).matches(emptyOpt);
        assertThat(isPresent, is(false));
    }

    @Test
    void whenOptionalPresent_thenExpectMatchesReturnsTrue() {
        final var optional = Optional.of("some optional");
        final var isPresent = new OptionalPresentAndValueMatcher<>(is(oneOf(("some optional")))).matches(optional);
        assertThat(isPresent, is(true));
    }

    @Test
    void whenDescribeTo_thenExpectValidDescription() {
        final var description = new StringDescription();
        new OptionalPresentAndValueMatcher<>(startsWith("some")).describeTo(description);
        assertThat(description.toString(), is("has Optional value that is a string starting with \"some\""));
    }

    @Test
    void whenDescribeMismatchSafelyAndValueIsPresent_thenExpectValidDescription() {
        final var optional = Optional.of("some optional");
        final var description = new StringDescription();
        new OptionalPresentAndValueMatcher<>(startsWith("some")).describeMismatchSafely(optional, description);
        assertThat(description.toString(), is("Optional value was \"some optional\""));
    }

    @Test
    void whenDescribeMismatchSafelyAndValueIsEmpty_thenExpectValidDescription() {
        final var optional = Optional.empty();
        final var description = new StringDescription();
        new OptionalPresentAndValueMatcher<Object>(is(oneOf(Optional.empty())))
                .describeMismatchSafely(optional, description);
        assertThat(description.toString(), is("was <Empty Optional>"));
    }
}
