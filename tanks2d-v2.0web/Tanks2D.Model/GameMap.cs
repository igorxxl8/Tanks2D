using System.Collections.Generic;

namespace Tanks2D.Model
{
    public class GameMap : Entity<int>
    {
        public ICollection<MapElement> Elements { get; }

        public GameMap()
        {
            Elements = new List<MapElement>();
        }
    }
}
