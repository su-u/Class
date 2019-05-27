using System;
using System.Collections.Generic;
using System.Text;

namespace Poker
{
    public class Card
    {
        public int Number { get; private set; }

        public Card(int _num)
        {
            this.Number = _num;
        }

        public String GetString {
            get { 
                int num = (this.Number % 13);
                String result = $"{num}";
                switch (num)
                {
                    case 1:
                        result = "A";
                        break;
                    case 10:
                        result = "0";
                        break;
                    case 11:
                        result = "J";
                        break;
                    case 12:
                        result = "Q";
                        break;
                    case 0:
                        result = "K";
                        break;
                }
                return result;
            }
        }

        public String GetSuitString {
            get {
                int num = (this.Number / 13);
                String result = "";
                switch (num)
                {
                    case 0:
                        result = "S";
                        break;
                    case 1:
                        result = "H";
                        break;
                    case 2:
                        result = "D";
                        break;
                    case 3:
                    case 4:
                        result = "C";
                        break;
                }

                return result + this.GetString;
            }
        }

    }
}
