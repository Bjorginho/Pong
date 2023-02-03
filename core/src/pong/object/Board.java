package pong.object;

public class Board {

    private int width, height, wallHeight;

    public Board(int width, int height, int wallHeight) {
        this.width = width;
        this.height = height;
        this.wallHeight = wallHeight;

    }

    public int getWidth() {
        return width;
    }

    public int getTop(){
        return height - wallHeight;
    }

    public int getBot(){
        return wallHeight;
    }

}
