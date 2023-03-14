package com.kenzie.breadthfirstsearch.wordsearch;

import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.wordsearch.sharedmodel.Direction;

import java.util.*;

import static com.kenzie.breadthfirstsearch.wordsearch.SampleWordSearches.SORE_SEARCH;

/**
 * Class for completing word search puzzles.
 */
public class WordSearcher {
    private final WordSearch wordSearch;

    /**
     * Create a word search instance for the provided problem.
     *
     * @param wordSearch - the word search puzzle to solve
     */
    public WordSearcher(WordSearch wordSearch) {
        this.wordSearch = wordSearch;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */
    public static void main(String[] args) {
        WordSearcher wordSearcher = new WordSearcher(SORE_SEARCH);

        System.out.println(wordSearcher.calculateWordCounts());
    }

    /**
     * Calculate how many ways (permutations) you can use the letters in the puzzle to spell
     * each word provided as part of the puzzle.
     *
     * @return a Map of the desired words, and how many ways (permutations) you can use the letters in the puzzle to
     * spell each word provided as part of the puzzle.
     */
    public Map<String, Long> calculateWordCounts() {
        Map<String, Long> wordCounts = new HashMap<>();
        for (String word : wordSearch.getWordsToFind()) {
            long count = findWord(word);
            wordCounts.put(word, count);
        }
        return wordCounts;
    }

    private long findWord(String word) {
        int numRows = wordSearch.getWidth();
        int numCols = wordSearch.getHeight();
        boolean[][] visited = new boolean[numRows][numCols];
        Queue<Coordinate> queue = new LinkedList<>();
        long count = 0;

        // Loop through the entire grid to find the starting coordinate of the word
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
               // if (wordSearch.getGrid()[i][j] == word.charAt(0)) {
                    Coordinate start = new Coordinate(i, j);
                    queue.offer(start);
                    visited[i][j] = true;
                    count += bfs(word, start, visited, queue);
                }
            }
        return count;
    }

    private long bfs(String word, Coordinate start, boolean[][] visited, Queue<Coordinate> queue) {
        if (word.length() == 1) {
            return 1; // found a permutation of the word
        }
        long count = 0;
        for (Direction dir : Direction.ALL_DIRECTIONS) {
            Coordinate next = dir.addToCoordinate(start);
            int row = next.getRow();
            int col = next.getColumn();
            if (row >= 0 && row < visited.length && col >= 0 && col < visited[0].length
                    && !visited[row][col] && wordSearch.getGrid()[row][col] == word.charAt(1)) {
                visited[row][col] = true;
                queue.offer(next);
                count += bfs(word.substring(1), next, visited, queue);
                visited[row][col] = false;
                queue.poll();
            }
        }
        return count;
    }

}


