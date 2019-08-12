package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

public class RealLifecycleFragment extends Fragment {

    {
        mLifecycleRegistry = new RealLifecycleRegistry(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        } else {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        } else {
            mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

}
