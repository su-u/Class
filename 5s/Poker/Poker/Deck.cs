using System;
using System.Collections.Generic;
using System.Text;

namespace Poker
{
    public class Deck : Cards
    {
        public string Name { get; private set; }

        public Deck(string _name)
        {
            this.Name = _name;

        }
        public Card RemoveFirst()
        {
            return RemoveAt(0);
        }

        public Card GetNextCard()
        {
            return RemoveAt(0);
        }

        public void Sort()
        {
            this.cards.Sort();
        }


    }
}
