using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

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
            if (this.cards.Count >= 1) c = this.cards[0];
            this.cards.RemoveAt(0);
            return c;
        }

    }
}
