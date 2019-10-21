package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShotService {

    private final List<ShotResult> shotResults;

    public ShotService(List<ShotResult> shotResults) {
        this.shotResults = shotResults;
    }

    public String getBetterIds() {
        return shotResults.stream()
                .filter(ShotResult::hasTwoSuccessShotsInRow)
                .map(ShotResult::getId)
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public Integer getLongestShotSequencesId() {
        return shotResults.stream()
                .max(Comparator.comparing(ShotResult::getShotsCount))
                .map(ShotResult::getId)
                .get();
    }

    public String getShotResultDetails(int id) {
        ShotResult shotResult = getShotResultById(id);
        return String.format("   a. Célt érő lövések: %s%n" +
                        "   b. Az eltalált korongok száma: %d%n" +
                        "   c. A leghosszabb hibátlan sorozat hossza: %d%n" +
                        "   d. A versenyző pontszáma: %d",
                shotResult.getSuccessShotsIndexes(), shotResult.countSuccessShots(),
                shotResult.getLongestSuccessSequenceSize(), shotResult.getScore());
    }

    public List<String> getFinalResult() {
        List<String> lines = new ArrayList<>();
        List<ShotResult> finalResultList = createFinalResult();
        int prevScore = 0, prevOrder = 0;
        for (int i = 0; i < finalResultList.size(); i++) {
            ShotResult finalResult = finalResultList.get(i);
            int order = finalResult.getScore() == prevScore ? prevOrder : i + 1;
            lines.add(order + "\t" + finalResult.getId() + "\t" + finalResult.getScore());
            prevScore = finalResult.getScore();
            prevOrder = order;
        }
        return lines;
    }

    private List<ShotResult> createFinalResult() {
        return shotResults.stream()
                .sorted((i, j) -> j.getScore().compareTo(i.getScore()))
                .collect(Collectors.toList());
    }

    private ShotResult getShotResultById(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .get();
    }
}
