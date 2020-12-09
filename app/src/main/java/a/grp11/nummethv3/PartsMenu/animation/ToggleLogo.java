package a.grp11.nummethv3.Menus.Animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;



public class ToggleLogo {

    private final static int duration = 400;
    private View headerLogo;

    public static void toggle(final CircleImageView headerLogo, final Drawable newLogo, final int newColor){

        //animation de disparition
        final AnimatorSet animatorSetDisappear = new AnimatorSet();


        animatorSetDisappear.setDuration(duration);
        animatorSetDisappear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 0),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 0)
        );

        //animation d'apparition
        final AnimatorSet animatorSetAppear = new AnimatorSet();
        animatorSetAppear.setDuration(duration);
        animatorSetAppear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 1),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 1)
        );

        //après la disparition
        animatorSetDisappear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                //modifie la couleur du cercle
                headerLogo.setBackgroundColor(Color.TRANSPARENT);
                headerLogo.setCircleBackgroundColor(newColor);
                //modifie l'image contenue dans le cercle
                headerLogo.setImageDrawable(newLogo);

                //démarre l'animation d'apparition
                animatorSetAppear.start();
            }
        });
        //démarre l'animation de disparition
        animatorSetDisappear.start();
    }
}
