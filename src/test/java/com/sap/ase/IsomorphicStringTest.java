package com.sap.ase;


import org.junit.jupiter.api.Test;

import static com.sap.ase.IsomorphicChecker.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IsomorphicStringTest {

	@Test
	public void testSame() throws Exception {
		assertTrue(comparePattern("egg", "add"), "start point");

	}

	@Test
	public void testLength() {
		assertFalse(comparePattern("egg", "abcd"));
	}
	@Test
	public void testNull() {
		assertFalse(comparePattern(null, "abcd"));
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
