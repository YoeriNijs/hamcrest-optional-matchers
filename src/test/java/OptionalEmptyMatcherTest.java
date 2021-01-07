import org.hamcrest.StringDescription;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class OptionalEmptyMatcherTest {

    @Test
    void whenOptionalIsEmpty_thenItShouldNotFail() {
        final var emptyOpt = Optional.empty();
        assertThat(emptyOpt, is(OptionalEmptyMatcher.emptyOptional()));
    }

    @Test
    void whenOptionalIsEmpty_thenMatchesShouldReturnTrue() {
        final var emptyOpt = Optional.empty();
        final boolean isEmpty = OptionalEmptyMatcher.emptyOptional().matches(emptyOpt);
        assertThat(isEmpty, is(true));
    }

    @Test
    void whenOptionalIsNotEmpty_thenMatchesShouldReturnFalse() {
        final var presentOpt = Optional.of("value");
        final boolean isEmpty = OptionalEmptyMatcher.emptyOptional().matches(presentOpt);
        assertThat(isEmpty, is(false));
    }

    @Test
    void whenOptionalIsNull_thenMatchesShouldReturnFalse() {
        final boolean isEmpty = OptionalEmptyMatcher.emptyOptional().matches(null);
        assertThat(isEmpty, is(false));
    }

    @Test
    void whenOptionalIsEmpty_thenDescriptionShouldBeValid() {
        final var emptyOpt = Optional.empty();
        final var description = new StringDescription().appendText("some optional");
        OptionalEmptyMatcher.emptyOptional().describeMismatch(emptyOpt, description);
        assertThat(description.toString(), is("some optional had Optional value \"<Empty Optional>\""));
    }

    @Test
    void whenOptionalIsString_thenDescriptionShouldBeValid() {
        final var optional = Optional.of("aap, noot, mies");
        final var description = new StringDescription().appendText("some optional");
        OptionalEmptyMatcher.emptyOptional().describeMismatch(optional, description);
        assertThat(description.toString(), is("some optional had Optional value \"aap, noot, mies\""));
    }

    @Test
    void whenOptionalIsNotString_thenDescriptionShouldBeValid() {
        final var optional = Optional.of(false);
        final var description = new StringDescription().appendText("some optional");
        OptionalEmptyMatcher.emptyOptional().describeMismatch(optional, description);
        assertThat(description.toString(), is("some optional had Optional value <false>"));
    }

    @Test
    void whenDescribeTo_itShouldBeValid() {
        final var description = new StringDescription();
        OptionalEmptyMatcher.emptyOptional().describeTo(description);
        assertThat(description.toString(), is("<Empty Optional>"));
    }
}
