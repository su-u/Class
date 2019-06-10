using System;
using System.Collections;
using System.Linq;
using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace Stack
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            //input sample : 1 2 + 3 4 - *
            var line = Console.ReadLine()?.Split();
            var stack = new Stack<string>();

            foreach (var s in line ?? Enumerable.Empty<string>())
            {
                stack.Push(s);

                if(!Regex.IsMatch(stack.Peek(), "[+\\-*/]")) continue;

                var p = stack.Pop();
                var t1 = stack.Pop();
                var t2 = stack.Pop();

                switch (p)
                {
                    case "+":
                        Console.WriteLine($"{t2} + {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) + int.Parse(t1)));
                        break;
                    case "-":
                        Console.WriteLine($"{t2} - {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) - int.Parse(t1)));
                        break;
                    case "*":
                        Console.WriteLine($"{t2} * {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) * int.Parse(t1)));
                        break;
                    case "/":
                        Console.WriteLine($"{t2} * {t1}");
                        stack.Push(Convert.ToString(int.Parse(t2) * int.Parse(t1)));
                        break;
                    default:
                        break;
                }
            }

            
            Console.WriteLine(stack?.Pop());
        }
    }
}
