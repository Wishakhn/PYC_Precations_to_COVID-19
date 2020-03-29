package com.oktwohundred.corona.preventcorona.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.Helpers.Constants;
import com.oktwohundred.corona.preventcorona.Helpers.preferenceClass;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.User;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Activities.MainActivity;

import static com.oktwohundred.corona.preventcorona.Helpers.CommonMethods.makeAlert;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_USER_IS_ACTIVE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.OPPS;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.SORRY;


public class LoginFragment extends Fragment {


    ImageView log_mailicon;
    ImageView log_passicon;
    View log_passview;
    View log_mailview;
    EditText log_passedit;
    EditText logmailedit;
    private String email = "example";
    private String password = "";
    FirebaseAuth auth;
    DatabaseReference firebaseRef;
    DbManager database;

    private TextView errortextpass1;
    private TextView errortextmail1;
    Context context;
    preferenceClass prefs;
    public LoginFragment(Context context) {
        this.context = context;
        prefs = new preferenceClass(context);
        prefs.initPreference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lv = inflater.inflate(R.layout.fragment_login, container, false);
        TextView registerFragment = lv.findViewById(R.id.registerFragment);
        auth = FirebaseAuth.getInstance();
        database = new DbManager(context);
        logmailedit = lv.findViewById(R.id.logmailedit);
        log_passedit = lv.findViewById(R.id.log_passedit);
        log_mailicon = lv.findViewById(R.id.log_mailicon);
        log_passicon = lv.findViewById(R.id.log_passicon);
        log_passview = lv.findViewById(R.id.log_passview);
        log_mailview = lv.findViewById(R.id.log_mailview);
        errortextpass1 = lv.findViewById(R.id.errortextpass1);
        errortextmail1 = lv.findViewById(R.id.errortextmail1);
        underline(registerFragment);

        logmailedit.addTextChangedListener(mailListener);
        log_passedit.addTextChangedListener(passListener);
        log_passedit.setOnEditorActionListener(passDoneLisnter);
        CardView btn_login = lv.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = logmailedit.getText().toString().trim();
                password = log_passedit.getText().toString().trim();
                if (CommonMethods.isNetworkConnected(getContext())) {
                    if (validateEmail_Password()) {
                        loginUser(email, password);
                    } else {
                        makeAlert(getContext(), SORRY, "You cannot Login until you fill the missing fields.");

                    }
                } else {
                    makeAlert(getContext(), OPPS, Constants.CHECK_INTERNET);
                }
            }
        });
        registerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisterFragment();
            }
        });
        return lv;

    }

    private TextView.OnEditorActionListener passDoneLisnter = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                CommonMethods.unselectLayout(getContext(), log_passicon, log_passview, R.drawable.lock);
            }
            return false;
        }
    };
    private TextWatcher mailListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CommonMethods.selectLayout(getContext(), log_mailicon, log_mailview, R.drawable.mail_selected);
            CommonMethods.unselectLayout(getContext(), log_passicon, log_passview, R.drawable.lock);
        }
    };
    private TextWatcher passListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CommonMethods.selectLayout(getContext(), log_passicon, log_passview, R.drawable.lock_selected);
            CommonMethods.unselectLayout(getContext(), log_mailicon, log_mailview, R.drawable.mail);
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }

    void callRegisterFragment() {
        RegisterFragment fragment2 = new RegisterFragment(context);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_authfragment, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        TextView textLabel = getActivity().findViewById(R.id.labelfragment);
        textLabel.setText("Register");
    }

    void underline(TextView text) {
        String content1 = text.getText().toString().trim();
        SpannableString spannableString1 = new SpannableString(content1);
        spannableString1.setSpan(new UnderlineSpan(), 23, content1.length(), 0);
        text.setText(spannableString1);
    }

    ;

    private boolean validateEmail_Password() {
        boolean isEmailvalid = true;
        if (email.equalsIgnoreCase("example") || !CommonMethods.isValidEmail(email) || email.isEmpty()) {
            isEmailvalid = false;
            errortextmail1.setVisibility(View.VISIBLE);
            errortextmail1.setText("Enter a valid e-mail !!");

        } else {
            errortextmail1.setVisibility(View.GONE);
        }
        if (password.isEmpty()) {
            isEmailvalid = false;
            errortextpass1.setVisibility(View.VISIBLE);
        } else {
            errortextpass1.setVisibility(View.GONE);
        }

        return isEmailvalid;
    }

    private void loginUser(String email, String pass) {
        CommonMethods.displayProgressDialog(getContext(), "Logging-In  . . . . ");

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser fUser = auth.getCurrentUser();
                    assert fUser != null;
                    final String userId = fUser.getUid();
                    System.out.println("User ID is " + userId);
                    firebaseRef = FirebaseDatabase.getInstance().getReference("PycUsers").child(userId).child("userStatus");
                    firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            firebaseRef.setValue("1");
                            loadUserData(userId);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {
                    makeAlert(getContext(), SORRY, "" + task.getException().getMessage());

                }
            }
        });
    }

    private void loadUserData(final String id) {

        firebaseRef = FirebaseDatabase.getInstance().getReference("PycUsers").child(id);
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CommonMethods.hideProgressDialog();
                User modelUser = dataSnapshot.getValue(User.class);
                String userId = modelUser.getFirebaseId();
                String name = modelUser.getUserName();
                String dob = modelUser.getUserDob();
                String stage = modelUser.getUserStage();
                String country = modelUser.getUserCountry();
                String gen = modelUser.getUserGender();
                String email = modelUser.getUserMail();
                String status = modelUser.getUserStatus();
                String image = modelUser.getUserImage();
                database.deleteAllUsers();
                database.insertUserData(0123,userId,name,email,image,gen,dob,country,stage,status);
                prefs.save_boolean(KEY_USER_IS_ACTIVE,true);

                CommonMethods.intentHandler(getContext(), MainActivity.class);

                System.out.println("Database is :" + dataSnapshot.getChildren());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
