using System;
using System.Collections.Generic;
using System.Linq;

namespace ALDS
{
    class Program
    {

        private static Stack<int> stack;
        private static int[,] graph;
        private static int[] state;



        //深さ優先探索
        static void Main(string[] args)
        {
            var n = int.Parse(Console.ReadLine());

            graph = new int[n, n];
            state = new int[n];


            for (int i = 0; i < n; i++)
            {
                var line = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
                for (int j = 2; j < line?.Length; j++)
                {
                    graph[line[0] - 1, line[j] - 1] = 1;

                    //無向グラフの場合
                    //graph[line[j] - 1, line[0] - 1] = 1;
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

        private static void Init()
        {
            for (int i = 0; i < state.Length; i++)
            {
                state[i] = 0;
            }
            
        }

        private static void Dts(int point)
        {
            stack.Push(point);
            state[point] = 1;

        }

    }
}
