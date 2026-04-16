package com.library.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CommonLibraryTest {

	@Test
	void libraryLoads() {
		assertTrue(true);
	}

	@Test
	void exampleMockTest() {
		// Example Mockito test
	}
}

