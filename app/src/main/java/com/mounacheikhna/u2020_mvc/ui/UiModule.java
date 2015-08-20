package com.mounacheikhna.u2020_mvc.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UiModule {
  @Provides
  @Singleton AppContainer provideAppContainer() {
    return AppContainer.DEFAULT;
  }

  @Provides
  @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }
}
