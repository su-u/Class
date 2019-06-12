using System;
using System.Collections.Generic;
using System.Linq;

namespace ALDS
{
    class Program
    {
        //深さ優先探索
        static void Main(string[] args)
        {

            var n = int.Parse(Console.ReadLine());
            var g = new Dfs(n);


            for (int i = 0; i < n; i++)
            {
                var line = Console.ReadLine()?.Split().Select(int.Parse).ToArray();
                for (int j = 2; j < line?.Length; j++)
                {
                    g.Graph[line[0] - 1, line[j] - 1] = 1;

                    //無向グラフの場合
                    //graph[line[j] - 1, line[0] - 1] = 1;
                }
            }

            g.DfsStart();
            g.PrintTime();
        }
    }

    public class Dfs
    {
        public int[,] Graph { set; get; }
        private int[] State;
        private int[] D;
        private int[] F;
        private int[] nt;
        private int n;

        public Dfs(int n)
        {
            this.n = n;
            this.Graph = new int[n, n];
            this.State = new int[n];
            this.D = new int[n];
            this.F = new int[n];
            this.nt = new int[n];

            for (int i = 0; i < this.State.Length; i++)
            {
                this.State[i] = 0;
                this.nt[i] = 0;
            }
        }

        private int Next(int u)
        {
            for(int v = nt[u]; v < this.n; v++)
            {
                this.nt[u] = v + 1;
                if (Convert.ToBoolean(this.Graph[u, v])) return v;
            }
            return -1;
        }

        public void DfsStart()
        {
            for(int u = 0;u < n; u++)
            {
                if (this.State[u] == 0) this.DfsVisit(u);
            }

        }


        private void DfsVisit(int point)
        {
            Stack<int> Stack = new Stack<int>();

            int time = 0;
            Stack.Push(point);
            this.State[point] = 1;
            this.D[point] = ++time;

            while (Stack.Count > 0)
            {
                int u = Stack.Peek();
                int v = this.Next(u);
                if (v != -1)
                {
                    if (this.State[v] == 0)
                    {
                        this.State[v] = 1;
                        this.D[v] = ++time;
                        Stack.Push(v);
                    }

                }
                else
                {
                    Stack.Pop();
                    this.State[u] = 2;
                    this.F[u] = ++time;
                }
            }
            

        }

        public void Print()
        {
            //表示
            for (int i = 0; i < this.Graph.GetLength(0); i++)
            {
                for (int j = 0; j < this.Graph.GetLength(1); j++)
                {
                    Console.Write($"{this.Graph[i, j]} ");
                }
                Console.WriteLine();
            }
        }

        public void PrintTime()
        {
            for(int i = 0; i < D.Length; i++)
            {
                Console.WriteLine($"{i + 1} {D[i]} {F[i]}");
            }
        }
    }
}
