using System;
using System.Collections;
using System.Linq;
using System.Collections.Generic;

namespace Stack
{
    class Program
    {
        static void Main(string[] args)
        {
            //input sample : 1 2 + 3 4 - *
            var line = Console.ReadLine()?.Split();
            Stack<string> stack = new Stack<string>();

            foreach (var s in line)
            {
                stack.Push(s);
                switch (stack.Peek())
                {
                    case "+":
                        stack.Pop();
                        var t1 = stack.Pop();
                        var t2 = stack.Pop();
                        Console.WriteLine($"{t2} + {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) + int.Parse(t1)));
                        break;
                    case "-":
                        stack.Pop();
                        var t3 = stack.Pop();
                        var t4 = stack.Pop();
                        Console.WriteLine($"{t4} - {t3}");
                        stack.Push(Convert.ToString(int.Parse(t4) - int.Parse(t3)));
                        break;
                    case "*":
                        stack.Pop();
                        var t5 = stack.Pop();
                        var t6 = stack.Pop();
                        Console.WriteLine($"{t6} * {t5}");
                        stack.Push(Convert.ToString(int.Parse(t6) * int.Parse(t5)));
                        break;
                    case "/":
                        stack.Pop();
                        var t7 = stack.Pop();
                        var t8 = stack.Pop();
                        Console.WriteLine($"{t8} * {t7}");
                        stack.Push(Convert.ToString(int.Parse(t8) * int.Parse(t7)));
                        break;
                    default:
                        break;
                }
            }

            
            Console.WriteLine(stack.Pop());
        }
    }
}
