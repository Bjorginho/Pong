package pong.object;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pong.model.PongModel;
import pong.util.Direction;

public class Ball {
    private int x, y;
    private final int radius, startX, startY;
    private Direction direction;
    private final float speed;
    private final Random r;
    PongModel model;
    private Board board;

    public Ball(PongModel model, Board board, int x, int y, float speed) {
        this.model = model;
        this.board = board;
        this.startX = x;
        this.startY = y;
        this.speed = speed;

        this.r = new Random();
        this.radius = 10;
        initialize();
    }
    private void initialize() {
        this.x = startX;
        this.y = startY;
        boolean startEast = r.nextBoolean();
        direction = startEast ? Direction.EAST : Direction.WEST;
    }

    public void update(){
        if (getTopY() >= board.getTop() || getBotY() <= board.getBot()){
            collideWithWall();
        } else if (model.ballPaddleCollision()) {
            collideWithPaddle();
        }
        move();
    }

    private void move() {
        switch (direction){
            case NORTH_EAST:
                x += speed;
                y += speed;
                break;
            case EAST:
                x += speed;
                break;
            case SOUTH_EAST:
                y -= speed;
                x += speed;
                break;
            case SOUTH_WEST:
                y -= speed;
                x -= speed;
                break;
            case WEST:
                x -= speed;
                break;
            case NORTH_WEST:
                y += speed;
                x -= speed;
                break;
        }
    }

    public void collideWithPaddle(){
        List<Direction> options = null;
        switch (direction){
            case NORTH_EAST:
            case EAST:
            case SOUTH_EAST:
                options = Arrays.asList(Direction.WEST, Direction.SOUTH_WEST, Direction.NORTH_WEST);
                break;
            case SOUTH_WEST:
            case WEST:
            case NORTH_WEST:
                options = Arrays.asList(Direction.EAST, Direction.SOUTH_EAST, Direction.NORTH_EAST);
                break;
        }
        int idx = r.nextInt(3);
        direction = options.get(idx);

    }

    private void collideWithWall() {
        switch (direction){ // Ball cannot go NORTH or SOUTH
            case NORTH_EAST:
                direction = Direction.SOUTH_EAST;
                break;
            case SOUTH_EAST:
                direction = Direction.NORTH_EAST;
                break;
            case SOUTH_WEST:
                direction = Direction.NORTH_WEST;
                break;
            case NORTH_WEST:
                direction = Direction.SOUTH_WEST;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getTopY(){
        return this.y + radius;
    }

    public int getBotY(){
        return this.y - radius;
    }

    public int getLeftX(){
        return this.x - radius;
    }

    public int getRightX(){
        return this.x + radius;
    }

    public void resetPosition(){
        initialize();
    }
}
