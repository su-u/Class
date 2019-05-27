using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using System.Collections;

namespace Poker
{
    class Cards
    {
        private List<Card> cards;

        public Cards()
        {
        }

        public void AddCard(int _num) => cards.Add(new Card(_num));
        public void AddCard(params int[] _num)
        {
            foreach(var i in Enumerable.Range(0, _num.Length))
            {
                cards.Add(new Card(_num[i]));
            }
        }

        public Card this[int index] {
            get {
                return cards[index];
            }
        }

        public int GetCardNumber(int index) => cards[index].Number;

        public int GetSize => this.cards.Count;

        public Card GetMaxNumber
        {
            get {
                return this.cards.Max();
            }
        }

        public void Reverse()
        {
            cards.Reverse();
        }

        public int Count => this.cards.Count;

        public Card RemoveAt(int index)
        {
            Card c = null;
            if (this.cards.Count >= 1) c = this.cards[index];
            this.cards.RemoveAt(index);
            return c;
        }

        public void Shuffle()
        {
            this.cards.Shuffle();
        }
    }

    static class Ex
    {
        public static List<T> Shuffle<T>(this List<T> list)
        {

            Random rnd = new System.Random();    // インスタンスを生成
            for (int i = 0; i < list.Count; i++)
            {
                T temp = list[i];
                int randomIndex = rnd.Next(list.Count);
                list[i] = list[randomIndex];
                list[randomIndex] = temp;
            }

            return list;
        }
    }
}
