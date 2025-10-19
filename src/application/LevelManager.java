package application;

import gameobject.Brick; // @TODO: Import từ package của Khánh
import level.*; // Import các màn chơi của bạn

import java.util.List;

public class LevelManager {
    private BrickMapLoader brickMapLoader;

    public LevelManager() {
        this.brickMapLoader = new BrickMapLoader();
    }

    public List<Brick> loadLevel(int levelNumber) {
        Level currentLevel;
        switch (levelNumber) {
            case 1:
                currentLevel = new Level1();
                break;
            case 2:
                currentLevel = new Level2();
                break;
            // @TODO: Thêm các case cho Level3, Level4, Boss
            default:
                // Màn chơi mặc định hoặc xử lý khi hết màn
                currentLevel = new Level1();
        }

        // Dùng BrickMapLoader để chuyển layout từ mảng số nguyên thành danh sách đối tượng Brick
        return brickMapLoader.createBrickList(currentLevel.getBrickLayout());
    }
}
