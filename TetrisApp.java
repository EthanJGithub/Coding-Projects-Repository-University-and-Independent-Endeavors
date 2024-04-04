import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TetrisApp extends Application {

    static final int TILE_SIZE = 30;
    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 20;

    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
    private Pane root = new Pane();
    private List<Tetromino> tetrominos = new ArrayList<>();
    private Tetromino currentTetromino;
    private Random random = new Random();
    private long lastUpdate = 0;
    private int score = 0;
    private int level = 1;

    private Parent createContent() {
        root.setPrefSize(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.GRAY);
                tile.setX(x * TILE_SIZE);
                tile.setY(y * TILE_SIZE);
                root.getChildren().add(tile);
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN:
                    currentTetromino.moveDown();
                    break;
                case LEFT:
                    currentTetromino.moveLeft();
                    break;
                case RIGHT:
                    currentTetromino.moveRight();
                    break;
                case UP:
                    currentTetromino.rotate();
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tetris");
        primaryStage.show();

        spawnTetromino();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 500_000_000 / level) {
                    currentTetromino.moveDown();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    private void spawnTetromino() {
        Tetromino tetromino = new Tetromino(this);
        tetromino.setTranslateX((GRID_WIDTH / 2) * TILE_SIZE);
        tetromino.setTranslateY(0);
        root.getChildren().add(tetromino);
        currentTetromino = tetromino;
    }

    boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT;
    }

    boolean isOccupied(int x, int y) {
        return isWithinBounds(x, y) && grid[x][y] != 0;
    }

    void addToGrid(int x, int y, int color) {
        if (isWithinBounds(x, y)) {
            grid[x][y] = color;
        }
    }

    private void checkRows() {
        for (int y = GRID_HEIGHT - 1; y >= 0; y--) {
            boolean isRowFull = true;
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (grid[x][y] == 0) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                clearRow(y);
                y++;
            }
        }
    }

    private void clearRow(int row) {
        for (int y = row; y > 0; y--) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                grid[x][y] = grid[x][y - 1];
            }
        }
        for (int x = 0; x < GRID_WIDTH; x++) {
            grid[x][0] = 0;
        }
        score += 100;
        if (score % 1000 == 0) {
            level++;
        }
    }

    boolean isValidMove(int[][] shape, int newX, int newY) {
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    int currentX = newX + x;
                    int currentY = newY + y;
                    if (!isWithinBounds(currentX, currentY) || isOccupied(currentX, currentY)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void mergeTetromino(int[][] shape, int color) {
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    int currentX = (int) (currentTetromino.getTranslateX() / TILE_SIZE) + x;
                    int currentY = (int) (currentTetromino.getTranslateY() / TILE_SIZE) + y;
                    addToGrid(currentX, currentY, color);
                }
            }
        }
        root.getChildren().remove(currentTetromino);
        tetrominos.add(currentTetromino);
        checkRows();
        spawnTetromino();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Tetromino extends Pane {

    private int[][] shape;
    private TetrisApp game;
    private Color color;

    Tetromino(TetrisApp game) {
        this.game = game;
        shape = Tetrominoes.getRandomShape();
        color = Tetrominoes.getRandomColor();
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                if (shape[y][x] != 0) {
                    Rectangle tile = new Rectangle(TetrisApp.TILE_SIZE, TetrisApp.TILE_SIZE, color);
                    tile.setTranslateX(x * TetrisApp.TILE_SIZE);
                    tile.setTranslateY(y * TetrisApp.TILE_SIZE);
                    getChildren().add(tile);
                }
            }
        }
    }

    void moveDown() {
        if (game.isValidMove(shape, (int) (getTranslateX() / TetrisApp.TILE_SIZE), (int) (getTranslateY() / TetrisApp.TILE_SIZE) + 1)) {
            setTranslateY(getTranslateY() + TetrisApp.TILE_SIZE);
        } else {
            game.mergeTetromino(shape, Tetrominoes.getColorIndex(color));
        }
    }

    void moveLeft() {
        if (game.isValidMove(shape, (int) (getTranslateX() / TetrisApp.TILE_SIZE) - 1, (int) (getTranslateY() / TetrisApp.TILE_SIZE))) {
            setTranslateX(getTranslateX() - TetrisApp.TILE_SIZE);
        }
    }

    void moveRight() {
        if (game.isValidMove(shape, (int) (getTranslateX() / TetrisApp.TILE_SIZE) + 1, (int) (getTranslateY() / TetrisApp.TILE_SIZE))) {
            setTranslateX(getTranslateX() + TetrisApp.TILE_SIZE);
        }
    }

    void rotate() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape[y].length; x++) {
                rotatedShape[x][shape.length - 1 - y] = shape[y][x];
            }
        }
        if (game.isValidMove(rotatedShape, (int) (getTranslateX() / TetrisApp.TILE_SIZE), (int) (getTranslateY() / TetrisApp.TILE_SIZE))) {
            shape = rotatedShape;
            getChildren().clear();
            for (int y = 0; y < shape.length; y++) {
                for (int x = 0; x < shape[y].length; x++) {
                    if (shape[y][x] != 0) {
                        Rectangle tile = new Rectangle(TetrisApp.TILE_SIZE, TetrisApp.TILE_SIZE, color);
                        tile.setTranslateX(x * TetrisApp.TILE_SIZE);
                        tile.setTranslateY(y * TetrisApp.TILE_SIZE);
                        getChildren().add(tile);
                    }
                }
            }
        }
    }
}

class Tetrominoes {
    private static final int[][][] SHAPES = {
            // I
            {
                    {1, 1, 1, 1}
            },
            // J
            {
                    {1, 0, 0},
                    {1, 1, 1}
            },
            // L
            {
                    {0, 0, 1},
                    {1, 1, 1}
            },
            // O
            {
                    {1, 1},
                    {1, 1}
            },
            // S
            {
                    {0, 1, 1},
                    {1, 1, 0}
            },
            // T
            {
                    {0, 1, 0},
                    {1, 1, 1}
            },
            // Z
            {
                    {1, 1, 0},
                    {0, 1, 1}
            }
    };

    private static final Color[] COLORS = {
            Color.CYAN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.PURPLE,
            Color.RED
    };

    static int[][] getRandomShape() {
        return SHAPES[new Random().nextInt(SHAPES.length)];
    }

    static Color getRandomColor() {
        return COLORS[new Random().nextInt(COLORS.length)];
    }

    static int getColorIndex(Color color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (color.equals(COLORS[i])) {
                return i + 1; // Add 1 since 0 is reserved for empty cell
            }
        }
        return 0;
    }
}
