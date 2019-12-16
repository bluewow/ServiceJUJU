package com.stockmarket.www.controller.myAsset;

public class MergeArray {

	static void merge(int[] list, int start, int middle, int end) {
		int[] newArr = new int[end - start + 1];
		int left = start, right = middle + 1;
		int i = 0;
		do {
			if (list[left] < list[right])
				newArr[i++] = list[left++]; // ★
			else
				newArr[i++] = list[right++]; // ★
		} 
		while (left <= middle && right <= end);

		while (left <= middle)
			newArr[i++] = list[left++];
		while (right <= end)
			newArr[i++] = list[right++];

		for (i = 0; i < newArr.length; i++)
			list[start + i] = newArr[i];
	}

	static void mergeSort(int[] list, int start, int end) {
		if (start == end)
			return;
		int middle = (start + end) / 2;

		mergeSort(list, start, middle);
		mergeSort(list, middle + 1, end);
		merge(list, start, middle, end);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 2, 4, 6, 8, 10, 2, -1, -10, 100, 97 };

		System.out.print("정렬 전 : " + print(arr));

		mergeSort(arr, 0, arr.length - 1);

		System.out.print("정렬 후 : " + print(arr));
	}

	static String print(int[] arr) {
		String result = "";
		for (int i : arr)
			result += (i + " ");
		result += '\n';
		return result;
	}

}
