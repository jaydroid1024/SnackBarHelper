package org.jay.snackbarhelper;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.snack_with_custom_action)
    Button mSnackTxtColor;
    @BindView(R.id.snack_with_color)
    Button mSnackActionClick;
    @BindView(R.id.snack_with_font)
    Button mSnackActionFont;
    @BindView(R.id.snack_with_icon)
    Button mSnackTitleIcon;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick({R.id.snack_with_custom_action, R.id.snack_with_color, R.id.snack_with_font, R.id.snack_with_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.snack_with_custom_action:
                SnackBarHelper
                        .with(this)
                        .setTwoActionLayout(R.layout.layout_snackbar)
                        .text("Snackbar with custom action", "ONE", "TWO")
                        .textColors(Color.RED, Color.GREEN, Color.BLUE)
                        .duration(SnackBarHelper.SnackBarDuration.INDEFINITE)
                        .actionListener(new SnackBarHelper.OnActionClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "ONE", Toast.LENGTH_SHORT).show();
                            }
                        }, new SnackBarHelper.OnActionTwoClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "TWO", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                break;
            case R.id.snack_with_color:
                SnackBarHelper
                        .with(this)
                        .text("Snackbar with native bg", "action")
                        .textColors(Color.WHITE, Color.YELLOW)
                        .backgroundColor(Color.BLACK)
                        .duration(SnackBarHelper.SnackBarDuration.INDEFINITE)
                        .actionListener(new SnackBarHelper.OnActionClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "ACTION", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.snack_with_font:
                SnackBarHelper
                        .with(this)
                        .text("Snackbar with Custom font", "ACTION")
                        .customTitleFont(Typeface.MONOSPACE)
                        .customActionFont(Typeface.MONOSPACE)
                        .duration(SnackBarHelper.SnackBarDuration.INDEFINITE)
                        .actionListener(new SnackBarHelper.OnActionClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "ACTION", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.snack_with_icon:
                SnackBarHelper
                        .with(this)
                        .setTwoActionLayout(R.layout.layout_snackbar)
                        .text("Snackbar with icon", "ONE", "TWO")
                        .textColors(Color.RED, Color.GREEN, Color.BLUE)
                        .setIconForTitle(generateDrawable(android.R.drawable.ic_dialog_alert), SnackBarHelper.IconPosition.LEFT, 6)
                        .setIconForAction(generateDrawable(android.R.drawable.ic_delete), SnackBarHelper.IconPosition.LEFT, 6)
                        .setIconForActionTwo(generateDrawable(android.R.drawable.ic_menu_edit), SnackBarHelper.IconPosition.LEFT, 6)
                        .duration(SnackBarHelper.SnackBarDuration.INDEFINITE)
                        .actionListener(new SnackBarHelper.OnActionClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "ONE", Toast.LENGTH_SHORT).show();
                            }
                        }, new SnackBarHelper.OnActionTwoClickListener() {
                            @Override
                            public void onActionClick(View view) {
                                Toast.makeText(MainActivity.this, "TWO", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }

    }

    public Drawable generateDrawable(int id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(id ,null);
        } else {
            return getResources().getDrawable(id);
        }
    }
}
