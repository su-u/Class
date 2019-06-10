using System;
using System.Linq;

namespace ALDS1_11_A
{
    class Program
    {
        static void Main(string[] args)
        {
            var n = int.Parse(Console.ReadLine());

            var graph = new int[n, n];

            for (int i = 0; i < n; i++)
            {
                var line = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
                for (int j = 2; j < line?.Length; j++)
                {
                    graph[line[0] - 1, line[j] - 1] = 1;
                    
                    //無向グラフの場合
                    graph[line[j] - 1, line[0] - 1] = 1;
                }
            }

            for (int i = 0; i < graph.GetLength(0); i++)
            {
                for (int j = 0; j < graph.GetLength(1); j++)
                {
                    Console.Write($"{graph[i, j]} ");
                }
                Console.WriteLine();
            }
        }
    }
}
