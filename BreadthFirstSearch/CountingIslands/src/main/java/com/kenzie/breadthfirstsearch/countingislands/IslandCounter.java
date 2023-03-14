package com.kenzie.breadthfirstsearch.countingislands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Counts the number of islands for a map.
 */
public class IslandCounter {
    private final int width;
    private final int height;
    private final int[][] map;

    public IslandCounter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        IslandCounter islandCounter = new IslandCounter(5, 5, SamplesMaps.FIVE_ISLAND_MAP);
        int islandCount = islandCounter.countIslands();
        System.out.println(String.format("Found %s islands.", islandCount));
    }

    /**
     * Counts the number of islands for the map.
     *
     * @return the number of islands for the map.
     */
    public int countIslands() {
        // Create a boolean matrix to keep track of visited cells
        boolean[][] visited = new boolean[height][width];

        // Initialize island count to 0
        int islandCount = 0;

        // Iterate through all cells in the map
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // If the current cell is land and hasn't been visited yet, start a new island search
                if (map[i][j] == 1 && !visited[i][j]) {
                    // Increment island count
                    islandCount++;

                    // Create a queue to store neighboring land cells
                    Queue<int[]> queue = new LinkedList<>();
                    // Add the current cell to the queue
                    queue.offer(new int[]{i, j});

                    // Mark the current cell as visited
                    visited[i][j] = true;

                    // Perform BFS on the current island
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int x = curr[0];
                        int y = curr[1];

                        // Check neighboring cells and add any neighboring land cells to the queue
                        if (x > 0 && map[x - 1][y] == 1 && !visited[x - 1][y]) {
                            queue.offer(new int[]{x - 1, y});
                            visited[x - 1][y] = true;
                        }
                        if (x < height - 1 && map[x + 1][y] == 1 && !visited[x + 1][y]) {
                            queue.offer(new int[]{x + 1, y});
                            visited[x + 1][y] = true;
                        }
                        if (y > 0 && map[x][y - 1] == 1 && !visited[x][y - 1]) {
                            queue.offer(new int[]{x, y - 1});
                            visited[x][y - 1] = true;
                        }
                        if (y < width - 1 && map[x][y + 1] == 1 && !visited[x][y + 1]) {
                            queue.offer(new int[]{x, y + 1});
                            visited[x][y + 1] = true;
                        }
                    }
                }
            }
        }

        return islandCount;
    }
}