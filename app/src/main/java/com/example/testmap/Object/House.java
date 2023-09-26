package com.example.testmap.Object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.testmap.Object.Structure;
import com.example.testmap.R;

public class House extends Structure {

    public House(float x, float y, Context context) {

        super(x, y, context, R.drawable.housetest,0,0);

    }
}
