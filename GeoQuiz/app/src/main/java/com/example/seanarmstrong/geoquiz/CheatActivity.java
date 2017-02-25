package com.example.seanarmstrong.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.seanarmstrong.geoquiz.answer_is_true";
    private static final String ANSWER_SHOWN = "com.example.seanarmstrong.geoquiz.answer_shown";


    private boolean mAnswerIsTrue;
    private TextView mAnswerTextview;
    private Button mShowAnswerButton;
    private boolean mAnswerShown;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextview = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showAnswer();
            }
        });


        if (savedInstanceState != null) {
            mAnswerShown = savedInstanceState.getBoolean(ANSWER_SHOWN, false);
            if (mAnswerShown) {
                showAnswer();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(ANSWER_SHOWN, mAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        mAnswerShown = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void showAnswer() {
        if (mAnswerIsTrue) {
            mAnswerTextview.setText(R.string.true_button);
        } else {
            mAnswerTextview.setText(R.string.false_button);
        }
        setAnswerShownResult(true);
    }
}
