package com.example.shivam.readlip.classification;

import com.example.shivam.readlip.Sample;

import java.util.List;

public class NullClassifier implements Classifier {

	@Override
	public String test(Sample test) {
		return null;
	}

	@Override
	public void train(List<Sample> trainingSet) {}

	@Override
	public void update(Sample train) {}

}
