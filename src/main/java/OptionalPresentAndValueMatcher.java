import com.google.common.annotations.VisibleForTesting;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Custom Hamcrest matcher to verify whether an Optional is present.
 */
public class OptionalPresentAndValueMatcher<T> extends TypeSafeMatcher<Optional<T>> {

    private final Matcher<? super T> matcher;

    @VisibleForTesting
    OptionalPresentAndValueMatcher(final Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    public static <T> Matcher<Optional<T>> presentOptionalAnd(final Matcher<? super T> matcher) {
        return new OptionalPresentAndValueMatcher<>(matcher);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("has Optional value that is ");
        matcher.describeTo(description);
    }

    @Override
    protected boolean matchesSafely(@Nullable final Optional<T> item) {
        return null != item && item.isPresent() && matcher.matches(item.get());
    }

    @Override
    protected void describeMismatchSafely(final Optional<T> item, final Description mismatchDescription) {
        if (item.isPresent()) {
            mismatchDescription.appendText("Optional value ");
            matcher.describeMismatch(item.get(), mismatchDescription);
        } else {
            mismatchDescription.appendText("was <Empty Optional>");
        }
    }
}
