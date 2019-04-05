package com.example.testcard.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.testcard.R;
import com.example.testcard.models.Card;


public class CardView extends View {

    String cardValue, cardColor;
    boolean isTurn,isFind;

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CardView, 0, 0);
        cardValue = a.getString(R.styleable.CardView_valueText);
        cardColor = a.getString(R.styleable.CardView_colorText);
        isTurn = a.getBoolean(R.styleable.CardView_isTurn, false);
    }

    public CardView(Context context, Card card) {
        super(context);
        cardColor = card.getColor();
        cardValue = card.getValue();
        isTurn = card.isTurn();
        isFind = card.isFind();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(isFind ? Color.GRAY: Color.BLUE);
        paint.setColor(isTurn ? Color.BLUE : getColorPaint());

        canvas.drawRect(0, 0, 200, 300, paint);
        paint.setColor(Color.WHITE);
        canvas.drawRect(10, 10, 190, 290, paint);
        paint.setTextSize(34);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        if(isTurn) {
            paint.setColor(Color.BLUE);
            canvas.drawText("Card",100,150,paint);
        }else{
            paint.setColor(getColorPaint());
            canvas.drawText(getColorSymbol(cardColor), 100, 150, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(cardValue, 50, 50, paint);
            canvas.scale(-1f, -1f, 40, 50);
            canvas.drawText(cardValue, -70, -150, paint);
        }
    }

    private String getColorSymbol(String value) {
        switch (value) {
            case "COEUR":
                return new String(Character.toChars(0x2665));
            case "CARREAU":
                return new String(Character.toChars(0x2666));
            case "PIQUE":
                return new String(Character.toChars(0x2660));
            case "TREFLE":
                return new String(Character.toChars(0x2663));
        }
        return ""; //car on ne peut pas mettre de default, il faudrait toujours avoir un return pour chaque cas
    }

    private int getColorPaint() {
        if (cardColor == "COEUR" || cardColor == "CARREAU") {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
        invalidate(); //la methode vue repasse dans onDraw donc sa redessine la carte
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public boolean isFind() {
        return isFind;
    }

    public void setFind(boolean find) {
        isFind = find;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200, 300);
    }
}
