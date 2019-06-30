using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using static System.Console;
//using System.Numerics;
using System.Text.RegularExpressions;

namespace Paiza4
{
    static class Program
    {
        static void Main(string[] args)
        {
            var n = ReadLine().TryParse<uint>();
            ulong a = 1;
            for (uint i = n; i > 0; i--)
            {
                a *= i;
            }
            var strA = a.ToString();
            WriteLine(strA);
            var aa = Regex.Replace(strA,"[0]*$", "");
            var aa1 = Regex.Match(aa, ".{0,9}$");
            var aa2 = Regex.Match(aa1.Value, "^*[^0].*");
            WriteLine(aa2.Value);
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
