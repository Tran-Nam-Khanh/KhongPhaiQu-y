package application;

import gameobject.core.Brick;
import gameobject.dynamic.Ball;
import gameobject.dynamic.Paddle;
import level.BrickMapLoader;

import java.util.List;

/**
 * Lớp Singleton xử lý việc tải và chuyển tiếp các màn chơi.
 */
public class LevelManager {

    private static LevelManager instance;
    private int currentLevel = 1;
    private static final int MAX_LEVELS = 4;

    private LevelManager() {}

    public static synchronized LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public void loadLevel(int levelNumber) {
        this.currentLevel = levelNumber;
        GameManager gm = GameManager.getInstance();

        gm.clearGameObjects();

        List<Brick> bricks = BrickMapLoader.load(levelNumber);
        bricks.forEach(gm::addGameObject);

        gm.addGameObject(new Paddle());
        gm.addGameObject(new Ball());

        StoryManager.getInstance().showStoryForLevel(levelNumber);
    }

    public void loadNextLevel() {
        if (currentLevel < MAX_LEVELS) {
            currentLevel++;
            loadLevel(currentLevel);
        } else {
            GameManager.getInstance().setCurrentState(GameManager.GameState.GAME_OVER);
            SceneManager.getInstance().showGameOverScene(GameManager.getInstance().getScore());
        }
    }
}
