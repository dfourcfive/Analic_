package a.grp11.nummethv3.Menus.BottomMenu.Animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class BottomMenuAnimation {

    private AnimatorSet mAnimationSlideUp;

    public BottomMenuAnimation(View target , int slideLenght , int duration) {
        mAnimationSlideUp = new AnimatorSet();

        mAnimationSlideUp.setDuration(duration);
        mAnimationSlideUp.playTogether(
                ObjectAnimator.ofFloat(target,"translationY",slideLenght)
        );
    }
    private void start(){
        mAnimationSlideUp.start();
    }
    public static void slide(View target , int slideLenght ,int duration) {
        new BottomMenuAnimation(target,slideLenght,duration).start();
    }
}

