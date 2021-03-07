using System;
using System.Collections.Generic;
using System.Text;

namespace Tanks2D.Model
{
    public class MapElement : Entity<int>
    {
        public float XPos { get; set; }
        public float YPos { get; set; }
    }
}
