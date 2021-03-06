package com.droidsonroids.workcation.screens.main.map;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.droidsonroids.workcation.R;
import com.droidsonroids.workcation.common.model.Place;

public class BaliDetailsLayout extends CoordinatorLayout {

    @BindView(R.id.cardview) CardView cardViewContainer;
    @BindView(R.id.headerImage) ImageView imageViewPlaceDetails;
    @BindView(R.id.title) TextView textViewTitle;
    @BindView(R.id.description) TextView textViewDescription;

    public BaliDetailsLayout(final Context context) {
        this(context, null);
    }

    public BaliDetailsLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    private void setData(Place place) {
        textViewTitle.setText(place.getName());
        textViewDescription.setText(place.getDescription());
    }

    public static Scene showScene(Activity activity, final ViewGroup container, final View sharedView, final String transitionName, final Place data) {
        BaliDetailsLayout baliDetailsLayout = (BaliDetailsLayout) activity.getLayoutInflater().inflate(R.layout.item_place, container, false);
        baliDetailsLayout.setData(data);

        TransitionSet set = new ShowDetailsTransitionSet(activity, transitionName, sharedView, baliDetailsLayout);
        Scene scene = new Scene(container, (View)baliDetailsLayout);
        TransitionManager.go(scene, set);
        return scene;
    }

    public static Scene hideScene(Activity activity, final ViewGroup container, final View sharedView, final String transitionName) {
        BaliDetailsLayout baliDetailsLayout = (BaliDetailsLayout) container.findViewById(R.id.bali_details_container);

        TransitionSet set = new HideDetailsTransitionSet(activity, transitionName, sharedView, baliDetailsLayout);
        Scene scene = new Scene(container, (View)baliDetailsLayout);
        TransitionManager.go(scene, set);
        return scene;
    }
}
