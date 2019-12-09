package com.web.mining.cli.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 * User: bap
 * Date: 12/9/2019
 * Time: 22:39
 */
@Component
public class LinearRegression extends AbstractMiningAlgorithm {
    @Override
    public Evaluation process(MultipartFile multipartFile, int numberOfFolds) throws Exception {
        Instances dataSet = getDataSet(multipartFile);
        Instances[] split = crossValidationSplit(dataSet, numberOfFolds);
        Instances trainingDataSet = split[0];
        Instances testingDataSet = split[1];
        Classifier classifier = new weka.classifiers.functions.LinearRegression();
        classifier.buildClassifier(trainingDataSet);

        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        /** Print the algorithm summary */
        System.out.println("** Linear Regression Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(classifier);

        return eval;
    }
}
