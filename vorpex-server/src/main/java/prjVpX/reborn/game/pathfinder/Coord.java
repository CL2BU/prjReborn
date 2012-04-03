package prjVpX.reborn.game.pathfinder;

public class Coord
    {
        public int X;
        public int Y;
        
        public int distanceToUserPos;
        public int distanceToUserPos2;

        public Coord(int x, int y)
        {
            X = x;
            Y = y;
            distanceToUserPos = 1000;
            distanceToUserPos2 = 1000;
        }
    }