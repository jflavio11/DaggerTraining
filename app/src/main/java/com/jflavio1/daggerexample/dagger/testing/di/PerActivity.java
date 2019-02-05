package com.jflavio1.daggerexample.dagger.testing.di;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * PerActivity
 * <p>
 * The Scope annotation is used for specify the dependency lifetime based on the lifecycle of the
 * class that requires it. For example, this interface can be used for dependencies that will be
 * alive into an activity lifecycle, once it is destroyed (the activity), the instantiated
 * dependency will be removed from memory too.
 *
 * PerActivity interface is going to be used as an annotation on Components that will need to
 * specify dependencies that relies on activities lifecycle.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
