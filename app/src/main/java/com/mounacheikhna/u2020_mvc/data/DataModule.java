package com.mounacheikhna.u2020_mvc.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.u2020.data.api.ApiModule;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;
import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;
import static java.util.concurrent.TimeUnit.SECONDS;

@Module(includes = ApiModule.class)
public final class DataModule {
  static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

  @Provides
  @Singleton SharedPreferences provideSharedPreferences(Application app) {
    return app.getSharedPreferences("u2020", MODE_PRIVATE);
  }

  @Provides
  @Singleton Gson provideGson() {
    return new GsonBuilder()
        .registerTypeAdapter(DateTime.class, new DateTimeConverter())
        .create();
  }

  @Provides
  @Singleton Clock provideClock() {
    return Clock.REAL;
  }

  @Provides
  @Singleton IntentFactory provideIntentFactory() {
    return IntentFactory.REAL;
  }

  @Provides
  @Singleton OkHttpClient provideOkHttpClient(Application app) {
    return createOkHttpClient(app);
  }

  @Provides
  @Singleton Picasso providePicasso(Application app, OkHttpClient client) {
    return new Picasso.Builder(app)
        .downloader(new OkHttpDownloader(client))
        .listener(new Picasso.Listener() {
          @Override public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
            Timber.e(e, "Failed to load image: %s", uri);
          }
        })
        .build();
  }

  static OkHttpClient createOkHttpClient(Application app) {
    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(10, SECONDS);
    client.setReadTimeout(10, SECONDS);
    client.setWriteTimeout(10, SECONDS);

    // Install an HTTP cache in the application cache directory.
    File cacheDir = new File(app.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
    client.setCache(cache);

    return client;
  }
}
