using System.Collections.Generic;

namespace Tanks2D.Model
{
    public class Tank : MapElement
    {
        private readonly ICollection<TankTurret> _turrets;
        private readonly TankBody _body;
    }
}
