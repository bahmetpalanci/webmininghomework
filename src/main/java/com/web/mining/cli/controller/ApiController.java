package com.web.mining.cli.controller;

import com.web.mining.cli.model.EvaluationVM;
import com.web.mining.cli.service.AbstractMiningAlgorithm;
import com.web.mining.cli.service.DecisionTree;
import com.web.mining.cli.service.KNN;
import com.web.mining.cli.service.NaiveBayes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ApiController {

    @Autowired
    private DecisionTree decisionTree;

    @Autowired
    private NaiveBayes naiveBayes;

    @Autowired
    private KNN KNN;

    @PostMapping(value = "/getResult")
    public EvaluationVM getResult(@RequestParam("algorithmType") String algorithmType,
                                  @RequestParam("numberOfFolds") Integer numberOfFolds,
                                  @RequestParam("neighbors") Integer neighbors,
                                  @RequestParam(value = "dataset", required = false) MultipartFile dataset) throws Exception {
        AbstractMiningAlgorithm miningAlgorithm;
        switch (algorithmType) {
            case "DECISION":
                miningAlgorithm = decisionTree;
                break;
            case "KNN":
                miningAlgorithm = KNN;
                break;
            default:
                miningAlgorithm = naiveBayes;
        }
        return miningAlgorithm.process(dataset, numberOfFolds, neighbors);
    }
}
