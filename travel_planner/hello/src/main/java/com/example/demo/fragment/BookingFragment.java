package com.example.demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo.R;


//Booking view
public class BookingFragment extends Fragment {

    private WebView webview;
    private LinearLayout layout;
    private TextView tv_loading;
    private ProgressBar progressbar;

    public static BookingFragment newInstance(String text) {
        BookingFragment fragmentCommon = new BookingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_booking, container, false);
        webview = view.findViewById(R.id.webview);
        layout = view.findViewById(R.id.layout);
        tv_loading = view.findViewById(R.id.tv_loading);
        progressbar = view.findViewById(R.id.progressbar);
        webview.setWebViewClient(webViewClient);
        webview.setWebChromeClient(webChromeClient);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//Enlarge the content in html into a column of equal width in webview
        //Set not to cache
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //Set up an both adaptive screen
        webSettings.setUseWideViewPort(true); //Resize the picture to fit the webview
        webSettings.setLoadWithOverviewMode(true); // Zoom to the size of the screen
        //Zoom operation
        webSettings.setSupportZoom(true); //Support zoom, the default is true. It is the premise of the following.
        webSettings.setBuiltInZoomControls(false); //Set the built-in zoom control. If false, the WebView is not scalable
        webSettings.setDisplayZoomControls(false); //Hide native zoom controls
        webSettings.setAllowFileAccess(true); //Set access to files
//        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //Support to open a new window through JS
        webSettings.setLoadsImagesAutomatically(true); //Support auto loading pictures
        webSettings.setDefaultTextEncodingName("utf-8");//Set encoding format
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webview.loadUrl("https://www.skyscanner.com/?locale=en-US&currency=USD&market=US");
        return view;
    }

    //WebViewClient mainly helps WebView to handle various notifications and request events
    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //The page starts to load
            layout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //Page loading completed
            layout.setVisibility(View.GONE);
            webview.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient mainly assists WebView in handling Javascript dialog boxes, website icons, website titles, loading progress, etc.
    private WebChromeClient webChromeClient = new WebChromeClient() {

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressbar.setProgress(newProgress);
            tv_loading.setText("loading " + newProgress + "% ...");
        }
    };


}
