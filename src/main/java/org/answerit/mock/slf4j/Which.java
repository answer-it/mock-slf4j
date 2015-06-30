package org.answerit.mock.slf4j;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * Decorates another Matcher, retaining the behaviour but allowing tests
 * to be slightly more expressive.
 *
 */
public class Which<T> extends BaseMatcher<T> {
    private final Matcher<T> matcher;

    public Which(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    /**
	 * {@inheritDoc}
	 */
    public boolean matches(Object arg) {
        return matcher.matches(arg);
    }

    /**
	 * {@inheritDoc}
	 */
    public void describeTo(Description description) {
        description.appendText("which ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    /**
     * Decorates another Matcher, retaining its behaviour, but allowing tests
     * to be slightly more expressive.
     * 
     */
    @Factory
    public static <T> Matcher<T> which(Matcher<T> matcher) {
        return new Which<T>(matcher);
    }

}
