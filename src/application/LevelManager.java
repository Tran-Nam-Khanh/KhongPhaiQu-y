package application;

import gameobject.core.Brick;
// Giả sử bạn có lớp BrickMapLoader trong package level
import level.BrickMapLoader;
import java.util.List;
import java.util.stream.Collectors;

public class LevelManager {

    private static LevelManager instance;
    private int currentLevelNumber;

    private LevelManager() {}

    public static synchronized LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public void loadLevel(int levelNumber) {
        this.currentLevelNumber = levelNumber;
        GameManager gameManager = GameManager.getInstance();

        // Xóa các đối tượng cũ trước khi tải level mới
        gameManager.clearGameObjects();

        // Giả sử BrickMapLoader.load trả về một List<Brick>
        List<Brick> bricks = BrickMapLoader.load(levelNumber);

        // Thêm tất cả gạch vào GameManager
        for (Brick brick : bricks) {
            gameManager.addGameObject(brick);
        }

        // Tạo và thêm paddle, ball vào màn chơi
        // gameManager.addGameObject(new Paddle());
        // gameManager.addGameObject(new Ball());

        System.out.println("Đã tải thành công Level " + levelNumber);
    }

    public boolean isLevelComplete() {
        // Level hoàn thành khi không còn viên gạch nào có thể bị phá hủy
        // Giả sử UnbreakableBrick có một phương thức để kiểm tra
        return GameManager.getInstance().getGameObjects().stream()
                .filter(obj -> obj instanceof Brick)
                .noneMatch(brick -> ((Brick) brick).isBreakable());
    }

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }
}
