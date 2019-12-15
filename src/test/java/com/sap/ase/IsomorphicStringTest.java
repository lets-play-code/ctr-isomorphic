package com.sap.ase;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.sap.ase.IsomorphicChecker.*;
import static org.junit.jupiter.api.Assertions.*;

public class IsomorphicStringTest {

	@ParameterizedTest
	@MethodSource("args")
	public void testSame(String s1, String s2, boolean expected) throws Exception {
		assertEquals(expected, comparePattern(s1, s2));

	}

	static Stream<Arguments> args () {
		return Stream.of(
				Arguments.of(null, "abcd", false),
				Arguments.of("egg", "abcd", false),
				Arguments.of("egg", "add", true)
		);
	}

	@Test
	public void testAABB() {
		assertTrue(comparePattern("aa", "bb"));
	}
	@Test
	public void testAABC() {
		assertFalse(comparePattern("aa", "bc"));
	}
	@Test
	public void testABCD() {
		assertTrue(comparePattern("ab", "cd"));
	}
	@Test
	public void testABB_CDD() {
		assertTrue(comparePattern("abb", "cdd"));
	}
	@Test
	public void testADA_BOB() {
		assertTrue(comparePattern("ada", "bob"));
	}
	@Test
	public void testADA_TOP() {
		assertFalse(comparePattern("ada", "tom"));
	}
	@Test
	public void testDiff() {
		assertFalse(comparePattern("egg", "abc"));
	}
}
