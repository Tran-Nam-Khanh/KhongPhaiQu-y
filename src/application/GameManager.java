package application;

import gameobject.core.Brick;
import gameobject.core.GameObject;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static GameManager instance;

    public enum GameState {
        MENU, PLAYING, PAUSED, GAME_OVER, LEVEL_TRANSITION
    }

    private GameState currentState;
    private int score;
    private int lives;

    private List<GameObject> gameObjects;

    private GameManager() {
        this.gameObjects = new ArrayList<>();
    }

    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        this.score = 0;
        this.lives = Config.INITIAL_LIVES;
        this.currentState = GameState.PLAYING;
        LevelManager.getInstance().loadLevel(1);
    }

    public void update() {
        if (currentState != GameState.PLAYING) {
            return;
        }

        // Cập nhật tất cả các đối tượng game
        new ArrayList<>(gameObjects).forEach(GameObject::update);

        // Kiểm tra xem màn chơi đã hoàn thành chưa
        if (isLevelComplete()) {
            LevelManager.getInstance().loadNextLevel();
        }
    }

    public void loseLife() {
        this.lives--;
        if (this.lives <= 0) {
            this.currentState = GameState.GAME_OVER;
            SceneManager.getInstance().showGameOverScene(this.score);
        } else {
            Paddle paddleToReset = null;
            Ball ballToReset = null;

            for (GameObject obj : gameObjects) {
                if (obj instanceof Paddle) {
                    paddleToReset = (Paddle) obj;
                }
                if (obj instanceof Ball) {
                    ballToReset = (Ball) obj;
                }
            }

            if (paddleToReset != null && ballToReset != null) {
                // Tính toán vị trí mới và đặt lại cho thanh đỡ (ở giữa màn hình)
                double paddleNewX = (Config.SCREEN_WIDTH - Config.PADDLE_WIDTH) / 2.0;
                paddleToReset.setX(paddleNewX);

                // Tính toán vị trí mới và đặt lại cho bóng (ở giữa, ngay trên thanh đỡ)
                double ballNewCenterX = paddleNewX + (Config.PADDLE_WIDTH / 2.0);
                double ballNewCenterY = Config.PADDLE_Y_POSITION - Config.BALL_RADIUS;
                ballToReset.setPosition(ballNewCenterX, ballNewCenterY);
                // Đặt lại vận tốc cho bóng để nó bay lên
                ballToReset.resetVelocity();
            }
        }
    }

    private boolean isLevelComplete() {
        return gameObjects.stream()
                .filter(obj -> obj instanceof Brick)
                .map(obj -> (Brick) obj)
                .noneMatch(Brick::isBreakable);
    }

    // Các phương thức quản lý đối tượng game
    public void addGameObject(GameObject obj) {
        this.gameObjects.add(obj);
    }
    public void removeGameObject(GameObject obj) {
        this.gameObjects.remove(obj);
    }
    public void clearGameObjects() {
        this.gameObjects.clear();
    }
    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    // Getters và Setters
    public GameState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(GameState state) {
        this.currentState = state;
    }
    public int getScore() {
        return score;
    }
    public void addScore(int points) {
        this.score += points;
    }
    public int getLives() {
        return lives;
    }
}
