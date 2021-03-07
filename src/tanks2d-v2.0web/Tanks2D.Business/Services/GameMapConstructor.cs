using Tanks2D.Core.Interfaces.Services;
using Tanks2D.Model;

namespace Tanks2D.Business.Services
{
    public class GameMapConstructor : IGameMapConstructor
    {
        private readonly int _gameLevel;

        public GameMapConstructor(int gameLevel)
        {
            _gameLevel = gameLevel;
        }

        public GameMap InitializeMap()
        {
            var map = new GameMap();

            return map;
        }
    }
}
