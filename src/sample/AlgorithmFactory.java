package sample;

import java.util.Locale;

public class AlgorithmFactory {
    public Algorithm getAlgorithm(String algorithmName)
    {
        switch (algorithmName.toLowerCase()) {
            case "bfs":
                return new BFS();
        }
        return null;
    }
}
