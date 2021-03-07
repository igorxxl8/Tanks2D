using System;
using Tanks2D.Core.Interfaces.Services;
using Tanks2D.Model;

namespace Tanks2D.Business
{
    public class GameMapService : IGameMapService
    {
        private readonly GameMap _map;

        public GameMapService(IGameMapConstructor constructor)
        {
            _map = constructor.InitializeMap();
        }
    }
}
