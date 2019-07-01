using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
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

            string ans = list[0];
            for (int i = 0; i < list.Count - 1; i++)
            {
                int max = -1;
                string s = list[i];
                for (int j = 0; j < list[i].Length || j < s.Length; j++)
                {
                }

                WriteLine(ans.Length - max - 1);
                if (max != -1)
                {
                    ans = ans.Substring(0, ans.Length - max - 1) + list[i + 1];
                }
                else
                {
                    ans += list[i + 1];
                }

            }
            WriteLine(ans);


        }

        private static string StringDisc(string s1, string s2)
        {
            for (int i = s1.Length - 1; i > 0; i++)
            {
                for (int j = 0; j < s2.Length; j++)
                {
                    if(s1[i] == s2[j])
                }
            }


            return "";
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
