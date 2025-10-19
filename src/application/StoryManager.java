package application;

import com.KhongPhaiQu-y.Arkanoid.ui.StoryDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StoryManager {

    /**
     * Hiển thị một hộp thoại cốt truyện.
     * @param ownerStage Cửa sổ chính của game, để hộp thoại mới hiện lên trên.
     * @param npcImagePath Đường dẫn tới ảnh nhân vật NPC.
     * @param dialogueText Lời thoại cần hiển thị.
     */
    public static void showStoryDialog(Stage ownerStage, String npcImagePath, String dialogueText) {
        try {
            FXMLLoader loader = new FXMLLoader(StoryManager.class.getResource("/com/KhongPhaiQuy/Arkanoid/ui/StoryDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Story");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ownerStage);
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StoryDialogController controller = loader.getController();
            Image npcImage = new Image(StoryManager.class.getResourceAsStream(npcImagePath));
            controller.showDialogue(npcImage, dialogueText, () -> dialogStage.close()); // Truyền hành động đóng cửa sổ vào

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
