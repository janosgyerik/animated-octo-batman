package com.janosgyerik.codereview.bazola;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class ListUtils {
	public static <T> List<List<T>> partition(List<T> orig, int size) {
		if (size < 1) {
			throw new IllegalArgumentException("The target partition size must be 1 or greater");
		}
		if (orig == null) {
			return Collections.emptyList();
		}
		int origSize = orig.size();
		List<List<T>> result = new ArrayList<>(origSize / size + 1);
		for (int i = 0; i < origSize; i += size) {
			result.add(orig.subList(i, Math.min(i + size, origSize)));
		}
		return result;
	}

	public static <T> List<List<T>> simon(List<T> originalList, int resultsPerList) {
		if (resultsPerList <= 0) {
			throw new IllegalArgumentException("resultsPerList must be positive");
		}
		List<List<T>> listOfLists = new ArrayList<>();
		List<T> latestList = new ArrayList<>();
		Iterator<T> iterator = originalList.iterator();

		while (iterator.hasNext()) {
			T next = iterator.next();
			if (latestList.size() >= resultsPerList) {
				listOfLists.add(latestList);
				latestList = new ArrayList<T>();
			}
			latestList.add(next);
		}

		if (!latestList.isEmpty()) {
			listOfLists.add(latestList);
		}

		return listOfLists;
	}
	public static <T> List<List<T>> tim(List<T> originalList, int itemsPerList) {
		List<List<T>> listOfLists = new ArrayList<>();
		int originalListSize = originalList.size();
		int listIndex = -1;

		for (int index = 0; index < originalListSize; index++) {
			if (index % itemsPerList == 0) {
				listIndex++;
				listOfLists.add(new ArrayList<>());
			}
			List<T> activeList = listOfLists.get(listIndex);
			activeList.add(originalList.get(index));
		}

		return listOfLists;
	}

	public static <T> List<List<T>> bazola(List<T> originalList, int splitCount) {
		List<List<T>> listOfLists= new ArrayList<>();
		listOfLists.add(new ArrayList<>());

		int originalListSize = originalList.size();
		int index = 0;
		int pageNumber = 0;
		int numItemsAdded = 0;

		while (index < originalListSize) {
			if (numItemsAdded > splitCount - 1) {
				numItemsAdded = 0;
				pageNumber ++;
				listOfLists.add(new ArrayList<>());
			}
			List activeList = listOfLists.get(pageNumber);
			activeList.add(originalList.get(index));
			numItemsAdded++;
			index++;
		}

		return listOfLists;
	}
}

public class ListUtilsTest {
	private <T> List<List<T>> partition(List<T> items, int size) {
		return ListUtils.partition(items, size);
	}

	@Test
	public void testPartitionEmpty() {
		assertEquals(Collections.EMPTY_LIST, partition(Arrays.asList(), 3));
	}

	@Test
	public void testPartitionNull() {
		assertEquals(Collections.EMPTY_LIST, partition(null, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPartitionZeroSize() {
		partition(Arrays.asList(1, 2, 3), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPartitionNegativeSize() {
		partition(Arrays.asList(1, 2, 3), -3);
	}

	@Test
	public void testPartitionInts() {
		assertEquals(
				Arrays.asList(
						Arrays.asList(1, 2),
						Arrays.asList(3, 4),
						Arrays.asList(5)),
				partition(Arrays.asList(1, 2, 3, 4, 5), 2));
	}

	@Test
	public void testPartitionStrings() {
		assertEquals(
				Arrays.asList(
						Arrays.asList("1", "2"),
						Arrays.asList("3", "4"),
						Arrays.asList("5")),
				partition(Arrays.asList("1", "2", "3", "4", "5"), 2));
	}

	@Test
	public void testPartition_MultipleOfSize() {
		assertEquals(
				Arrays.asList(
						Arrays.asList(1, 2),
						Arrays.asList(3, 4)),
				partition(Arrays.asList(1, 2, 3, 4), 2));
	}

	@Test
	public void testPartition_Size1() {
		assertEquals(
				Arrays.asList(
						Arrays.asList(1),
						Arrays.asList(2),
						Arrays.asList(3),
						Arrays.asList(4)),
				partition(Arrays.asList(1, 2, 3, 4), 1));
	}

	@Test
	public void testPartition_BiggerSize() {
		List<Integer> orig = Arrays.asList(1, 2, 3, 4);
		assertEquals(
				Arrays.asList(orig),
				partition(orig, orig.size() + 11));
	}

}
