using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using static System.Console;

namespace B2002
{
    static class Program
    {
        static void Main(string[] args)
        {
            var n = ReadLine().TryParse<int>();
            var list = Enumerable.Range(1, n).Select(x => ReadLine()?.ToList()).ToList();
            var data = new List<List<int>>(new List<List<int>>(n + 10));
            int i = 1;
            foreach (var line in list)
            {
                var w = line.Count(x => x == 'W');
                var d = line.Count(x => x == 'D');
                var l = line.Count(x => x == 'L');
                var t = (2 * w) + d;
                data.Add(new List<int>(){i, t, w, d, l});
                i++;
            }

            var max = data.OrderByDescending(x => x[1]).FirstOrDefault();
            WriteLine($"{max[0]} {max[1]} {max[2]} {max[3]} {max[4]}");
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
    }
}
