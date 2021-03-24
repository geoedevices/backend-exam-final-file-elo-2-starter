package com.codecool.chessopen;

import java.io.*;
import java.util.*;

public class ChessResults {

    private class Competitor {

        private final String NAME;
        private final int TOTAL_POINTS;

        private Competitor(String name, int totalPoints) {
            this.NAME = name;
            this.TOTAL_POINTS = totalPoints;
        }

    }

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<Competitor> competitors = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] lineContent = scanner.nextLine().split(",");
                int totalPoints = Integer.parseInt(lineContent[1]) +
                        Integer.parseInt(lineContent[2]) +
                        Integer.parseInt(lineContent[3]) +
                        Integer.parseInt(lineContent[4]) +
                        Integer.parseInt(lineContent[5]);
                competitors.add(new Competitor(lineContent[0], totalPoints));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        List<String> listToReturn = new ArrayList<>();
        Competitor dummyCompetitor = new Competitor("", -1);
        while (competitors.size() > 0) {
            Competitor highestPointsCompetitor = dummyCompetitor;
            for (Competitor actualCompetitor : competitors) {
                if (actualCompetitor.TOTAL_POINTS > highestPointsCompetitor.TOTAL_POINTS) {
                    highestPointsCompetitor = actualCompetitor;
                }
            }
            listToReturn.add(highestPointsCompetitor.NAME);
            competitors.remove(highestPointsCompetitor);
        }
        return listToReturn;
    }

}
