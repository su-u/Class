using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using static System.Console;

namespace botchi
{
    static class Program
    {
        static void Main(string[] args)
        {
        }

        public static int Judge(string a1, string b1)
        {
            var a = a1 == "g" ? 0 : a1 == "c" ? 1 : 2;
            var b = b1 == "g" ? 0 : b1 == "c" ? 1 : 2;
            var c = (a - b + 3) % 3;
            if (c == 0)
            {
                return 0;
            }
            else if (c == 2)
            {
                return 1;
            }
            else
            {
                return 2;
            }
            
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
                throw new InvalidCastException(typeof(T) + " is not suported.");
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
    }
}
