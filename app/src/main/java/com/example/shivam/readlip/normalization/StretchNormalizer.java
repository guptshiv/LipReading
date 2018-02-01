package com.example.shivam.readlip.normalization;

import com.example.shivam.readlip.Sample;
import com.example.shivam.readlip.Utils;

import java.util.List;

public class StretchNormalizer extends CenterNormalizer {

    // TODO TBD:
    private static final int OUT_UP_X = 1000;
    private static final int OUT_LOWER_X = 0;
    private static final int OUT_UP_Y = 1000;
    private static final int OUT_LOWER_Y = 0;


    @Override
	public Sample normalize(Sample sample) {
        double outRangeX = (OUT_UP_X - OUT_LOWER_X)/(sample.getRightEye().x - sample.getLeftEye().x);
        int inLowerBoundX = (int) sample.getLeftEye().x;
        int inLowerBoundY = (int) sample.getLeftEye().y;
        // SHIVAM CHANGED getX() to x and getY() to y
        for (List<Integer> vector : sample.getMatrix()) {
            int[] center = Utils.getCenter(vector);
            double outRangeY = (OUT_UP_Y - OUT_LOWER_Y)/(center[Utils.Y_INDEX] - sample.getLeftEye().y);
            // Computation:
            // OUTVAL=(INVAL - INLO) * ((OUTUP-OUTLO)/(INUP-INLO)) + OUTLO
            // OUTVAL = Value of pixel in output map
            // INVAL = Value of pixel in input map
            // INLO = Lower value of 'stretch from' range
            // INUP = Upper value of 'stretch from' range
            // OUTLO = Lower value of 'stretch to' range
            // OUTUP = Upper value of 'stretch to' range

            for (int i = 0; i < vector.size(); i++) {
                if(i % 2 == 0){
                    int outValX = (int) (((vector.get(i)-inLowerBoundX)*outRangeX) + OUT_LOWER_X);
                    vector.set(i, outValX);
                }
                else  {
                    int outValY = (int) (((vector.get(i)-inLowerBoundY)*outRangeY) + OUT_LOWER_Y);
                    vector.set(i, outValY);
                }
            }
        }
        return sample;
	}

}
