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

	@Test
	public void testDiff() throws Exception {
		boolean result = comparePattern("egg", "abc");
		assertFalse(result, "should not matched");
	}
}
