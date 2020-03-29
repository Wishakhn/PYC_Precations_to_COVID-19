package com.oktwohundred.corona.preventcorona.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oktwohundred.corona.preventcorona.Adapter.timezoneAdapter;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.Helpers.PermissionHandler;
import com.oktwohundred.corona.preventcorona.Helpers.preferenceClass;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.allcountry;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Activities.MainActivity;
import com.oktwohundred.corona.preventcorona.ExpenssionLayout.ExpansionLayout;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.oktwohundred.corona.preventcorona.Helpers.CommonMethods.makeAlert;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_USER_IS_ACTIVE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.PYC_LOG;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.REQUEST_PERMISSION_CODE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.RESULT_CAPTURE_IMAGE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.RESULT_LOAD_IMAGE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.SORRY;


public class RegisterFragment extends Fragment {



    public RegisterFragment() {
        // Required empty public constructor
    }
    private FirebaseAuth auth;
    DatabaseReference firebaseRef;

    private CircleImageView uploadimage_reg;
    private CardView btn_reg;
    private TextView logintext;


    private ImageView reg_nameicon;
    private EditText reg_nameedit;
    private View reg_nameview;
    private TextView errortextname;

    private ImageView reg_mailicon;
    private EditText reg_mailedit;
    private View reg_mailview;
    private TextView errortextmail;


    private ImageView reg_passicon;
    private EditText reg_passedit;
    private View reg_passview;
    private TextView errortextpass;

    private ImageView reg_dobicon;
    private EditText reg_dobedit;
    private View reg_dobview;
    private TextView errortextdob;
    private CardView date_picker;
    private DatePicker datePicker1;
    private CardView no_date;
    private CardView pick_date;
    LinearLayout dob_conatiner;


    private ImageView gendericon;
    private View reg_genderview;
    private ImageView indecator_genderP;
    private ExpansionLayout gender_listP;
    private EditText genderedit;
    private TextView femaleP;
    private TextView maleP;
    private TextView noneeP;
    private TextView errortextgen;


    private ImageView countryicon;
    private EditText countryedit;
    private View reg_countryview;
    private ImageView indecator_timeP;
    private ExpansionLayout timezoneP;
    private RecyclerView timezonecylcer;
    private TextView errortextcountry;
    timezoneAdapter tAdapter;
    List<allcountry> countryItem;


    private ImageView sageicon;
    private EditText stageedit;
    private View reg_stageview;
    private ImageView indecator_stage;
    private ExpansionLayout user_liststage;
    private TextView stage1;
    private TextView stage2;
    private TextView stage3;
    private TextView stage4;
    private TextView stage5;
    private TextView errortextstage;

    private String profImage = "default";
    private String gender = "";
    private String stage = "";
    private String dob = "";
    private String name = "";
    private String email = "";
    private String password = "";
    private String country = "";
    Context context;
    preferenceClass prefs;

    public RegisterFragment(Context context) {
        this.context = context;
        prefs = new preferenceClass(context);
        prefs.initPreference();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.fragment_register, container, false);
        initiateViews(rv);
        underline(logintext);
        setLisnterforFragment();
        return rv;
    }

    private void initiateViews(View rv) {
        auth = FirebaseAuth.getInstance();
        profImage = "" + getResources().getDrawable(R.drawable.profile_image);
        btn_reg = rv.findViewById(R.id.btn_reg);
        uploadimage_reg = rv.findViewById(R.id.uploadimage_reg);
        reg_nameicon = rv.findViewById(R.id.reg_nameicon);
        reg_nameedit = rv.findViewById(R.id.reg_nameedit);
        reg_nameview = rv.findViewById(R.id.reg_nameview);
        logintext = rv.findViewById(R.id.logintext);
        errortextname = rv.findViewById(R.id.errortextname);

        reg_mailicon = rv.findViewById(R.id.reg_mailicon);
        reg_mailedit = rv.findViewById(R.id.reg_mailedit);
        reg_mailview = rv.findViewById(R.id.reg_mailview);
        errortextmail = rv.findViewById(R.id.errortextmail1);

        reg_passicon = rv.findViewById(R.id.reg_passicon);
        reg_passedit = rv.findViewById(R.id.reg_passedit);
        reg_passview = rv.findViewById(R.id.reg_passview);
        errortextpass = rv.findViewById(R.id.errortextpass1);


        reg_dobicon = rv.findViewById(R.id.reg_dobicon);
        reg_dobedit = rv.findViewById(R.id.reg_dobedit);
        reg_dobview = rv.findViewById(R.id.reg_dobview);
        errortextdob = rv.findViewById(R.id.errortextdob);
        date_picker = rv.findViewById(R.id.date_picker);
        no_date = rv.findViewById(R.id.no_date);
        pick_date = rv.findViewById(R.id.pick_date);
        datePicker1 = rv.findViewById(R.id.datePicker1);
        dob_conatiner = rv.findViewById(R.id.dob_conatiner);


        gendericon = rv.findViewById(R.id.gendericon);
        genderedit = rv.findViewById(R.id.genderedit);
        reg_genderview = rv.findViewById(R.id.reg_genderview);
        indecator_genderP = rv.findViewById(R.id.indecator_genderP);
        gender_listP = rv.findViewById(R.id.gender_listP);
        femaleP = rv.findViewById(R.id.femaleP);
        maleP = rv.findViewById(R.id.maleP);
        noneeP = rv.findViewById(R.id.noneeP);
        errortextgen = rv.findViewById(R.id.errortextgen);

        countryItem = new ArrayList<>();
        countryicon = rv.findViewById(R.id.countryicon);
        countryedit = rv.findViewById(R.id.countryedit);
        reg_countryview = rv.findViewById(R.id.reg_countryview);
        indecator_timeP = rv.findViewById(R.id.indecator_timeP);
        timezoneP = rv.findViewById(R.id.timezoneP);
        timezonecylcer = rv.findViewById(R.id.timezonecylcer);
        timezonecylcer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        errortextcountry = rv.findViewById(R.id.errortextcountry);
        tAdapter = new timezoneAdapter(countryItem,timezoneP,countryedit);

        sageicon = rv.findViewById(R.id.sageicon);
        stageedit = rv.findViewById(R.id.stageedit);
        reg_stageview = rv.findViewById(R.id.reg_stageview);
        indecator_stage = rv.findViewById(R.id.indecator_stage);
        user_liststage = rv.findViewById(R.id.user_liststage);
        stage1 = rv.findViewById(R.id.stage1);
        stage2 = rv.findViewById(R.id.stage2);
        stage3 = rv.findViewById(R.id.stage3);
        stage4 = rv.findViewById(R.id.stage4);
        stage5 = rv.findViewById(R.id.stage5);
        errortextstage = rv.findViewById(R.id.errortextstage);

    }

    void setLisnterforFragment() {
        loadAllcountries();
        btn_reg.setOnClickListener(registerListener);
        uploadimage_reg.setOnClickListener(getImageListner);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginFragment();
            }
        });
        pick_date.setOnClickListener(selectpickerListener);
        no_date.setOnClickListener(cancelpickerLintener);
        dob_conatiner.setOnClickListener(displayDateLstener);
        femaleP.setOnClickListener(femaleListner);
       maleP.setOnClickListener(maleListner);
     noneeP.setOnClickListener(noneListner);
     stage1.setOnClickListener(stageListner);
     stage2.setOnClickListener(stageListner2);
     stage3.setOnClickListener(stageListner3);
     stage4.setOnClickListener(stageListner4);
     stage5.setOnClickListener(stageListner5);



    }
    private View.OnClickListener femaleListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gender = femaleP.getText().toString().trim();
            genderedit.setText(gender);
            gender_listP.collapse(true);
        }
    };
    private View.OnClickListener maleListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gender = maleP.getText().toString().trim();
            genderedit.setText(gender);
            gender_listP.collapse(true);
        }
    };
    private View.OnClickListener noneListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gender = noneeP.getText().toString().trim();
            genderedit.setText(gender);
            gender_listP.collapse(true);
        }
    };
    private View.OnClickListener stageListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stage = stage1.getText().toString().trim();
            stageedit.setText(stage);
            user_liststage.collapse(true);
        }
    }; private View.OnClickListener stageListner2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stage = stage2.getText().toString().trim();
            stageedit.setText(stage);
            user_liststage.collapse(true);
        }
    }; private View.OnClickListener stageListner3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stage = stage3.getText().toString().trim();
            stageedit.setText(stage);
            user_liststage.collapse(true);
        }
    }; private View.OnClickListener stageListner4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stage = stage4.getText().toString().trim();
            stageedit.setText(stage);
            user_liststage.collapse(true);
        }
    }; private View.OnClickListener stageListner5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stage = stage5.getText().toString().trim();
            stageedit.setText(stage);
            user_liststage.collapse(true);
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name = reg_nameedit.getText().toString().trim();
            email = reg_mailedit.getText().toString().trim();
            password = reg_passedit.getText().toString().trim();
            country = tAdapter.returnTimezone();
            if (checkfield() && validateEmail(email, errortextmail)) {
                requestRegistration(email,password);
            } else {
                makeAlert(getContext(), SORRY, "You cannot register until you fill the missing fields.");
            }

        }
    };
    private View.OnClickListener getImageListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (PermissionHandler.checkCameraPermissions(getContext())) {
                createDialogforImage();
            } else {
                createDialogforPermission();
            }
        }
    };

    private View.OnClickListener displayDateLstener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (date_picker.getVisibility() == View.GONE) {
                date_picker.setVisibility(View.VISIBLE);
            }
            CommonMethods.stopEditing(reg_passedit, getContext());
        }
    };
    private View.OnClickListener cancelpickerLintener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (date_picker.getVisibility() == View.VISIBLE) {
                date_picker.setVisibility(View.GONE);
            }
        }
    };
    private View.OnClickListener selectpickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (date_picker.getVisibility() == View.VISIBLE) {
                date_picker.setVisibility(View.GONE);
            }
            dob = datePicker1.getDayOfMonth() + "/" + (datePicker1.getMonth() + 1) + "/" + datePicker1.getYear();
            reg_dobedit.setText(dob);
        }
    };

    void callLoginFragment() {
        LoginFragment fragment2 = new LoginFragment(context);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_authfragment, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        TextView textLabel = getActivity().findViewById(R.id.labelfragment);
        textLabel.setText("Login");
    }

    private void loadAllcountries() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countryItem.contains(country)) {
                countryItem.add(new allcountry(country));
            }
        }
        tAdapter.notifyDataSetChanged();
        timezonecylcer.setAdapter(tAdapter);
        tAdapter.notifyDataSetChanged();

    }
    void underline(TextView text) {
        String content1 = text.getText().toString().trim();
        SpannableString spannableString1 = new SpannableString(content1);
        spannableString1.setSpan(new UnderlineSpan(), 25, content1.length(), 0);
        text.setText(spannableString1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                makeAlert(getContext(), SORRY, getResources().getString(R.string.youcannotgonext));
            } else {
                createDialogforImage();
            }
        }
    }

    private void createDialogforPermission() {
        AlertDialog.Builder chekPer = new AlertDialog.Builder(getContext());
        View v = getLayoutInflater().inflate(R.layout.permission_dialog, null);
        chekPer.setView(v);
        final AlertDialog dialog = chekPer.create();
        dialog.show();
        CardView allowPermissions;
        CardView noPermission;
        TextView detailsPer;
        allowPermissions = v.findViewById(R.id.allow_permissions);
        noPermission = v.findViewById(R.id.no_permission);
        detailsPer = v.findViewById(R.id.details_per);
        detailsPer.setText(getResources().getString(R.string.permission_str));
        noPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        allowPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHandler.requestPermissions(getActivity());
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void createDialogforImage() {
        AlertDialog.Builder chekPer = new AlertDialog.Builder(getContext());
        View v = getLayoutInflater().inflate(R.layout.permission_dialog, null);
        chekPer.setView(v);
        final AlertDialog dialog = chekPer.create();
        dialog.show();
        CardView allowPermissions;
        CardView noPermission;
        TextView detailsPer;
        TextView btnright;
        TextView btnleft;
        allowPermissions = v.findViewById(R.id.allow_permissions);
        noPermission = v.findViewById(R.id.no_permission);
        detailsPer = v.findViewById(R.id.details_per);
        btnleft = v.findViewById(R.id.btnleft);
        btnright = v.findViewById(R.id.btnright);
        btnleft.setText(getResources().getString(R.string.camera));
        btnright.setText(getResources().getString(R.string.gallery));
        detailsPer.setText(getResources().getString(R.string.imageselector_str));
        noPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hanlderforImage(0);
                dialog.dismiss();
            }
        });
        allowPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hanlderforImage(1);
                dialog.dismiss();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void hanlderforImage(int i) {
        if (i == 0) {
            takeImagefromCamera();
        } else {
            takeImagefromGallery();
        }
    }

    private void takeImagefromGallery() {
        Intent g = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(g, RESULT_LOAD_IMAGE);
    }

    private void takeImagefromCamera() {
        Intent c = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(c, RESULT_CAPTURE_IMAGE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {

            Uri selectedImage = data.getData();
            uploadimage_reg.setImageURI(selectedImage);
            profImage = String.valueOf(selectedImage);
          /*  try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                profImage = CommonMethods.imageToString(bitmap);
            } catch (IOException e) {
                Log.i(PYC_LOG, "BITMAP  EXCEPTION " + e);
            }*/
            Log.i(PYC_LOG, "Register selected is " + profImage);

        } else if (requestCode == RESULT_CAPTURE_IMAGE && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profImage = CommonMethods.getImageUri(context,photo);

        }

    }

    private boolean checkfield() {
        boolean fieldmissed = true;
        if (name.isEmpty() || name.equalsIgnoreCase("user name")) {
            fieldmissed = false;
            errortextname.setVisibility(View.VISIBLE);
        } else {
            errortextname.setVisibility(View.GONE);
        }

        if (password.isEmpty() || password.equalsIgnoreCase("password")) {
            fieldmissed = false;
            errortextpass.setVisibility(View.VISIBLE);
        } else {
            errortextpass.setVisibility(View.GONE);
        }

        if (dob.isEmpty() || dob.equalsIgnoreCase("dd/mm/yyyy")) {
            fieldmissed = false;
            errortextdob.setVisibility(View.VISIBLE);
        } else {
            errortextdob.setVisibility(View.GONE);
        }

        if (gender.isEmpty() || gender.equalsIgnoreCase("none")) {
            fieldmissed = false;
            errortextgen.setVisibility(View.VISIBLE);
        } else {
            errortextgen.setVisibility(View.GONE);
        }

        if (stage.isEmpty() || stage.equalsIgnoreCase("stage")) {
            fieldmissed = false;
            errortextstage.setVisibility(View.VISIBLE);
        } else {
            errortextstage.setVisibility(View.GONE);
        }

        if (country.isEmpty() || country.equalsIgnoreCase("country")) {
            fieldmissed = false;
            errortextcountry.setVisibility(View.VISIBLE);
        } else {
            errortextcountry.setVisibility(View.GONE);
        }
        return fieldmissed;
    }

    private boolean validateEmail(String str, TextView errortext) {
        boolean isEmailvalid = true;
        if (str.equalsIgnoreCase("") || !CommonMethods.isValidEmail(str) || str.isEmpty()) {
            errortext.setVisibility(View.VISIBLE);
            errortext.setText("Enter a valid e-mail !!");
            isEmailvalid = false;
        } else {
            errortext.setVisibility(View.GONE);
        }
        return isEmailvalid;
    }

    private void requestRegistration(String mail, final String pass) {
        CommonMethods.displayProgressDialog(getContext(), "Registering . . . . ");
        auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                CommonMethods.hideProgressDialog();
                if (task.isSuccessful()) {
                    FirebaseUser fUser = auth.getCurrentUser();
                    assert fUser != null;
                    final String userId = fUser.getUid();
                    System.out.println("User ID is " + userId);
                    firebaseRef = FirebaseDatabase.getInstance().getReference("PycUsers").child(userId);
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("firebaseId", userId);
                    hashmap.put("userName", name);
                    hashmap.put("userMail", email);
                    hashmap.put("userGender", gender);
                    hashmap.put("userDob", dob);
                    hashmap.put("userCountry", country);
                    hashmap.put("userImage", profImage);
                    hashmap.put("password", pass);
                    hashmap.put("userStage", stage);
                    hashmap.put("userStatus", "1");
                    Log.i(PYC_LOG,"Sending Params "+hashmap);
                    firebaseRef.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                DbManager db= new DbManager(getContext());
                                db.deleteAllUsers();
                                db.insertUserData(0123,userId,name,email,profImage,gender,dob,country,stage,"1");
                                prefs.save_boolean(KEY_USER_IS_ACTIVE,true);
                                CommonMethods.intentHandler(getContext(), MainActivity.class);
                            }
                            else {
                                CommonMethods.makeAlert(getContext(),SORRY,"Unable to register this user");
                            }
                        }
                    });
                } else {
                    CommonMethods.makeAlert(getContext(),SORRY,""+task.getException().getMessage());

                }
            }
        });
    }

}
