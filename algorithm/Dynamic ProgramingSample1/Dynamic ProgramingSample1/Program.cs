using System;

namespace Dynamic_ProgramingSample1
{
    class Program
    {
        private static decimal count = 0m;

        private static decimal[] memo;
        //フィボナッチ数列
        static void Main(string[] args)
        {
            const decimal f = 10m;
            memo = new decimal[(int)f];
            MakeFibonacci(f);
            Console.WriteLine(memo[(int)f - 1]);
            Console.WriteLine(count);
        }


        private static decimal Fibonacci(decimal n)
        {
            count++;
            if (n == 0m || n == 1m) return memo[(int)n] = 1m;

            if (memo?[(int) n] != 0) return memo[(int) n];
            return memo[(int)n] = Fibonacci(n - 2m) + Fibonacci(n - 1m);
        }

        private static void MakeFibonacci(decimal n)
        {
            memo[0] = 1;
            memo[1] = 1;

            for (decimal i = 2; i < n; i++)
            {
                memo[(int) i] = memo[(int)i - 2] + memo[(int)i - 1];
            }
        }
    }
}
