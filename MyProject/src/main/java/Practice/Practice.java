package Practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Practice {

	public static void main(String[] args) {

		int arr1[] = { 1, 3, 4, 5, 7 };
		int arr2[] = { 2, 6, 8, 9, 10, 70, 80 };
		int arr3[] = new int[arr1.length + arr2.length];

//		int index1 = 0;
//		int index2 = 0;
		
//		int i = 0;
//		while(index1<arr1.length && index2 < arr2.length) {
//			
//			if(arr1[index1] <= arr2[index2]) {
//				arr3[i] = arr1[index1];
//				index1 ++;
//			}else {
//				arr3[i] = arr2[index2];
//				index2 ++;
//			}
//			i ++;
//		}
//		
//		
//		System.out.println("i: "+ i);
//		System.out.println("index1: "+ index1);
//		System.out.println("index2: "+ index2);
//		
//		while(index1 < arr1.length) {
//			arr3[i++] = arr1[index1++];
//		}
//		
//		while(index2 < arr2.length) {
//			arr3[i++] = arr2[index2++];
//		}
		
		int index1 = 0, index2 = 0, i = 0;

		while (index1 < arr1.length || index2 < arr2.length) {
		    if (index1 < arr1.length && (index2 >= arr2.length || arr1[index1] <= arr2[index2])) {
		        arr3[i++] = arr1[index1++];
		    } else {
		        arr3[i++] = arr2[index2++];
		    }
		}

		System.out.println(Arrays.toString(arr3));
		

	}

	public static int[] sortArr(int[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-1- i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

}
