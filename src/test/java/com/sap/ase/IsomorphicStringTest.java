package com.sap.ase;


import org.junit.jupiter.api.Test;

import static com.sap.ase.IsomorphicChecker.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IsomorphicStringTest {

	@Test
	public void testSame() throws Exception {
		boolean result = comparePattern("egg", "add");
	    assertTrue(result, "start point");

	}

//	@Test
//	public void testDiff() throws Exception {
//		boolean result = comparePattern("egg", "abc");
//		assertFalse(result, "should not matched");
//	}

	@Test
	public void testLength() {
		boolean result = comparePattern("egg", "abcd");
		assertFalse(result);
	}
	@Test
	public void testNull() {
		boolean result = comparePattern(null, "abcd");
		assertFalse(result);
	}

	@Test
	public void testAABB() {
		boolean result = comparePattern("aa", "bb");
		assertTrue(result);
	}


	@Test
	public void testAABC() {
		boolean result = comparePattern("aa", "bc");
		assertFalse(result);
	}
	@Test
	public void testABCD() {
		boolean result = comparePattern("ab", "cd");
		assertTrue(result);
	}
}
