package com.example.shivam.readlip.normalization;

import android.graphics.*;

import com.example.shivam.readlip.Sample;
import com.example.shivam.readlip.Utils;

import java.awt.*;
import java.awt.Point;
import java.util.List;

/**
 * This normalizer class intends to normalize the given Sample coordinates to be around (0,0).
 * The center point of Sample`s lip coordinates will be (0,0) after normalization.
 *
 */
public class CenterNormalizer implements Normalizer{



    @Override
    public Sample normalize(Sample sample) {

        for (int i=0; i< sample.getMatrix().size(); i++) {
            List<Integer> vector = sample.getMatrix().get(i);
            int[] center = Utils.getCenter(vector);

            if (i==0 && sample.getLeftEye() != null && sample.getRightEye() != null){ //If first frame - normalize also eyes
                sample.setLeftEye(new android.graphics.Point((int)sample.getLeftEye().x - center[Utils.X_INDEX], (int)sample.getLeftEye().y - center[Utils.Y_INDEX]));
                sample.setRightEye(new android.graphics.Point((int)sample.getRightEye().x - center[Utils.X_INDEX], (int)sample.getRightEye().y - center[Utils.Y_INDEX]));
            }
            for (int j = 0; j < vector.size(); j++) {
                 if(j % 2 == 0)
                    vector.set(j, vector.get(j)- center[Utils.X_INDEX]);
                else
                    vector.set(j, vector.get(j)- center[Utils.Y_INDEX]);
            }
        }
        return sample;
    }


}
