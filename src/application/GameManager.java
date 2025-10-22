package application;

import gameobject.core.GameObject;
import gameobject.dynamic.Ball;
import gameobject.dynamic.Paddle;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static GameManager instance;

    public enum GameState {
        MENU, PLAYING, PAUSED, LEVEL_COMPLETE, GAME_OVER
    }

    private GameState currentState;
    private int score;
    private int lives;
    private int currentLevel;

    // Danh sách các đối tượng trong game
    private Paddle paddle;
    private Ball ball;
    private List<GameObject> gameObjects = new ArrayList<>();

    private GameManager() {}

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Khởi tạo một màn chơi mới
    public void startGame() {
        this.score = 0;
        this.lives = Config.INITIAL_LIVES;
        this.currentLevel = 1;
        this.currentState = GameState.PLAYING;
        LevelManager.getInstance().loadLevel(currentLevel);
    }

    // Được gọi liên tục bởi Game Loop (AnimationTimer trong GameUIController)
    public void update() {
        if (currentState != GameState.PLAYING) {
            return;
        }

        // Cập nhật tất cả các đối tượng game (ball, paddle, powerups...)
        for (GameObject obj : new ArrayList<>(gameObjects)) {
            obj.update();
        }

        // Giả sử bạn có lớp CollisionDetector để xử lý va chạm
        // CollisionDetector.checkCollisions(gameObjects);

        // Kiểm tra điều kiện thua (mất bóng)
        if (ball.isOutOfBounds()) {
            lives--;
            if (lives <= 0) {
                currentState = GameState.GAME_OVER;
                SceneManager.getInstance().showGameOverScreen();
            } else {
                resetBallAndPaddle();
            }
        }

        // Kiểm tra điều kiện thắng màn (hết gạch)
        if (LevelManager.getInstance().isLevelComplete()) {
            currentState = GameState.LEVEL_COMPLETE;
            currentLevel++;
            // Có thể thêm logic chuyển level hoặc màn hình chiến thắng
            LevelManager.getInstance().loadLevel(currentLevel);
        }
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
        if (obj instanceof Paddle) this.paddle = (Paddle) obj;
        if (obj instanceof Ball) this.ball = (Ball) obj;
    }

    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    public void resetBallAndPaddle() {
        // Logic để đặt lại vị trí bóng và paddle sau khi mất mạng
        ball.resetPosition();
        paddle.resetPosition();
    }

    public void clearGameObjects() {
        gameObjects.clear();
    }

    // Getters and Setters
    public GameState getCurrentState() { return currentState; }
    public void setCurrentState(GameState state) { this.currentState = state; }
    public int getScore() { return score; }
    public void addScore(int points) { this.score += points; }
    public int getLives() { return lives; }
}
