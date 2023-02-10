import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
    /**
    * Takes a symbolic/numeric infix expression as input and converts it to
    * postfix notation. There is no assumption on spaces between terms or the
    * length of the term (e.g., two digits symbolic or numeric term)
    *
    * @param expression infix expression
    * @return postfix expression
    */
      
    public String infixToPostfix(String expression);
      
      
    /**
    * Evaluate a postfix numeric expression, with a single space separator
    * @param expression postfix expression
    * @return the expression evaluated value
    */
      
    public int evaluate(String expression);

}
/**
 * class to evaluate the postfix expression
 * @author hp
 *
 */
public class Evaluator implements IExpressionEvaluator {
    /**
     * a,b,c_2 are static variables that are the symbols of any postfix expression
     */
	public static int a;
    public static int b;
    public static int c_2;
    /**
     * class mystack which is implemented in the 1st problem
     * @author hp
     *
     */
    class MyStack {

    public class Node{
        Object elem;
        Node next;
    }
    Node top = null;
    public int size =0;
    
    

    
    public boolean isEmpty() {
        return(size == 0);
    }
    public int size() {
        return (size);
    }
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
    public void push(Object element) {
        Node temp = new Node();
        temp.elem = element;
        temp.next = top;
        top = temp;
        size ++;
    }
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
    }
    /**
     * infixToPostfix method which is used to convert from infix expression to postfix expression
     * it takes infix expression as a parameter and returns its corresponding postfix expression as string
     */
     public String infixToPostfix(String expression) {
        MyStack st = new MyStack();
        MyStack hop=new MyStack();
        String post = new String();
        int i;
        for(i=0;i<expression.length();i++)
        {
            char c = st.peek().toString().charAt(0);
            if(c=='-'&&post.equals("")&&expression.charAt(i)=='-')
                st.pop();
            else if(c=='-'&&!(post.equals(""))&&(expression.charAt(i)=='-'&&expression.charAt(i-1)=='-'))
            {
                st.pop();
                st.push('+');
            }
            else if((c=='/'||c=='*'||c=='^')&&!(post.equals(""))&&expression.charAt(i)=='-')
            {
                if(expression.charAt(i+1)=='-'&&post.length()%2==1)
                {
                    i=i+1;
                }
                else if(expression.charAt(i+1)=='-'&&post.length()%2==0)
                {
                    post = post+st.pop();
                    st.push('+');
                    i = i+1;
                }
            }
            else if(!(post.equals(""))&&(expression.charAt(i-1)=='+')&&expression.charAt(i)=='-'&&i!=expression.length()-1)
            {
                /*if(!(Character.isLetter(expression.charAt(i))))
                {
                    return "Error";
                }*/
                st.pop();
                st.push('-');
            }
            else if(expression.charAt(i)=='(')
            {
                if(i==expression.length())
                {
                    post = "Error";
                    return post;
                }
                else if(i!=expression.length()-1&&expression.charAt(i+1)==')')
                {
                    post = "Error";
                    return post;
                }
                else
                {
                    st.push(expression.charAt(i));
                    hop.push('(');
                }
            }
            else if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i)))
            {
                post = post+expression.charAt(i);
            }
            else if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*'||expression.charAt(i)=='/'||expression.charAt(i)=='^')
            {
                if(i==expression.length()-1)
                {
                    post = "Error";
                    return (post);
                }
                else if(st.isEmpty())
                {
                    if((expression.charAt(i)=='*'||expression.charAt(i)=='/'||expression.charAt(i)=='^')&&post.equals(""))
                    {
                        post = "Error";
                        return post;
                    }
                    else
                        st.push(expression.charAt(i));
                    
                }

                else if(expression.charAt(i+1)=='*'||expression.charAt(i+1)=='/'||expression.charAt(i+1)=='^')
                {
                    return("Error");
                }
                else if((expression.charAt(i)=='^'))
                    st.push(expression.charAt(i));
                else if((expression.charAt(i)=='*'||expression.charAt(i)=='/')&&(c=='('||c=='+'||c=='-'))
                    st.push(expression.charAt(i));
                else if((expression.charAt(i)=='+'||expression.charAt(i)=='-')&&(c=='('))
                    st.push(expression.charAt(i));
                else
                {
                    post = post + st.pop();
                    st.push(expression.charAt(i));
                }
            }
            else if(expression.charAt(i)==')')
            {
                if(post.equals("")||hop.isEmpty())
                {
                    post = "Error";
                    return post;
                }
                else
                {
                    while(c != '(')
                    {
                        post = post+st.pop();
                        c=st.peek().toString().charAt(0);
                    }
                    st.pop();
                    hop.pop();
                }
            }
            else
                return ("Error");
        }
        if(!(hop.isEmpty()))
        {
            post = "Error";
            return post;
        }
        while(!st.isEmpty())
        {
            post =post+st.pop();
        }
        return (post);
    }
     /**
      * method to evaluating the postfix expression i.e when substituting about a,b and c_2 
      */
     public int evaluate(String expression) {
        int i;
        int foper=0;
        int soper=0;
        int res=0;
        String str = infixToPostfix(expression);
        MyStack st = new MyStack();
        if(str.equals("Error"))
        {
            return(-9000);
        }
        else
        {
            for(i=0;i<str.length();i++)
            {
                if(Character.isLetter(str.charAt(i)))
                {
                    if(str.charAt(i)=='a')
                    {
                        st.push(a);
                    }
                    else if(str.charAt(i)=='b')
                    {
                        st.push(b);
                    }
                    else if(str.charAt(i)=='c')
                    {
                        st.push(c_2);
                    }
                }
                else
                {
                    if(st.size==1)
                    {
                        if(str.charAt(i)=='-')
                        {
                            res = (int)st.pop()*-1;
                            st.push(res);
                        }
                        else if(str.charAt(i)=='+')
                        {
                            continue;
                        }
                        else
                        {
                            return(-9000);
                        }
                    }
                    else
                    {
                    soper =  (int) st.pop();
                    foper =  (int) st.pop();
                    if(str.charAt(i)=='^')
                    {
                        
                        res =(int) Math.pow((int)foper, (int)soper);
                        st.push(res);
                    }
                    else if(str.charAt(i)=='*')
                    {
                        res =  ((int)foper *(int) soper);
                        st.push(res);
                    }
                    else if(str.charAt(i)=='/')
                    {
                        res = ((int)foper/(int)soper);
                        st.push(res);
                    }
                    else if(str.charAt(i)=='+')
                    {
                        res = ((int)foper+(int)soper);
                        st.push(res);
                    }
                    else if(str.charAt(i)=='-')
                    {
                        if(st.size==1)
                        {
                            res = (int)st.pop() * -1;
                            st.push(res);
                        }
                        else
                        {
                            res =  (int)foper-(int)soper;
                            st.push(res);
                        } 
                    }
                }
            }
            }
        }

        res = (int) st.pop();
        return res;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        String val_a = sc.nextLine().substring(2);
        a=Integer.parseInt(val_a);
        String val_b = sc.nextLine().substring(2);
        b=Integer.parseInt(val_b);
        String val_c = sc.nextLine().substring(2);
        c_2= Integer.parseInt(val_c);
        //System.out.println(a+b);
        
        String res = new String();
        
        int good = 0;
        Evaluator s = new Evaluator();
        res = s.infixToPostfix(expression);
        good = s.evaluate(expression);
        System.out.println(res);
        if(!res.equals("Error"))
        {
            System.out.println(good);
        }
       
    }
}