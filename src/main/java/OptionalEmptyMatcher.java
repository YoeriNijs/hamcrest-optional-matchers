import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Custom matcher for validating empty optionals.
 */
public final class OptionalEmptyMatcher extends TypeSafeMatcher<Optional<?>> {

    private OptionalEmptyMatcher() {
        // Utility class
    }

    @SuppressWarnings("java:S1452") // Wildcard since we are not interested in the return type
    public static Matcher<Optional<?>> emptyOptional() {
        return new OptionalEmptyMatcher();
    }

    @Override
    @SuppressWarnings("java:S2789") // Sonar complains that this optional should not be nullable, but it is possible
    protected boolean matchesSafely(@Nullable final Optional<?> optional) {
        return null != optional && optional.isEmpty();
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("is <Empty Optional>");
    }

    @Override
    protected void describeMismatchSafely(final Optional<?> item, final Description mismatchDescription) {
        mismatchDescription.appendText(" had Optional value ");
        if (item.isPresent()) {
            mismatchDescription.appendValue(item.get());
        } else {
            mismatchDescription.appendValue("<Empty Optional>");
        }
    }
}
