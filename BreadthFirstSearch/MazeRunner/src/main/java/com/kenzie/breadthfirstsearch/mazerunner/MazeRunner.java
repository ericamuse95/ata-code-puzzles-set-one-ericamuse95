package com.kenzie.breadthfirstsearch.mazerunner;

import com.kenzie.breadthfirstsearch.mazerunner.model.MazePattern;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazeSpace;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Node;
import com.kenzie.breadthfirstsearch.mazerunner.utils.MazeGenerator;

import java.util.*;

import static com.kenzie.breadthfirstsearch.mazerunner.SampleMazes.MAZE_ONE_EXIT;

/**
 * Class for running through mazes.
 */
public class MazeRunner {

    /**
     * Private constructor, as all methods are static.
     */
    private MazeRunner() {}

    /**
     * Utility main method, to run MazeRunner methods without adding tests.
     *
     * @param args - Method arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println(MazeRunner.findClosestExit(MAZE_ONE_EXIT));
    }

    /**
     * Finds the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
     */
    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        if (entrance.isEmpty()) {
            return Optional.empty();
        }

        // nodes to visit
        Queue<Node<MazeSpace>> nodesToVisit = new LinkedList<>();
        //nodes visited
        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        //get entrance point
        //add entry node to visitedNodes
        nodesToVisit.offer(entrance.get());
        visitedNodes.add(entrance.get());
        //while nodesToVisit is not empty
        ///retrieve next node from the list of nodes to visit
        while(!nodesToVisit.isEmpty()){
            Node<MazeSpace> current = nodesToVisit.poll();
            MazeSpace space = current.getValue();
            if(space.isExit()){
                return Optional.of(space);
            }
            //compare current node against condition
            for(Node<MazeSpace> neighbor : current.getNeighbors()){
                if(!visitedNodes.contains(neighbor)){
                    visitedNodes.add(neighbor);
                    nodesToVisit.offer(neighbor);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Finds the path to the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
     */
    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);
        if (entrance.isEmpty()) {
            return Collections.emptyList();
        }
        //nodes to visit
        Queue<Node<MazeSpace>> nodesToVisit = new LinkedList<>();
        //nodes visited
        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        //map to hold the shortest path for each node
        Map<Node<MazeSpace>, Node<MazeSpace>> parents = new HashMap<>();
        //add the entrance node to the nodes to visit and to the map
        nodesToVisit.offer(entrance.get());
        visitedNodes.add(entrance.get());
        //if we reach an exit, return shortest path
        while (!nodesToVisit.isEmpty()) {
            Node<MazeSpace> current = nodesToVisit.poll();
            MazeSpace space = current.getValue();

            if (space.isExit()) {
                List<MazeSpace> path = new ArrayList<>();
                Node<MazeSpace> node = current;
                while (node != null) {
                    path.add(node.getValue());
                    node = parents.get(node);
                }
                Collections.reverse(path);
                return path;
            }
            //iterate over neighbors of the current node
            for (Node<MazeSpace> neighbor : current.getNeighbors()) {
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    parents.put(neighbor, current);
                    nodesToVisit.offer(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }
}


