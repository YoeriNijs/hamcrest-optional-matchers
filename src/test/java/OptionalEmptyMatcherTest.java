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
        final boolean isEmpty = new OptionalEmptyMatcher<>().matches(emptyOpt);
        assertThat(isEmpty, is(true));
    }

    @Test
    void whenOptionalIsNotEmpty_thenMatchesShouldReturnFalse() {
        final var presentOpt = Optional.of("value");
        final boolean isEmpty = new OptionalEmptyMatcher<String>().matches(presentOpt);
        assertThat(isEmpty, is(false));
    }

    @Test
    void whenOptionalIsNull_thenMatchesShouldReturnTrue() {
        final boolean isEmpty = new OptionalEmptyMatcher<String>().matchesSafely(null); // Deliberately null
        assertThat(isEmpty, is(false));
    }

    @Test
    void whenOptionalIsEmpty_thenMatchesSafelyShouldReturnTrue() {
        final var emptyOpt = Optional.empty();
        final boolean isEmpty = new OptionalEmptyMatcher<>().matchesSafely(emptyOpt);
        assertThat(isEmpty, is(true));
    }

    @Test
    void whenOptionalIsNotEmpty_thenMatchesSafelyShouldReturnFalse() {
        final var presentOpt = Optional.of("value");
        final boolean isEmpty = new OptionalEmptyMatcher<String>().matchesSafely(presentOpt);
        assertThat(isEmpty, is(false));
    }

    @Test
    void whenDescribeToCalled_thenMismatchDescriptionShouldBeValid() {
        final var description = new StringDescription();
        new OptionalEmptyMatcher<>().describeTo(description);
        assertThat(description.toString(), is("is <Empty Optional>"));
    }

    @Test
    void whenOptionalIsEmpty_thenSafeMismatchDescriptionShouldBeValid() {
        final var emptyOpt = Optional.empty();
        final var description = new StringDescription().appendText("some optional");
        new OptionalEmptyMatcher<>().describeMismatchSafely(emptyOpt, description);
        assertThat(description.toString(), is("some optional had Optional value \"<Empty Optional>\""));
    }

    @Test
    void whenOptionalIsString_thenSafeMismatchDescriptionShouldBeValid() {
        final var optional = Optional.of("aap, noot, mies");
        final var description = new StringDescription().appendText("some optional");
        new OptionalEmptyMatcher<String>().describeMismatchSafely(optional, description);
        assertThat(description.toString(), is("some optional had Optional value \"aap, noot, mies\""));
    }

    @Test
    void whenOptionalIsNotString_thenSafeMismatchDescriptionShouldBeValid() {
        final var optional = Optional.of(false);
        final var description = new StringDescription().appendText("some optional");
        new OptionalEmptyMatcher<Boolean>().describeMismatchSafely(optional, description);
        assertThat(description.toString(), is("some optional had Optional value <false>"));
    }
}
