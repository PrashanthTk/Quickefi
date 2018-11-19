
package com.quickefi.retailapp.view.fragment;
import com.android.volley.toolbox.StringRequest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fra;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import com.quickefi.retailapp.domain.helper.FireMissilesDialogFragment;
import com.android.volley.VolleyError;
import com.quickefi.retailapp.domain.MyApplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.quickefi.retailapp.R;
import com.quickefi.retailapp.domain.api.EndPoints;
import com.quickefi.retailapp.domain.api.JsonRequest;
import com.quickefi.retailapp.domain.helper.FireMissilesDialogFragment;
import com.quickefi.retailapp.util.PreferenceHelper;
import com.quickefi.retailapp.util.Utils;
import com.quickefi.retailapp.util.Utils.AnimationType;
import com.quickefi.retailapp.view.activities.ECartHomeActivity;
import com.quickefi.retailapp.util.JsonUtils;
import org.json.JSONObject;
import timber.log.Timber;
// TODO: Auto-generated Javadoc

/**
 * Fragment that appears in the "content_frame", shows a animal.
 */
public class SettingsFragment extends Fragment {

    private TextView submitLog;
    private Toolbar mToolbar;

    /**
     * Instantiates a new settings fragment.
     */
    public SettingsFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance() {
        // TODO Auto-generated method stub
        return new SettingsFragment();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_settings, container,
                false);

        getActivity().setTitle("About App");

        mToolbar = (Toolbar) rootView.findViewById(R.id.htab_toolbar);
        if (mToolbar != null) {
            ((ECartHomeActivity) getActivity()).setSupportActionBar(mToolbar);
        }

        if (mToolbar != null) {
            ((ECartHomeActivity) getActivity()).getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);

            mToolbar.setNavigationIcon(R.drawable.ic_drawer);

        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ECartHomeActivity) getActivity()).getmDrawerLayout()
                        .openDrawer(GravityCompat.START);
            }
        });

        mToolbar.setTitleTextColor(Color.WHITE);

        submitLog = (TextView) rootView.findViewById(R.id.submit_log_txt);

        if (PreferenceHelper.getPrefernceHelperInstace().getBoolean(
                getActivity(), PreferenceHelper.SUBMIT_LOGS, true)) {

            submitLog.setText("Disable");
        } else {
            submitLog.setText("Enable");
        }

        rootView.findViewById(R.id.submit_log).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (PreferenceHelper.getPrefernceHelperInstace()
                                .getBoolean(getActivity(),
                                        PreferenceHelper.SUBMIT_LOGS, true)) {
                            PreferenceHelper
                                    .getPrefernceHelperInstace()
                                    .setBoolean(getActivity(),
                                            PreferenceHelper.SUBMIT_LOGS, false);

                            submitLog.setText("Disable");
                        } else {
                            PreferenceHelper.getPrefernceHelperInstace()
                                    .setBoolean(getActivity(),
                                            PreferenceHelper.SUBMIT_LOGS, true);
                            submitLog.setText("Enable");
                        }

                    }
                });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP
                        && keyCode == KeyEvent.KEYCODE_BACK) {

                    Utils.switchContent(R.id.frag_container,
                            Utils.HOME_FRAGMENT,
                            ((ECartHomeActivity) (getContext())),
                            AnimationType.SLIDE_UP);

                }
                return true;
            }
        });

        rootView.findViewById(R.id.picasso).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/square/picasso"));
                        startActivity(browserIntent);

                    }
                });

        rootView.findViewById(R.id.acra).setOnClickListener(

                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int tkuser=2;
                        //FireMissilesDialogFragment myalert1= new FireMissilesDialogFragment();
                        //myalert1.show();
                        //String url = String.format(EndPoints.CONTACT, tkuser);
                        //JsonRequest req = new JsonRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        String url ="http://www.google.com";

// Request a string response from the provided URL.
                        StringRequest req = new StringRequest(Request.Method.GET, url,//null,
                                new Response.Listener<String>() {
                            @Override
                            //public void onResponse(JSONObject response) {
                            public void onResponse(String response) {
                                //System.out.println("getCartCount: %s", response.toString());
                                System.out.println(" Finally managerd to hget a user frmo heroku"+response.toString());
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://google.com/"));
                                startActivity(browserIntent);

                /*try {
                    showNotifyCount(response.getInt(JsonUtils.TAG_PRODUCT_COUNT));
                } catch (Exception e) {
                    System.err.println("Unable to get that basic contact from mongo heroku");
                    //showNotifyCount(0);
                }*/
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("Ran into an error in SettingsFragemtn");
                                Timber.e(error.toString());
                                // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

// 2. Chain together various setter methods to set the dialog characteristics
                                builder.setMessage("Yo TKe")
                                        .setTitle("Whaddup");

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
                                AlertDialog dialog = builder.create();
                                //showNotifyCount(0);
                            }
                        }
                                //null,
                                //Activity.getSupportFragmentManager(),
                                //EndPoints.accesstokenonfidomock//user.getAccessToken()
                        );
                        req.setRetryPolicy(MyApplication.getDefaultRetryPolice());
                        req.setShouldCache(false);
                        MyApplication.getInstance().addToRequestQueue(req, "main_activity_requests");
                                //CONST.MAIN_ACTIVITY_REQUESTS_TAG);


        /*

  updateContact(contact: Contact): void {
    this.contactService.updateContact(contact).then((updatedContact: Contact) => {
      this.updateHandler(updatedContact);
    });
  }

  deleteContact(contactId: String): void {
    this.contactService.deleteContact(contactId).then((deletedContactId: String) => {
      this.deleteHandler(deletedContactId);
    });
  }
         */
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/ACRA/acra"));
                        startActivity(browserIntent);

                    }
                });

        rootView.findViewById(R.id.pull_zoom_view).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/Frank-Zhu/PullZoomView"));
                        startActivity(browserIntent);

                    }
                });

        rootView.findViewById(R.id.list_buddies).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/jpardogo/ListBuddies"));
                        startActivity(browserIntent);

                    }
                });

        rootView.findViewById(R.id.list_jazzy).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/twotoasters/JazzyListView"));
                        startActivity(browserIntent);

                    }
                });

        rootView.findViewById(R.id.email_dev).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final Intent emailIntent = new Intent(
                                android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent
                                .putExtra(
                                        android.content.Intent.EXTRA_EMAIL,
                                        new String[]{"serveroverloadofficial@gmail.com"});
                        emailIntent.putExtra(
                                android.content.Intent.EXTRA_SUBJECT,
                                "Hello There");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                                "Add Message here");

                        emailIntent.setType("message/rfc822");

                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(),
                                    "No email clients installed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return rootView;
    }
}
