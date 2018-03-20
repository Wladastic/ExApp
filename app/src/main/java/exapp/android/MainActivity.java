package exapp.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    View include_main;
    View include_webView;

    Button ChatMenu_showButton;
    HorizontalScrollView ChatMenu_ScrollView;

    LinearLayout Chathistory_LinearLayout;

    EditText ChatMessage_EditText;

    Button ChatMessage_SendButton;
    WebView webview;

    LinearLayout.LayoutParams _TextLayout_params;
    LinearLayout.LayoutParams _LinearLayout_params_received;
    LinearLayout.LayoutParams _LinearLayout_params_sent;
    CardView.LayoutParams _CardView_Entry_params;

    ImageView paypal_chatmenu_Button;

    float scale;
    int cardradius ;
    int contentpadding ;
    int message_margin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("~: Exapp Bot");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        get_new_Messages("Wlad");

        scale = getResources().getDisplayMetrics().density;
        cardradius = (int) (10 * scale + 0.5f);
        contentpadding = (int) (5 * scale + 0.5f);
        message_margin = (int) (5 * scale + 0.5f);

        include_main = findViewById(R.id.include_main);
        include_webView = findViewById(R.id.include_fb);

        ChatMenu_showButton = (Button) findViewById(R.id.chatMenu_moneyButton);
        ChatMenu_ScrollView = (HorizontalScrollView) findViewById(R.id.chatMenu_ScrollView);
        ChatMenu_ScrollView.setVisibility(View.GONE);

        ChatMessage_EditText = (EditText) findViewById(R.id.ChatMessage_EditText);
        ChatMessage_SendButton = (Button) findViewById(R.id.ChatMessage_SendButton);
        Chathistory_LinearLayout = (LinearLayout) findViewById(R.id.ChatHistory_LinearLayout);

        _TextLayout_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        _TextLayout_params.setMargins(15, 0, 10, 5);


        _CardView_Entry_params = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        _CardView_Entry_params.setMargins(10, 0, 10, 20);

        _LinearLayout_params_sent = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        _LinearLayout_params_sent.setMargins(message_margin, message_margin, message_margin, message_margin);
        _LinearLayout_params_sent.gravity = Gravity.END;
        _LinearLayout_params_received = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        _LinearLayout_params_received.setMargins(message_margin, message_margin, message_margin, message_margin);
        _LinearLayout_params_received.gravity = Gravity.START;

        paypal_chatmenu_Button = (ImageView) findViewById(R.id.paypal_chatmenu_Button);

        paypal_chatmenu_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chat_addMessage(ChatMessage_EditText.getText().toString(), false);
            }
        });

        ChatMessage_SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chat_addMessage(ChatMessage_EditText.getText().toString(), true);
                ChatMessage_EditText.setText("");
            }
        });

        ChatMenu_showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ChatMenu_ScrollView.getVisibility() == View.VISIBLE) {
                    ChatMenu_ScrollView.setVisibility(View.GONE);
                    ChatMenu_showButton.setBackgroundDrawable(getResources().getDrawable(R.mipmap.icondropdown_up));

                } else {
                    ChatMenu_ScrollView.setVisibility(View.VISIBLE);
                    ChatMenu_showButton.setBackgroundDrawable(getResources().getDrawable(R.mipmap.icondropdown_down));
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean authenticate(){
        return true;
    }

    public String[] get_new_Messages(String contact_id){
        return new String[]{"hey du", "wie geht's dir?"};
    }

    public void request_messages(String contact_id) {
        if (authenticate()){

            String[] new_Messages = get_new_Messages(contact_id);
            if (new_Messages.length > 0) {
                for (String message : new_Messages){
                    Chat_addMessage(message, false);
                }
            }

            //
        }
    }

    private void Chat_addMessage(String msg, Boolean sent) {
        CardView newMessageCardView = new CardView(this);
        TextView newMessageTextView = new TextView(this);

        newMessageTextView.setLayoutParams(_TextLayout_params);

        if (sent) {
            newMessageCardView.setLayoutParams(_LinearLayout_params_sent);
            newMessageCardView.setCardBackgroundColor(getResources().getColor(R.color.message_sent));
        } else {
            newMessageCardView.setLayoutParams(_LinearLayout_params_received);
            newMessageCardView.setCardBackgroundColor(getResources().getColor(R.color.message_received));
            newMessageTextView.setTextColor(getResources().getColor(R.color.message_received_text));
        }

        newMessageCardView.setRadius(cardradius);
        newMessageCardView.setContentPadding(contentpadding, contentpadding, contentpadding, contentpadding);
        newMessageTextView.setText(msg);

        newMessageCardView.addView(newMessageTextView);
        Chathistory_LinearLayout.addView(newMessageCardView);
    }


    private void load_webView(String _url) {
        WebView webview = (WebView) findViewById(R.id.fb_webview);
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(_url);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadActivity(String _url, String _title) {
        load_webView(_url);
        setTitle(_title);
        include_webView.setVisibility(View.VISIBLE);
        include_main.setVisibility(View.GONE);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_instagram) {
            loadActivity("https://www.instagram.com/", "Instagram");
        } else if (id == R.id.nav_facebook) {
            loadActivity("https://m.facebook.com/", "Facebook");
        } else if (id == R.id.nav_Youtube) {
            loadActivity("https://m.youtube.com/", "Youtube");
        } else if (id == R.id.nav_ebay_Kleinanzeigen) {
            loadActivity("https://m.ebay-kleinanzeigen.de/", "Ebay Kleinanzeigen");
        } else if (id == R.id.nav_twitter) {
            loadActivity("https://mobile.twitter.com/", "Twitter");
        } else if (id == R.id.nav_aliexpress) {
            loadActivity("https://m.aliexpress.com/", "Aliexpress");
        } else if (id == R.id.nav_lieferando) {
            loadActivity("https://www.lieferando.de/", "Lieferando");
        } else if (id == R.id.nav_sparkasse) {
            loadActivity("https://www.sparkasse.de/", "Sparkasse");
        } else if (id == R.id.nav_share) {
            loadActivity("https://m.facebook.com/ExApp-Messenger-770498696462926/", "share Exapp");
        } else if (id == R.id.nav_send) {
            include_webView.setVisibility(View.GONE);
            include_main.setVisibility(View.VISIBLE);
            setTitle("~: Exapp Bot");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
