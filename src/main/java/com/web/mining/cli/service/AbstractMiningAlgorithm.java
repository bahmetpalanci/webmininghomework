package com.web.mining.cli.service;

import org.springframework.web.multipart.MultipartFile;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * User: bap
 * Date: 12/9/2019
 * Time: 22:37
 */
public abstract class AbstractMiningAlgorithm {
    public Instances getDataSet(MultipartFile multipartFile) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setSource(new BufferedInputStream(multipartFile.getInputStream()));
        Instances dataSet = loader.getDataSet();
        dataSet.randomize(new Random(1));
        if (dataSet.classIndex() == -1)
            dataSet.setClassIndex(dataSet.numAttributes() - 1);
        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(dataSet);
        return Filter.useFilter(dataSet, filter);
    }

    public Instances[] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[] split = new Instances[2];
        for (int i = 0; i < numberOfFolds; i++) {
            split[0] = data.trainCV(numberOfFolds, i);
            split[1] = data.testCV(numberOfFolds, i);
        }
        return split;
    }

    public abstract Evaluation process(MultipartFile multipartFile, int numberOfFolds) throws Exception;

}
