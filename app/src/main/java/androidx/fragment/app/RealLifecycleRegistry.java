package androidx.fragment.app;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import java.lang.ref.WeakReference;

public class RealLifecycleRegistry extends LifecycleRegistry {

    private final WeakReference<LifecycleOwner> mLifecycleOwner;

    public RealLifecycleRegistry(@NonNull LifecycleOwner provider) {
        super(provider);
        mLifecycleOwner = new WeakReference<>(provider);
    }

    @Override
    public void handleLifecycleEvent(@NonNull Event event) {
        LifecycleOwner lifecycleOwner = mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            super.handleLifecycleEvent(event);
            return;
        }

        if (!(lifecycleOwner instanceof RealLifecycleFragment)) {
            super.handleLifecycleEvent(event);
            return;
        }

        RealLifecycleFragment fragment = (RealLifecycleFragment) lifecycleOwner;
        if (event == Event.ON_RESUME) {
            if (fragment.isResumed() && fragment.getUserVisibleHint() && !fragment.isHidden()) {
                super.handleLifecycleEvent(event);
            }
        } else {
            super.handleLifecycleEvent(event);
        }
    }

}
