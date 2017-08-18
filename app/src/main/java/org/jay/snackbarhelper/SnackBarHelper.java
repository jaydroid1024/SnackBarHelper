package org.jay.snackbarhelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Jay.
 */
public class SnackBarHelper {
    private Context mContext;
    private int mLayoutResource;
    private boolean mIsCustomeSnackBarLayout;
    private TextView mTxtTitle;
    private TextView mTxtAction;
    private TextView mTxtActionTwo;
    private Snackbar.SnackbarLayout mSbLayout;


    enum SnackBarDuration {
        SHORT, LONG, INDEFINITE;
    }


    enum IconPosition {
        LEFT, RIGHT, TOP, BOTTOM;
    }

    //Default snackbar will be showed for short duration
    private SnackBarDuration mSnackBarDuration = SnackBarDuration.SHORT;

    //Icons will be shown left by default for title
    private IconPosition mIconPositionTitle = IconPosition.LEFT;

    //Icons will be shown right by default for action
    private IconPosition mIconPositionAction = IconPosition.RIGHT;
    private IconPosition mIconPositionActionTwo = IconPosition.RIGHT;

    private OnActionClickListener mOnActionClickListener;
    private OnActionTwoClickListener mOnActionTwoClickListener;

    private View mView;

    private String mTitleText;

    private String mActionText;
    private String mActionTextTwo;

    private int mTitleTextColor = Color.WHITE;

    private int mActionTextColor = Color.YELLOW;
    private int mActionTextTwoColor = Color.GREEN;

    private int mBackgroundColor = Color.BLACK;


    private Typeface mTfTitle = Typeface.DEFAULT;

    private Typeface mTfAction = Typeface.DEFAULT;
    private Typeface mTfActionTwo = Typeface.DEFAULT;

    private Drawable mTitleIconDrawable = null;

    private Drawable mActionIconDrawable = null;
    private Drawable mActionTwoIconDrawable = null;

    private int mIconTitlePadding = 0;

    private int mIconActionPadding = 0;
    private int mIconActionTwoPadding = 0;


    public SnackBarHelper(Context context) {
        mContext = context;
    }

    public SnackBarHelper() {

    }

    public static SnackBarHelper with(@NonNull Context context) {
        return new SnackBarHelper(context);
    }


    //Assigning the view for which Snackbar responds
    public SnackBarHelper view(View view) {
        this.mView = view;
        return this;
    }

    //Assigning the title and action text

    public SnackBarHelper text(String titleText, String actionText) {
        this.mTitleText = titleText;
        this.mActionText = actionText;
        return this;
    }

    public SnackBarHelper text(String titleText, String actionTextOne, String actionTextTwo) {
        this.mTitleText = titleText;
        this.mActionText = actionTextOne;
        this.mActionTextTwo = actionTextTwo;
        return this;
    }

    //To customize title and action text colors

    public SnackBarHelper textColors(int titleTextColor, int actionTextColor) {
        this.mTitleTextColor = titleTextColor;
        this.mActionTextColor = actionTextColor;
        return this;
    }

    public SnackBarHelper textColors(int titleTextColor, int actionTextOneColor, int actionTextTwoColor) {
        this.mTitleTextColor = titleTextColor;
        this.mActionTextColor = actionTextOneColor;
        this.mActionTextTwoColor = actionTextTwoColor;
        return this;
    }

    //To customize background color of snack bar
    public SnackBarHelper backgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        return this;
    }

    //Change duration whether SnackBar should be shown for short, long or Indefinite
    public SnackBarHelper duration(SnackBarDuration snackBarDuration) {
        this.mSnackBarDuration = snackBarDuration;
        return this;
    }

    //Change font for title
    public SnackBarHelper customTitleFont(Typeface tf) {
        this.mTfTitle = tf;
        return this;
    }

    //Change font for title
    public SnackBarHelper customActionFont(Typeface tf) {
        this.mTfAction = tf;
        return this;
    }

    public SnackBarHelper customActionTwoFont(Typeface tf) {
        this.mTfAction = tf;
        return this;
    }


    public SnackBarHelper setIconForTitle(Drawable titleDrawable, IconPosition iconPostion, int drawablePadding) {
        this.mTitleIconDrawable = titleDrawable;
        this.mIconPositionTitle = iconPostion;
        this.mIconTitlePadding = drawablePadding;
        return this;
    }

    public SnackBarHelper setIconForAction(Drawable actionDrawable, IconPosition iconPostion, int drawablePadding) {
        this.mActionIconDrawable = actionDrawable;
        this.mIconPositionAction = iconPostion;
        this.mIconActionPadding = drawablePadding;
        return this;
    }

    public SnackBarHelper setIconForActionTwo(Drawable actionDrawable, IconPosition iconPostion, int drawablePadding) {
        this.mActionTwoIconDrawable = actionDrawable;
        this.mIconPositionActionTwo = iconPostion;
        this.mIconActionTwoPadding = drawablePadding;
        return this;
    }

    public SnackBarHelper setTwoActionLayout(@LayoutRes int resource) {
        mLayoutResource = resource;
        mIsCustomeSnackBarLayout = true;
        return this;
    }

    public Snackbar getSnackBar() {

        int duration = 0;

        switch (mSnackBarDuration) {

            case SHORT:
                duration = Snackbar.LENGTH_SHORT;
                break;

            case LONG:
                duration = Snackbar.LENGTH_LONG;
                break;

            case INDEFINITE:
                duration = Snackbar.LENGTH_INDEFINITE;
                break;
        }
        Snackbar snackbar = Snackbar.make(((Activity) mContext).findViewById(android.R.id.content), mTitleText, duration);
        if (mView != null) {
            snackbar = Snackbar.make(mView, mTitleText, duration);
        }

        // Get the Snackbar's layout view
        mSbLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        if (!mIsCustomeSnackBarLayout) {
            snackbar.setAction(mActionText, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnActionClickListener != null) {
                        mOnActionClickListener.onActionClick(view);
                    }
                }
            });
            mTxtTitle = (TextView) mSbLayout.findViewById(android.support.design.R.id.snackbar_text);
            mTxtAction = (TextView) mSbLayout.findViewById(android.support.design.R.id.snackbar_action);
        } else {
            LinearLayout.LayoutParams objLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mSbLayout.setPadding(0, 0, 0, 0);
            // Hide the text
            TextView textView = (TextView) mSbLayout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setVisibility(View.INVISIBLE);
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            // Inflate our custom view
            View CustomeSnackView = mInflater.inflate(mLayoutResource, null);
            // Configure the view
            final Snackbar finalSnackbar = snackbar;
            mTxtTitle = (TextView) CustomeSnackView.findViewById(R.id.txtMessage);
            mTxtTitle.setText(mTitleText);
            mTxtAction = (TextView) CustomeSnackView.findViewById(R.id.txtOne);
            mTxtAction.setText(mActionText);
            mTxtAction.setTextColor(mActionTextColor);
            mTxtAction.setTypeface(mTfAction);
            mTxtAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalSnackbar.dismiss();
                    if (mOnActionClickListener != null) {
                        mOnActionClickListener.onActionClick(v);
                    }
                }
            });

            mTxtActionTwo = (TextView) CustomeSnackView.findViewById(R.id.txtTwo);
            mTxtActionTwo.setText(mActionTextTwo);
            mTxtActionTwo.setTextColor(mActionTextTwoColor);
            mTxtActionTwo.setTypeface(mTfActionTwo);

            mTxtActionTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalSnackbar.dismiss();
                    if (mOnActionTwoClickListener != null) {
                        mOnActionTwoClickListener.onActionClick(v);
                    }
                }
            });
            setIcon(mTxtActionTwo, mActionTwoIconDrawable, mIconPositionActionTwo, Gravity.CENTER, mIconActionTwoPadding);
            // Add the view to the Snackbar's layout
            mSbLayout.addView(CustomeSnackView, objLayoutParams);
        }

        // Changing message text color
        mTxtTitle.setTextColor(mTitleTextColor);


        // Changing action button text color
        mTxtAction.setTextColor(mActionTextColor);

        //Changing background color
        mSbLayout.setBackgroundColor(mBackgroundColor);

        //Changing font style for title
        mTxtTitle.setTypeface(mTfTitle);

        //Changing font style for action
        mTxtAction.setTypeface(mTfAction);

        setIcon(mTxtTitle, mTitleIconDrawable, mIconPositionTitle, Gravity.CENTER_VERTICAL, mIconTitlePadding);

        setIcon(mTxtAction, mActionIconDrawable, mIconPositionAction, Gravity.CENTER, mIconActionPadding);



        return snackbar;
    }

    private void setIcon(TextView textView, Drawable drawable, IconPosition iconPosition, int gravity, int iconPadding) {

        textView.setGravity(gravity);
        textView.setCompoundDrawablePadding(iconPadding);
        switch (iconPosition) {

            case LEFT:

                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                break;

            case RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

                break;

            case TOP:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);

                break;

            case BOTTOM:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);

                break;
        }
    }

    //To show the Snackbar in UI.
    public void show() {
        getSnackBar().show();
    }


    //Listeners to handle SnackBar Action click
    public SnackBarHelper actionListener(OnActionClickListener onActionClickListener) {

        this.mOnActionClickListener = onActionClickListener;
        return this;
    }

    public SnackBarHelper actionListener(OnActionClickListener onActionClickListener, OnActionTwoClickListener onActionTwoClickListener) {
        this.mOnActionClickListener = onActionClickListener;
        this.mOnActionTwoClickListener = onActionTwoClickListener;
        return this;
    }

    public interface OnActionClickListener {

        void onActionClick(View view);

    }

    public interface OnActionTwoClickListener {

        void onActionClick(View view);

    }

}





