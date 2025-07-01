package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class comparatorRRT {

	static class Employee {
	    String name;
	    int age;
	    
		public Employee(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Employee [name=" + name + ", age=" + age + "]";
		}

	    // constructor, getters
		
		
	}
	public static void main(String[] args) {
//		List<Integer> numbe = Arrays.asList(1, 33,53, 23, 28, 26, 18, 46, 54, 57, 53);
	//	
	//	
//		List<String> names = Arrays.asList("Alice", "Bob", "Ankit", "John", "Aarav");
	//
//		List<String> startsWithA = names.stream().filter(n -> n.startsWith("A")).collect(Collectors.toList());
	//	
//		//startsWithA.forEach(n -> System.out.println(n));
	//	
//		List<Integer> numbers = Arrays.asList(2, 3, 5, 6, 8, 9);
//		 
//		List<Integer> squaresNum = numbers.stream().filter(n -> n % 2 == 0).map(m -> m*m).collect(Collectors.toList());
	//	
////		squaresNum.forEach(n -> System.out.println(n));
	//	
	//	
//		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
	//	
//		String vvb = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//				.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)) // Find the entry with the highest count
//	            .map(Map.Entry::getKey) // Extract the element (key) with the highest count
//	            .orElse(null);
////		System.out.println(vvb);
	//	
//		List<List<Integer>> nestedList = Arrays.asList(
//			    Arrays.asList(1, 2),
//			    Arrays.asList(3, 4),
//			    Arrays.asList(5)
//			);
	//	
//		List<Integer> bbv = nestedList.stream().flatMap(List :: stream).collect(Collectors.toList());
		
//		bbv.forEach(n-> System.out.println(n));
		
//		List<String> animals = Arrays.asList("cat", "dog", "elephant");
//		List<String> ANIMALS = animals.stream().map(n -> n.toUpperCase()).toList();
//		ANIMALS.forEach(n -> System.out.println(n));
		
		
//		Integer sum = numbe.stream()
//				.filter(n -> n % 2 != 0)
//				.mapToInt(m -> m*m)
//				.sum();
//		System.out.println(sum);	
//		List<Integer> jj = numbe.stream().filter(m -> m %2 ==0).toList();
		
		//jj.forEach(n -> System.out.println(n));

			
			
			List<Integer> numbers = Arrays.asList(4, 2, 1, 2, 3, 4, 5, 3);

			List<Integer> remDup = numbers.stream().distinct().sorted().toList();
			
			
			
			List<Employee> employees = Arrays.asList(
				    new Employee("Alice", 28),
				    new Employee("Bob", 35),
				    new Employee("Charlie", 32),
				    new Employee("David", 25)
				);
				
			 List<Employee> aged = employees.stream()
			            .filter(n -> n.getAge() > 30)
			            .collect(Collectors.toList()); // Use collect for Java 8
			 aged.forEach(n -> System.out.println(n));
			
		}
	
	
}
