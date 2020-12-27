import com.google.common.annotations.VisibleForTesting;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 * Custom Hamcrest matcher to verify whether an Optional is empty.
 */
public class OptionalEmptyMatcher<T> extends TypeSafeMatcher<Optional<T>> {

    @VisibleForTesting
    OptionalEmptyMatcher() {
        // Visible for testing
    }

    public static <T> Matcher<Optional<T>> emptyOptional() {
        return new OptionalEmptyMatcher<>();
    }

    @Override
    protected boolean matchesSafely(final Optional<T> optional) {
        return null != optional && optional.isEmpty();
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("is <Empty Optional>");
    }

    @Override
    protected void describeMismatchSafely(final Optional<T> item, final Description mismatchDescription) {
        mismatchDescription.appendText(" had Optional value ");
        if (item.isPresent()) {
            mismatchDescription.appendValue(item.get());
        } else {
            mismatchDescription.appendValue("<Empty Optional>");
        }
    }
}
