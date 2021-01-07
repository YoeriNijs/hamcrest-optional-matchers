import org.hamcrest.StringDescription;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
    void whenOptionalEmpty_thenExpectFalse() {
        final var emptyOpt = Optional.empty();
        final var isPresent = OptionalPresentAndValueMatcher.presentOptionalAnd(is(oneOf(empty()))).matches(emptyOpt);
        assertThat(isPresent, is(false));
    }

    @Test
    void whenOptionalNull_thenExpectFalse() {
        final var isPresent = OptionalPresentAndValueMatcher.presentOptionalAnd(is(oneOf(empty()))).matches(null);
        assertThat(isPresent, is(false));
    }

    @Test
    void whenOptionalIsPresent_thenExpectTrue() {
        final var optional = Optional.of("a value");
        final var isPresent = OptionalPresentAndValueMatcher.presentOptionalAnd(startsWith("a")).matches(optional);
        assertThat(isPresent, is(true));
    }

    @Test
    void whenOptionalPresent_thenExpectValidDescription() {
        final var optional = Optional.of("some optional");
        final var description = new StringDescription();
        OptionalPresentAndValueMatcher.presentOptionalAnd(startsWith("some")).describeMismatch(optional, description);
        assertThat(description.toString(), is("Optional value was \"some optional\""));
    }

    @Test
    void whenOptionalEmpty_thenExpectValidDescription() {
        final var emptyOpt = Optional.empty();
        final var description = new StringDescription();
        OptionalPresentAndValueMatcher.presentOptionalAnd(startsWith("some")).describeMismatch(emptyOpt, description);
        assertThat(description.toString(), is("was <Empty Optional>"));
    }

    @Test
    void whenOptionalNull_thenExpectValidDescription() {
        final var description = new StringDescription();
        OptionalPresentAndValueMatcher.presentOptionalAnd(startsWith("some")).describeMismatch(null, description);
        assertThat(description.toString(), is("was null"));
    }

    @Test
    void whenDescribeTo_itShouldBeValid() {
        final var description = new StringDescription();
        OptionalPresentAndValueMatcher.presentOptionalAnd(notNullValue()).describeTo(description);
        assertThat(description.toString(), is("Optional value that is not null"));
    }
}
