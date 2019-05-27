using System;
using System.Collections.Generic;
using System.Text;

namespace Poker
{
    public class Deck : Cards
    {
        private Cards cards;
        public string Name { get; private set; }

        public Deck(string _name)
        {
            this.Name = _name;

        }
        public Card RemoveFirst()
        {
            return cards.RemoveAt(0);
        }

        public Card GetNextCard()
        {
            return cards.RemoveAt(0);
        }


    }
}
