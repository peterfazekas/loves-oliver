package hu.targetshooting.model.service;

import hu.targetshooting.model.domain.ShotResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader implements DataReader {

    @Override
    public List<ShotResult> getData(String input) {
        return parse(read(input));
    }

    private List<String> read(String input) {
        return new ArrayList<>();
    }

    private List<ShotResult> parse(List<String> lines){
        return lines.stream()
                .map(i -> createShotResult(i))
                .collect(Collectors.toList());
    }

    private ShotResult createShotResult(String line) {
        Integer id = 1;
        String shots = "+++-+--";
        Integer score = 32;
        return new ShotResult(id, shots, score);
    }
}
