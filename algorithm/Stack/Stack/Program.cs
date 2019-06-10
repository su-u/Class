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
            Stack<int> stack = new Stack<int>();

            stack.Push(1);

            Console.WriteLine(stack.Peek());
            
            Console.WriteLine(stack.Pop());
            Console.WriteLine(stack.Count);
        }
    }
}
