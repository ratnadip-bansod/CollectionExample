package example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionExample {
	
	  public static void main(String args[]){  
	      ArrayList<String> alist=new ArrayList<String>();  
	      alist.add("blue");
	      alist.add("green");
	      alist.add("yellow");
	      alist.add("red");
	      alist.add("orange");
	      alist.add("white");
	  
	      //displaying elements
	      System.out.println(alist);
	  
	      //iterating ArrayList
	      for(String str:alist) { 
	         System.out.print(str);  
	      }  
	      
	      //Adding element "black" at first position
	      alist.add(0, "black");
	      
	      //retrieve an element (at a specified index) from a given array list.
	      String str= alist.get(2);
	      System.out.println(str); 
	      
	      //update specific array element by given element
	      alist.set(2, "gree");
	      System.out.println(alist);
	      
	      //remove the third element from a arraylist
	      alist.remove(2);
	      System.out.println(alist);
	     
	      // to search an element in a array list
	     boolean elementData= alist.contains("blue");
	     System.out.println(elementData);
	     
	     //to sort a given array list
	     System.out.println("List before sort: "+alist);
	     Collections.sort(alist);
	     System.out.println("List after sort: "+alist); 
	     
	     //to copy one arraylist into another
	     ArrayList<String> alist2=new ArrayList<String>();  
	      alist.add("blue");
	      alist.add("green");
	      Collections.copy(alist,alist2);
	      System.out.println(alist);
	      
	      // to reverse elements in a array list
	      ArrayList<String> revArrayList = new ArrayList<String>(); 
	        for (int i = alist.size() - 1; i >= 0; i--) {	   
	            revArrayList.add(alist.get(i)); 
	        } 
	        System.out.println(revArrayList);
	        
	       //to extract a portion of a array list 
	        List<String> sub_List = alist.subList(0, 3);
	        System.out.println("List of first three elements: " + sub_List);
	        
	       //compare two array lists.
	        boolean isEqual = alist.equals(revArrayList);     
	        System.out.println(isEqual);
	        
	       // increase the size of an array list.
	        ArrayList<String> c1= new ArrayList<String>(2);
	          c1.add("Red");
	          c1.add("Green");	         
	          System.out.println("old c1 array list: " + c1);
	          //Increase capacity to 4
	          c1.ensureCapacity(4);
	          c1.add("White");
	          c1.add("Pink");	         
	          System.out.println("New c1 array list: " + c1);	   
	      
	   } 

}
