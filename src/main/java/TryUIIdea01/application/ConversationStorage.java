package TryUIIdea01.application;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConversationStorage {
    private static final String CONVERSATIONS_DIR = "data/conversations";

    public static void saveConversation(Conversation conversation) {
        try {
            Path dirPath = Paths.get(CONVERSATIONS_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }

            Path filePath = dirPath.resolve(conversation.getId() + ".conv");
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(filePath.toFile()))) {
                oos.writeObject(conversation);
            }
        } catch (IOException e) {
            System.err.println("Error saving conversation: " + e.getMessage());
        }
    }

    public static List<Conversation> loadAllConversations() {
        List<Conversation> conversations = new ArrayList<>();
        Path dirPath = Paths.get(CONVERSATIONS_DIR);

        if (Files.exists(dirPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.conv")) {
                for (Path filePath : stream) {
                    try (ObjectInputStream ois = new ObjectInputStream(
                            new FileInputStream(filePath.toFile()))) {
                        conversations.add((Conversation) ois.readObject());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading conversations: " + e.getMessage());
            }
        }

        return conversations;
    }
}