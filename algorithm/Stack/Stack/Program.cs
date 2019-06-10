using System;
using System.Collections;
using System.Linq;
using System.Collections.Generic;

namespace Stack
{
    class Program
    {
        private static void Main(string[] args)
        {
            //input sample : 1 2 + 3 4 - *
            var line = Console.ReadLine()?.Split();
            var stack = new Stack<string>();

            foreach (var s in line ?? Enumerable.Empty<string>())
            {
                stack.Push(s);
                switch (stack.Peek())
                {
                    case "+":
                        {
                            stack.Pop();
                            var t1 = stack.Pop();
                            var t2 = stack.Pop();
                            Console.WriteLine($"{t2} + {t1}");
                            stack.Push(Convert.ToString(int.Parse(t2) + int.Parse(t1)));
                            break;
                        }
                    case "-":
                    {
                        stack.Pop();
                        var t1 = stack.Pop();
                        var t2 = stack.Pop();
                        Console.WriteLine($"{t2} - {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) - int.Parse(t1)));
                    }
                        break;
                    case "*":
                    {
                        stack.Pop();
                        var t1 = stack.Pop();
                        var t2 = stack.Pop();
                        Console.WriteLine($"{t2} * {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) * int.Parse(t1)));
                    }
                        break;
                    case "/":
                    {
                        stack.Pop();
                        var t1 = stack.Pop();
                        var t2 = stack.Pop();
                        Console.WriteLine($"{t2} * {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) * int.Parse(t1)));
                    }
                        break;
                    default:
                        break;
                }
            }

            
            Console.WriteLine(stack?.Pop());
        }
    }
}
