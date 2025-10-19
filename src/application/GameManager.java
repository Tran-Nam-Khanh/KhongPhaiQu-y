package application;

import gameobject.*; // @TODO: Import các lớp đối tượng game của Khánh
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import level.LevelManager;
import ui.GameUIController; // Import controller của Huy
import utils.BackgroundManager;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private Stage stage;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;

    // Các thành phần quản lý
    private LevelManager levelManager;
    private SoundManager soundManager;
    private BackgroundManager backgroundManager;
    private GameUIController gameUIController; // Tham chiếu tới controller của Huy

    // Trạng thái game
    private int currentLevel;
    private int score;
    private int lives;
    private boolean isRunning = false;

    // Danh sách các đối tượng trong game
    // @TODO: Sử dụng các lớp đối tượng do Khánh code
    private Paddle paddle;
    private List<Ball> balls = new ArrayList<>();
    private List<Brick> bricks = new ArrayList<>();

    public GameManager(Stage stage, GraphicsContext gc, GameUIController gameUIController) {
        this.stage = stage;
        this.gc = gc;
        this.gameUIController = gameUIController;

        this.levelManager = new LevelManager();
        this.soundManager = new SoundManager();
        this.backgroundManager = new BackgroundManager();
    }

    /**
     * bắt đầu trò chơi
     */
    public void startGame(Scene scene) {
        currentLevel = 1;
        score = 0;
        lives = Config.INITIAL_LIVES;
        isRunning = true;

        // Cập nhật giao diện của Huy
        gameUIController.updateLevel(currentLevel);
        gameUIController.updateLives(lives);

        loadLevel(currentLevel);
        handleKeyPress(scene);

        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / Config.TARGET_FPS) {
                    update();
                    render();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
        soundManager.playBackgroundMusic();
    }
    /**
     * Điều khiển thay đổi khi nhấn các nút trên bàn phims
     */
    private void handleKeyPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    paddle.moveLeft();
                    break;
                case RIGHT:
                    paddle.moveRight();
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                case RIGHT:
                    paddle.stop();
                    break;
                default:
                    break;
            }
        });
    }
    /**
     * hàm hiển thị khi bắt đầu một màn mới
     */
    private void loadLevel(int levelNumber) {

        bricks = LevelManager.loadLevel(levelNumber);

        double paddleStartX = (Config.SCREEN_WIDTH - Config.PADDLE_WIDTH) / 2;
        paddle = new Paddle(paddleStartX, Config.PADDLE_Y_POSITION, Config.PADDLE_WIDTH, Config.PADDLE_HEIGHT);

        balls.clear();
        double ballStartX = paddle.getX() + (paddle.getWidth() / 2);
        double ballStartY = paddle.getY() - Config.BALL_RADIUS;
        balls.add(new Ball(ballStartX, ballStartY, Config.BALL_RADIUS));

        gameUIController.updateLevel(levelNumber);
    }
    /**
     * Ham cap nhat trang thai khi choi game
     */
    private void update() {
        if (!isRunning) return;

        paddle.update();
        for (Ball ball : balls) {
            ball.update();
        }

        java.util.Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            if (CollisionDetector.checkCollision(ball, paddle)) {
                ball.reverseY();
                soundManager.playSoundEffect("paddle_hit.wav");
            }
            java.util.Iterator<Brick> brickIterator = bricks.iterator();
            while (brickIterator.hasNext()) {
                Brick brick = brickIterator.next();
                if (CollisionDetector.checkCollision(ball, brick)) {

                    double overlapX = (ball.getWidth() / 2 + brick.getWidth() / 2) - Math.abs(ball.getCenterX() - brick.getCenterX());
                    double overlapY = (ball.getHeight() / 2 + brick.getHeight() / 2) - Math.abs(ball.getCenterY() - brick.getCenterY());

                    if (overlapX < overlapY) {
                        ball.reverseX();
                    } else {
                        ball.reverseY();
                    }
                    brick.takeDamage();
                    if (brick.isDestroyed()) {
                        brickIterator.remove();
                        score += 10;
                        gameUIController.updateScore(score);
                    }
                    soundManager.playSoundEffect("brick_hit.wav");
                    break;
                }
            }
            if (ball.getY() > Config.SCREEN_HEIGHT) {
                ballIterator.remove();
            }
        }
        if (balls.isEmpty()) {
            loseLife();
        }
        if (bricks.isEmpty()) {
            nextLevel();
        }
    }
    /**
     * Ham in man hinh
     */
    private void render() {
        gc.clearRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        gc.drawImage(backgroundManager.getBackground(currentLevel), 0, 0);

        if (paddle != null) {
            paddle.render(gc);
        }

        for (Brick brick : bricks) {
            brick.render(gc);
        }

        for (Ball ball : balls) {
            ball.render(gc);
        }

        for (PowerUp powerUp : powerUps) {
             powerUp.render(gc);
        }
    }
    /**
     * Ham tru mang neu du dieu kien
     */
    private void loseLife() {
        lives--;
        soundManager.playSoundEffect("lose_life.wav");
        gameUIController.updateLives(lives);
        if (lives <= 0) {
            gameOver();
        } else {
            resetPaddleAndBall();
        }
    }

    /**
     * Ham tra ve vi tri cu neu con mang
     */
    private void resetPaddleAndBall() {
        double paddleStartX = (Config.SCREEN_WIDTH - Config.PADDLE_WIDTH) / 2;
        paddle.setX(paddleStartX);
        paddle.setY(Config.PADDLE_Y_POSITION);
        balls.clear();
        double ballStartX = paddle.getX() + (paddle.getWidth() / 2);
        double ballStartY = paddle.getY() - Config.BALL_RADIUS;
        balls.add(new Ball(ballStartX, ballStartY, Config.BALL_RADIUS));
    }

    private void nextLevel() {
        currentLevel++;
        if (currentLevel > 5) {
            winGame();
        } else {
            loadLevel(currentLevel);
        }
    }

    private void gameOver() {
        isRunning = false;
        gameLoop.stop();
        soundManager.stopBackgroundMusic();
        SceneManager.goToGameOver(stage);
    }

    private void winGame() {
        isRunning = false;
        gameLoop.stop();
        soundManager.stopBackgroundMusic();
        SceneManager.goToWinScreen(stage);
    }
}
