package com.web.mining.cli.controller;

import com.web.mining.cli.service.AbstractMiningAlgorithm;
import com.web.mining.cli.service.DecisionTree;
import com.web.mining.cli.service.LinearRegression;
import com.web.mining.cli.service.NaiveBayes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import weka.classifiers.Evaluation;

@RestController
public class ApiController {

    @Autowired
    private DecisionTree decisionTree;

    @Autowired
    private NaiveBayes naiveBayes;

    @Autowired
    private LinearRegression linearRegression;

    @PostMapping(value = "/getResult")
    public Evaluation getResult(@RequestParam("algorithmType") String algorithmType,
                                @RequestParam("numberOfFolds") Integer numberOfFolds,
                                @RequestParam(value = "dataset", required = false) MultipartFile dataset) throws Exception {
        AbstractMiningAlgorithm miningAlgorithm;
        switch (algorithmType) {
            case "NAIVE":
                miningAlgorithm = naiveBayes;
                break;
            case "DECISION":
                miningAlgorithm = decisionTree;
                break;
            case "LINEAR":
                miningAlgorithm = linearRegression;
                break;
            default:
                miningAlgorithm = naiveBayes;
        }
        return miningAlgorithm.process(dataset, numberOfFolds);
    }
}
