package java8;

public interface myFun {
	
	default void myMethod() {
		
	}
	 
	 static void my() {
		 
	 }
	 
	 
	 public interface child {
			
			default void myMethod() {
				
			}
			 
			 static void my() {
				 
			 }
	 }
	 
	 public class myCass implements myFun,child{
		 public static void main(String[] args) {
			
		}

		@Override
		public void myMethod() {
			child.super.myMethod();
		}

	
		
	 }
}
