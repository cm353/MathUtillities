package ArraysUtilities;

import java.util.Arrays;

final public class SortArraysUtil {

	private SortArraysUtil() {
	};

	public static int[] bubbleSort(final int[] input) {
		int[] result = SortArraysUtil.deepCopyArray(input);
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length - 1 - i; j++) {
				if (result[j] > result[j + 1]) {
					int tempValue = result[j + 1];
					result[j + 1] = result[j];
					result[j] = tempValue;
				}
			}
		}
		return result;
	}

	public static int[] bucketSort(final int[] input) throws IllegalArgumentException {
		if (SortArraysUtil.minValueOfArray(input, 0)[0] < 0)
			throw new IllegalArgumentException();
		int maxValue = Arrays.stream(input).max().getAsInt();
		int[] result = new int[input.length];
		int bucketIndex = 0;
		for (int i = 0; i <= maxValue; i++) {
			for (int j = 0; j < input.length; j++) {
				if (input[j] == i) {
					result[bucketIndex] = i;
					bucketIndex++;
				}
			}
		}
		return result;
	}

	public static int[] selectionSort(final int[] input) {
		int[] result = SortArraysUtil.deepCopyArray(input);
		for (int i = 0; i < input.length; i++) {
			int[] min = SortArraysUtil.minValueOfArray(result, i);
			if (result[i] > min[0]) {
				int indexValue = result[i];
				result[i] = min[0];
				result[min[1]] = indexValue;
			}
		}
		return result;
	}

	public static int[] minValueOfArray(final int[] array, final int startIndex) throws IllegalArgumentException {
		if (startIndex >= array.length)
			throw new IllegalArgumentException();
		int[] result = { array[startIndex], startIndex };
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] < result[0]) {
				result[0] = array[i];
				result[1] = i;
			}
		}
		return result;
	}

	public static int[] deepCopyArray(final int[] input) {
		int[] result = new int[input.length];
		for (int i = 0; i < input.length; i++)
			result[i] = input[i];
		return result;
	}

}
