package application;

import gameobject.*; // @TODO: Import các lớp đối tượng game của Khánh
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import level.LevelManager; // Import LevelManager của bạn
import utils.BackgroundManager; // Import BackgroundManager của bạn

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private Stage stage;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;

    // Quản lý các thành phần khác
    private LevelManager levelManager;
    private SoundManager soundManager;
    private BackgroundManager backgroundManager;

    // Trạng thái game
    private int currentLevel;
    private int score;
    private int lives;
    private boolean isRunning = false;

    // Danh sách các đối tượng trong game
    // @TODO: Sử dụng các lớp đối tượng do Khánh code
    // private Paddle paddle;
    // private List<Ball> balls;
    // private List<Brick> bricks;
    // private List<PowerUp> powerUps;

    public GameManager(Stage stage) {
        this.stage = stage;
        this.levelManager = new LevelManager();
        this.soundManager = new SoundManager();
        this.backgroundManager = new BackgroundManager();
    }

    public void startGame() {
        // Thiết lập màn hình game
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group(canvas);
        Scene gameScene = new Scene(root);

        stage.setScene(gameScene);

        // Khởi tạo trạng thái game
        currentLevel = 1;
        score = 0;
        lives = Config.INITIAL_LIVES;
        isRunning = true;

        // Tải màn chơi đầu tiên
        loadLevel(currentLevel);

        // Khởi tạo game loop
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

    private void loadLevel(int levelNumber) {
        // @TODO: Sử dụng LevelManager để lấy danh sách gạch cho màn chơi
        // bricks = levelManager.loadLevel(levelNumber);
        // Khởi tạo paddle, ball tại đây
    }

    // Vòng lặp cập nhật logic game
    private void update() {
        if (!isRunning) return;

        // @TODO:
        // 1. Cập nhật vị trí bóng (balls)
        // 2. Cập nhật vị trí vật phẩm (powerUps)
        // 3. Xử lý va chạm (dùng CollisionDetector của Khánh)
        //    - Bóng với tường
        //    - Bóng với paddle
        //    - Bóng với gạch
        //    - Paddle với vật phẩm
        // 4. Kiểm tra điều kiện thắng/thua
        //    - Nếu hết gạch -> thắng màn -> nextLevel()
        //    - Nếu bóng rơi xuống dưới -> mất mạng -> resetBall() hoặc gameOver()
    }

    // Vòng lặp vẽ lại các đối tượng
    private void render() {
        // 1. Vẽ nền
        gc.drawImage(backgroundManager.getBackground(currentLevel), 0, 0);

        // 2. Vẽ các đối tượng game
        // @TODO: Dùng gc.drawImage() hoặc gc.fillRect() để vẽ paddle, ball, bricks, powerups
        // paddle.render(gc);
        // for (Ball ball : balls) ball.render(gc);
        // for (Brick brick : bricks) brick.render(gc);

        // 3. Vẽ thông tin UI (Điểm, Mạng)
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 20, 30);
        gc.fillText("Lives: " + lives, Config.SCREEN_WIDTH - 80, 30);
    }

    private void nextLevel() {
        currentLevel++;
        // @TODO: Kiểm tra xem có phải màn cuối không. Nếu có -> winGame()
        loadLevel(currentLevel);
    }

    private void gameOver() {
        isRunning = false;
        gameLoop.stop();
        soundManager.stopBackgroundMusic();
        // @TODO: Gọi màn hình GameOver.fxml của Huy
        System.out.println("GAME OVER");
    }

    private void winGame() {
        isRunning = false;
        gameLoop.stop();
        // @TODO: Gọi màn hình WinScreen.fxml của Huy
        System.out.println("YOU WIN!");
    }
}
