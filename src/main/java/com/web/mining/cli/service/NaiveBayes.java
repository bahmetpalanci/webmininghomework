package com.web.mining.cli.service;

import com.web.mining.cli.model.EvaluationVM;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;

/**
 * User: bap
 * Date: 12/9/2019
 * Time: 22:39
 */
@Component
public class NaiveBayes extends AbstractMiningAlgorithm {

    @Override
    public EvaluationVM process(MultipartFile multipartFile, int numberOfFolds, int neighbors) throws Exception {
        Instances dataSet = getDataSet(multipartFile);
        Instances[] split = crossValidationSplit(dataSet, numberOfFolds);
        Instances trainingDataSet = split[0];
        Instances testingDataSet = split[1];

        Classifier classifier = new NaiveBayesMultinomial();
        classifier.buildClassifier(trainingDataSet);

        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        return new EvaluationVM(eval,classifier);
    }
}
