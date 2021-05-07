package Game;

import java.util.Random;

public class Game {
    private Snake snake = new Snake();
    private int map_width;
    private int map_height;
    private Point food = new Point();
    int score = 0;
    private Direction user_input_direction = Direction.Up;
    private boolean gameover = false;

    public Game(int width, int height) {
        this.map_width = width;
        this.map_height = height;

        this.generateFood();
    }

    private void generateFood() {
        Random random = new Random();
        Point pt = new Point();

        do {
            pt.x = random.nextInt(map_width);
            pt.y = random.nextInt(map_height);
        } while (snake.contains(pt));

        this.food = pt;
    }

    public void setDirection(Direction d) {
        this.user_input_direction = d;
    }

    public void move() {
        if (gameover) {
            return;
        }

        Direction direction = snake.getDirection();
        System.out.println(direction);
        System.out.println(this.user_input_direction);
        if (direction == Direction.Up || direction == Direction.Down) {
            if (this.user_input_direction == Direction.Left || this.user_input_direction == Direction.Right) {
                snake.setDirection(this.user_input_direction);
            }
        } else if (direction == Direction.Left || direction == Direction.Right) {
            if (this.user_input_direction == Direction.Up || this.user_input_direction == Direction.Down) {
                snake.setDirection(this.user_input_direction);
            }
        }

        Point nextLocation = new Point((Point) snake.head().data);
        switch (snake.getDirection()) {
            case Up:
                nextLocation.y--;
                break;

            case Down:
                nextLocation.y++;
                break;

            case Left:
                nextLocation.x--;
                break;

            case Right:
                nextLocation.x++;
                break;
        }

        if (nextLocation.equals(this.food)) {
            snake.eat();
            this.score++;
            this.generateFood();
        } else {
            if (nextLocation.x < 0 || nextLocation.x >= map_width || nextLocation.y < 0 || nextLocation.y >= map_height) {
                this.gameover = true;
            } else {
                if (!snake.contains(nextLocation)) {
                    snake.move();
                } else {
                    this.gameover = true;
                }
            }
        }

    }

    public Snake getSnake() {
        return this.snake;
    }

    public Point getFood() {
        return new Point(this.food);
    }

    public boolean isGameover(){
        return this.gameover;
    }

    public int getScore(){
        return this.score;
    }
}
