package com.example.shivam.readlip.normalization;

import com.example.shivam.readlip.Sample;

public class EmptyNomalizer implements Normalizer{

	@Override
	public Sample normalize(Sample sample) {
		return sample;
	}
	
}
