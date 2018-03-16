package Uber.Calculator;

import java.util.Stack;

public class Caculator {

    public static void main(String[] args) {
        Caculator cal = new Caculator();
        System.out.println(cal.caculate("(9*(9+1*3)-10)/2"));
    }

    public int caculate(String input) {
        String num = "";
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        for (char ch: input.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                num += ch;
                continue;
            }

            if (ch == '(') {
                opStack.push('(');
                continue;
            } else {
                if (num != "") {
                    numStack.push(Integer.parseInt(num));
                }
                num = "";
            }

            if (ch == ')') {
                while (opStack.peek() != '(') {
                    apply(numStack, opStack.pop());
                }
                opStack.pop();
            } else {
                if (!opStack.isEmpty() && precedent(opStack.peek(), ch)) {
                    apply(numStack, opStack.pop());
                }
                opStack.push(ch);
            }
        }

        if (num.length() != 0) {
            numStack.push(Integer.parseInt(num));
        }

        while(!opStack.isEmpty()) {
            apply(numStack, opStack.pop());
        }

        return numStack.isEmpty() ? 0 : numStack.pop();
    }

    public boolean precedent(char op1, char op2) {
        if (op1 == '(') {
            return false;
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        }
        return true;
    }

    public void apply(Stack<Integer> numStack, char op) {
        switch (op) {
            case '+':
                numStack.push(numStack.pop() + numStack.pop());
                break;
            case '-':
                int num0 = numStack.pop();
                numStack.push(numStack.pop() - num0);
                break;
            case '*':
                numStack.push(numStack.pop() * numStack.pop());
                break;
            case '/':
                int tmpNum = numStack.pop();
                numStack.push(numStack.pop() / tmpNum);
                break;
            default:
                throw new RuntimeException("Operator not found");
        }
    }
}
