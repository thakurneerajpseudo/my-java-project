package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnonymousInnerClass {

	List<String> names = Arrays.asList("Ankit", "Aarav", "John", "Bob", "Alice").stream().map(n -> n.toUpperCase()).filter(n -> n.startsWith("A")).collect(Collectors.toList());

	List<String> startsWith = names.stream().filter(n -> n.startsWith("A")).collect(Collectors.toList());
	
	List<Integer> numbers = Arrays.asList(2, 3, 5, 6, 8, 9);
	
	Integer abc = numbers.stream().filter(n -> n%2 == 0).mapToInt(n -> n *n).sum(); 
	
	List<Integer> squares = numbers.stream().filter(n -> n % 2 ==0 ).map(n -> n* n ).collect(Collectors.toList());

	List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
	
	
	
	
	String wor  = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)) // Find the entry with the highest count
            .map(Map.Entry::getKey) // Extract the element (key) with the highest count
            .orElse(null);
	
	List<Integer> nb = Arrays.asList(4, 2, 1, 2, 3, 4, 5, 3);
	 List<Integer> mn = nb.stream().distinct().sorted((a, b) -> b-a).collect(Collectors.toList());
	 
	 Integer secondLargest = nb.stream()
			    .distinct()
			    .sorted(Comparator.reverseOrder()) // descending order
			    .skip(1)                           // skip largest
			    .findFirst()
			    .orElse(null);

	 List<Integer> nums = Arrays.asList(4, 8, 15, 11, 16, 9);
	 
	 Integer t = nums.stream().filter(n -> n % 2 != 0).filter(n -> n < 10).findFirst().get();

//	 class Employee {
//		    String name;
//		    String department;
//		    int age;
//		    
//			public Employee(String name, String department, int age) {
//				super();
//				this.name = name;
//				this.department = department;
//				this.age = age;
//			}
//
//			public String getName() {
//				return name;
//			}
//
//			public void setName(String name) {
//				this.name = name;
//			}
//
//			public String getDepartment() {
//				return department;
//			}
//
//			public void setDepartment(String department) {
//				this.department = department;
//			}
//
//			public int getAge() {
//				return age;
//			}
//
//			public void setAge(int age) {
//				this.age = age;
//			}
//		   
//		}

	 
//	 public static void main(String[] args) {
//		 
//		 List<Employee> employees = Arrays.asList(
//				    new Employee("Alice", "HR", 30),
//				    new Employee("Bob", "IT", 25),
//				    new Employee("Charlie", "HR", 35),
//				    new Employee("David", "Finance", 28)
//				);
//		 
//		 Map<String, Long> myMap = employees.stream()
//				 .collect(Collectors.groupingBy(n -> n.getDepartment(), Collectors.counting()));
//	}
//	 
}
