import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  /**
   * it returns the size of stack i.e the no of elements in stack
   */
  public int size();
  /**
   * prints the stack elements
   */
  public void print();  
}

/**
 * stack class which implements the stack class
 * @author hp
 *
 */
public class MyStack implements IStack {

	/**
	 * class of the node which is used in stack implementation
	 * @author hp
	 *
	 */
    public class Node{
        Object elem;
        Node next;
    }
    Node top = null;
/**
 *  global variable to counts the number of elements in the stack
 */
    public int size =0;
    

    /**
     * compares size with 0 then return true or false
     */
    public boolean isEmpty() {
        return(size == 0);
    }
    /**
     * return the size of stack
     */
    public int size() {
        return (size);
    }
    /**
     * removes the last element added to the stack and returns it and returns error if stack was empty
     */
    public Object pop() {
        if(isEmpty())
        {
            return("Error");
        }
        else {
            Object temp = top.elem;
            top = top.next;
            size --;
            return temp;
        }
    }
    /**
     * returns the top element without removing it returning an error if the stack was empty
     */
    public Object peek() {
        if(isEmpty())
        {
            return ("Error");
        }
        else
        {
            return (top.elem);
        }
    }
    /**
     * adding an element to the stack from the top of the stack so it takes element as a parameter 
     */
    public void push(Object element) {
        Node temp = new Node();
        temp.elem = element;
        temp.next = top;
        top = temp;
        size ++;
    }
    /**
     * prints stack elements and if it was empty it prints []
     */
    public void print() {
        Node temp =new Node();
        temp = top;
        if(temp==null)
        {
            System.out.println("[]");
        }
        else
        {
            System.out.print("[");
            while(temp != null)
            {
                System.out.print(temp.elem);
                if(temp.next!=null)
                {
                    System.out.print(", ");
                }
                temp = temp.next;
            }
            System.out.print("]");
        }
    }
    /**
     * Main method
     * @param args
     * takes array of strings as parameter
     */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String str = new String();
        str = sc.nextLine().replaceAll("\\[|\\]","");
        String [] res = str.split(", ");
        int i;
        MyStack obj =new MyStack();
        MyStack st = new MyStack();
        if (res.length == 1 && res[0].isEmpty())
                obj =new MyStack();
        else
        {
            for(i=0;i<res.length;i++)
            {
            st.push(Integer.parseInt(res[i]));
            }
            while(!(st.isEmpty()))
           {
            obj.push(st.pop());
           }
        }
        
        String oper = sc.nextLine();
        
        if(oper.equals("push"))
        {
            int var = sc.nextInt();
            obj.push(var);
            (obj).print();
        }
        else if(oper.equals("pop"))
        {
            if(obj.isEmpty())
            {
                System.out.println("Error");
            }
            else
            {
                 obj.pop();
                (obj).print();
            }
           
        }
        else if(oper.equals("peek"))
        {
            System.out.println(obj.peek());
        }
        else if(oper.equals("isEmpty"))
        {
            if(obj.isEmpty())
            {
                System.out.println("True");
            }
            else
            {
                System.out.println("False");
            }
        }
        else if(oper.equals("size"))
        {
            System.out.println(obj.size());
        }
        else
        {
            System.out.println("Error");
        }
    }
}