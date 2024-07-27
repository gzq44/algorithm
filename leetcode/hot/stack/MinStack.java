package hot.stack;

import java.lang.invoke.LambdaConversionException;
import java.util.ArrayDeque;

/**
 * 153
 *
 * @author gzq44
 * @date 2024/02/29 23:20
 **/
public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-2);
        minStack.getMin();
        minStack.pop();
//        minStack.top();
        minStack.getMin();
    }

    ArrayDeque<Integer> d1;
    ArrayDeque<Integer> d2;
    public MinStack() {
        d1 = new ArrayDeque<>();
        d2 = new ArrayDeque<>();
    }

    public void push(int val) {
        if ((!d1.isEmpty() && d1.peek() >= val) || d1.size() == 0) d1.push(val);
        d2.push(val);
    }

    public void pop() {
        Integer poll = d2.poll();
        if (!d1.isEmpty() && d1.peek().intValue() == poll) d1.poll();
    }

    public int top() {
        return d2.peek();
    }

    public int getMin() {
        return d1.peek();
    }
}
