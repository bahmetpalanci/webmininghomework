package com.web.mining.cli.model;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;

/**
 * User: bap
 * Date: 12/10/2019
 * Time: 20:21
 */
public class EvaluationVM {
    private String summary;
    private String matrix;
    private String classDetail;
    private String classifier;

    public EvaluationVM(Evaluation evaluation, Classifier classifier) throws Exception {
        this.summary = evaluation.toSummaryString();
        this.matrix = evaluation.toMatrixString();
        this.classDetail = evaluation.toClassDetailsString();
        this.classifier = classifier.toString();
    }

    public String getSummary() {
        return summary;
    }

    public String getMatrix() {
        return matrix;
    }

    public String getClassDetail() {
        return classDetail;
    }

    public String getClassifier() {
        return classifier;
    }
}
