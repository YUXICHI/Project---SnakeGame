package Game;

import LinkedList.*;


public class Snake {
    private LinkedList snake = new LinkedList();
    private Direction current_direction = Direction.Up;

    public Snake() {
        snake.pushBack(new Point(3, 5));
        snake.pushBack(new Point(3, 6));
        snake.pushBack(new Point(3, 7));
        snake.pushBack(new Point(3, 8));
        snake.pushBack(new Point(3, 9));
    }

    public void move() {
        Point pt = new Point((Point) snake.front());

        switch (this.current_direction) {
            case Up:
                pt.y--;
                break;

            case Down:
                pt.y++;
                break;

            case Left:
                pt.x--;
                break;

            case Right:
                pt.x++;
                break;
        }

        snake.pushFront(pt);
        snake.popBack();
    }

    public void eat() {
        Point pt = new Point((Point) snake.front());

        switch (this.current_direction) {
            case Up:
                pt.y--;
                break;

            case Down:
                pt.y++;
                break;

            case Left:
                pt.x--;
                break;

            case Right:
                pt.x++;
                break;
        }

        snake.pushFront(pt);
    }

    public void setDirection(Direction d) {
        this.current_direction = d;
    }

    public Direction getDirection(){
        return this.current_direction;
    }

    public LinkedListNode head() {
        return snake.begin();
    }

    public int size() {
        return this.snake.count();
    }

    public boolean contains(Point pt) {
        LinkedListNode node = snake.begin();
        while (node != null) {
            if (node.data.equals(pt)) {
                return true;
            }
            node = node.next;
        }

        return false;
    }
}

