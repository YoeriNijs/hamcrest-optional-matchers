# hamcrest-optional-matchers
Some custom Hamcrest Optional matchers. Still under development.

Supported matchers:
- emptyOptional()
- presentOptionalAnd()

The idea is the following. Working example:
```
    @Test
    whenEmptyOptionalAndItShouldNotBe_thenFail() {
        final var empty = Optional.empty();
        assertThat(empty, isNot(emptyOptional()));
    }
```

And:
```
    @Test
    whenOptionalProvidedWithSome_itShouldSucceed() {
        final var optional = Optional.of("some optional");
        assertThat(optional, is(presentOptionalAnd(startsWith("some"))));
    }
```
