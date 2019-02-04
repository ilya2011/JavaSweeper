package sweeper;

public class Bomb {

    private Matrix bombMap; //матрица с бомбами
    private int totalBombs; //количество бомб

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start() { //расстановка бомб
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++) {
            placeBomb();
        }
    }

    Box get(Coord coord) { //где находятся бомбы
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb() { //размещение бомб
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord)) {
                continue;
            }
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
