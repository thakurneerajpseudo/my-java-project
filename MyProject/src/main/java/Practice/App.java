package Practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Student /* implements Comparable<Student> */ {
	
	int id;
	String name;
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}


//	@Override
//	public int compareTo(Student o) {
//		return this.name.compareTo(o.name);
//	}
	

}

public class App{
	public static void main(String[] args) {
		
//		Set<Student> students = new TreeSet<Student>((s1, s2)->s1.name.compareTo(s2.name));
//		
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(2, "Rajib"));
		students.add(new Student(1, "Neeraj"));
		students.add(new Student(3, "Ashis"));
		students.add(new Student(4, "Abhay"));
		
		Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(s->s.name.concat("a")));
//		
//		//Collections.sort(students);
//		
//		//students.sort((s1, s2)->s1.name.compareTo(s2.name));
//		
//		//students = students.stream().sorted((s1, s2)->s1.name.compareTo(s2.name)).collect(Collectors.toList());
//		
//		System.out.println(students);
		
		
//		 Map<Boolean, Long> collect = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.groupingBy(n->n%2 != 0, Collectors.counting()));
//		
		System.out.println(collect);
		
		
	}
}
