/**
 * Copyright 2016 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex.internal.subscribers.flowable;

import org.reactivestreams.*;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * A subscriber that ignores all events.
 */
public enum EmptySubscriber implements Subscriber<Object> {
    /** Empty instance that reports error to the plugins. */
    INSTANCE(true, false),
    /** Empty instance that doesn't report to the plugins to avoid flooding the test output. */
    INSTANCE_NOERROR(false, false),
    /** Empty instance that cancels subscriptions. */
    CANCELLED(true, true);
    
    private final boolean reportError;
    private final boolean cancelSubscription;
    
    EmptySubscriber(boolean reportError, boolean cancelSubscription) {
        this.reportError = reportError;
        this.cancelSubscription = cancelSubscription;
    }
    
    @Override
    public void onSubscribe(Subscription s) {
        if (cancelSubscription) {
            s.cancel();
        }
    }
    
    @Override
    public void onNext(Object t) {
        
    }
    
    @Override
    public void onError(Throwable t) {
        if (reportError) {
            RxJavaPlugins.onError(t);
        }
    }
    
    @Override
    public void onComplete() {
        
    }
}
