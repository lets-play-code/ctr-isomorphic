package com.xp.tcr;


import com.xp.tcr.IsomorphicChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IsomorphicStringTest {

	@ParameterizedTest(name = "{0} and {1} are isomorphic? {2}")
	@MethodSource("args")
	public void testSame(String s1, String s2, boolean expected) throws Exception {
		Assertions.assertEquals(expected, new IsomorphicChecker().isIsomorphic(s1, s2));
	}

	static Stream<Arguments> args () {
		return Stream.of(
				Arguments.of(null, "abcd", false),
				Arguments.of("egg", "abcd", false),
				Arguments.of("aa", "bb", true),
				Arguments.of("aa", "bc", false),
				Arguments.of("ab", "cd", true),
				Arguments.of("ada", "bob", true),
				Arguments.of("ada", "tom", false),
				Arguments.of("egg", "abc", false),
				Arguments.of("egg", "add", true)
		);
	}
}
