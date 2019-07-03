using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.IO;
using System.Linq;
using System.Text;
using static System.Console;

namespace B2001
{
    static class Program
    {
        static void Main(string[] args)
        {
            var n = ReadLine().TryParse<int>();
            var list = Enumerable.Range(1, n).Select(x => ReadLine()).ToList();

            

            WriteLine($"ans: {Search(list[0], list[1])}");


        }

        private static int Search(string target, string pattern = "")
        {
            if (pattern == "") return -1;

            var t = pattern[0];
            WriteLine($"search: {t}");
            var tList = target.Select((y, i) => new {index = i, c = y}).Where(x => x.c == t) .ToList();
            if (!tList.Any()) return -1;

            foreach (var VARIABLE in tList)
            {
                WriteLine(VARIABLE.index);
            }


            int result = 0;
            foreach (var i in tList)
            {
                int j = i.index, k = 0;
                while(j < target.Length && k < pattern.Length)
                {
                    WriteLine($"compare: {target[j]} : {pattern[k]}");
                    if (target[j] != pattern[k])
                    {
                        WriteLine("failed");
                        break;
                    }
                    j++;
                    k++;
                }

                if (j == target.Length - 1 || k == pattern.Length - 1) result = i.index;

            }

            return result;
        }


        public static T TryParse<T>(this String input)
        {
            try
            {
                var converter = TypeDescriptor.GetConverter(typeof(T));
                if (converter != null)
                {
                    return (T)converter.ConvertFromString(input);
                }
                else
                {
                    throw new InvalidCastException("");
                }
            }
            catch
            {
                throw new InvalidCastException(typeof(T) + " is not supported.");
            }
        }

        public static List<T> SplitTryParseToList<T>(this String input)
        {
            return input.Split().Select(n => n.TryParse<T>()).ToList();
        }

        public static List<T> ListSwap<T>(this List<T> list, Int32 index1, Int32 index2)
        {
            var t = list[index1];
            list[index1] = list[index2];
            list[index2] = t;
            return list;
        }

        public static List<Tuple<T, int>> DuplicateCount<T>(this IEnumerable<T> list)
        {
            return list
                .GroupBy(i => i)
                .Where(g => g.Any())
                .Select(g => Tuple.Create(g.Key, g.Count()))
                .ToList();
        }
        public static List<Tuple<T, int>> DuplicateSort<T>(this IEnumerable<Tuple<T, int>> list)
        {
            return list.OrderByDescending((x) => x.Item2).ToList();
        }
        public static List<T> ReadLineOne<T>(int n)
        {
            var list = new List<T>();
            foreach (var i in Enumerable.Range(1, n))
            {
                list.Add(Console.ReadLine().TryParse<T>());
            }
            return list;
        }
        public static void PrintAll<T>(this IEnumerable<T> list)
        {
            foreach (var i in list)
            {
                Console.Write($"{i} ");
            }

            Console.WriteLine();
        }

        public static string Reversed(this string s)
        {
            return string.Join("", s.Reverse());
        }
    }
}
