package hu.targetshooting.model.domain;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShotResult {

    private final Integer id;
    private final String shots;
    private final Integer score;

    public ShotResult(Integer id, String shots, Integer score) {
        this.id = id;
        this.shots = shots;
        this.score = score;
    }

    public boolean hasTwoSuccessShotsInRow() {
        return shots.contains("++");
    }

    public Integer getShotsCount() {
        return shots.length();
    }

    public String getSuccessShotsIndexes() {
        return IntStream.range(0, getShotsCount())
                .filter(i -> shots.charAt(i) == '+')
                .mapToObj(i -> String.valueOf(i + 1))
                .collect(Collectors.joining(" "));
    }

    public long countSuccessShots() {
        return shots.chars()
                .filter(i -> (char) i == '+')
                .count();
    }

    public Integer getLongestSuccessSequenceSize() {
        return Arrays.stream(shots.split("-"))
                .mapToInt(String::length)
                .max()
                .getAsInt();
    }

    public Integer getId() {
        return id;
    }

    public String getShots() {
        return shots;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", shots='" + shots + '\'' +
                ", score=" + score +
                '}';
    }
}
