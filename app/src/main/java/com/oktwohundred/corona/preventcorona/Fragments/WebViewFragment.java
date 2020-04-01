package com.oktwohundred.corona.preventcorona.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.OPPS;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.PYC_LOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    String BlogUrl ="https://www.who.int/health-topics/coronavirus#tab=tab_1";
    WebView webview;
    ProgressBar load;
    TextView nonettext;
    Context context;

    public WebViewFragment(String blogUrl, Context context) {
        BlogUrl = blogUrl;
        this.context = context;
    }


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View wv = inflater.inflate(R.layout.fragment_web_view, container, false);
        webview = wv.findViewById(R.id.webview);
        load = wv.findViewById(R.id.load);
        nonettext = wv.findViewById(R.id.nonettext);
        initWebView();
        return wv;
    }

    private void initWebView() {

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);

        webview.setWebChromeClient(new MyWebChromeClient(context));
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                load.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.i(PYC_LOG,"Url getting is "+url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                load.setVisibility(View.GONE);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                load.setVisibility(View.GONE);
                CommonMethods.makeAlert(context, OPPS, error.getDescription().toString());
            }

            @Override
            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                super.onReceivedClientCertRequest(view, request);
            }
        });

        webview.loadUrl(BlogUrl);


    }
    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }


    }

}
