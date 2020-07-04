package tests;

import static org.junit.jupiter.api.Assertions.*;

import arrays_utilities.SortArraysUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortArrayUtilTest {
	
	final int[] unsorted = {4, 2, 1, 6, 3, 5};
	final int[] unsorted2 = {4};
	final int[] test = {1, 2, 3, 4, 5, 6};	
	final int[] test2 = {4};	
	final int[] copy = {4, 2, 1, 6, 3, 5};	
	final int[] min = {1,2};

	@Test
	public void testBubbleSort() {
		Assertions.assertArrayEquals(test, SortArraysUtil.bubbleSort(unsorted));
		assertArrayEquals(test2, SortArraysUtil.bubbleSort(unsorted2));
	}

	@Test
	public void testBucketSort() {
		assertArrayEquals(test, SortArraysUtil.bucketSort(unsorted));
		assertArrayEquals(test2, SortArraysUtil.bucketSort(unsorted2));
	}

	@Test
	public void testSelectionSort() {
		assertArrayEquals(test, SortArraysUtil.selectionSort(unsorted));
		assertArrayEquals(test2, SortArraysUtil.selectionSort(unsorted2));
	}

	@Test
	public void testMinValueOfArray() {
		assertArrayEquals(min, SortArraysUtil.minValueOfArray(unsorted,0));
	}

	@Test
	public void testDeepCopyArray() {
		assertArrayEquals(copy, SortArraysUtil.deepCopyArray(unsorted));
	}

}
